
import json
import boto3
import uuid
import time
from collections import defaultdict
from urllib.parse import quote, unquote

s3_client = boto3.client('s3')
rekognition_client = boto3.client('rekognition')
dynamodb = boto3.resource('dynamodb')
table = dynamodb.Table('pantryTable')


def extract_user_email(file_key):
    parts = file_key.split('_')
    if len(parts) > 1:
        user_email = '_'.join(parts[0:-1])
        user_email = unquote(user_email)
        return user_email
    else:
        return None


def detect_custom_labels(bucket_name, file_key):
    print("GOT HERE")
    # file_key=file_key.replace("%40","@")
    file_key='mp1.jpeg'
    print({'Bucket': bucket_name, 'Name': file_key})
    
    response = rekognition_client.detect_custom_labels(
        Image={'S3Object': {'Bucket': bucket_name, 'Name': file_key}},
        ProjectVersionArn='arn:aws:rekognition:us-east-1:702549225640:project/SmartPantryProject/version/SmartPantryProject.2023-11-30T00.54.27/1701334467612',
    )

    print("Response:", response)
    filtered_labels = defaultdict(float)

    if 'CustomLabels' in response:
        for label in response['CustomLabels']:
            filtered_labels[label['Name']] = max(
                filtered_labels[label['Name']], label['Confidence'])

    labels_list = list(filtered_labels.keys())
    print("Labels List:", labels_list)

    return labels_list
    # return ['wheat','water','salt']

def get_existing_record(user_email):
    response = table.query(
        KeyConditionExpression='UserEmail=:email',
        ExpressionAttributeValues={':email': user_email}
    )

    if response.get('Items'):
        return response['Items'][0]
    else:
        return None

def process_image(event, user_email):
    bucket_name = event['Records'][0]['s3']['bucket']['name']
    file_key = event['Records'][0]['s3']['object']['key']
    upload_time = time.strftime('%Y-%m-%dT%H:%M:%SZ', time.gmtime())

    print(file_key)
    existing_record = get_existing_record(user_email)
    if existing_record:
        update_existing_record(existing_record, file_key,
                               upload_time, bucket_name)
    else:
        create_new_record(user_email, file_key, upload_time, bucket_name)


def create_new_record(user_email, file_key, upload_time, bucket_name):
    labels_list = detect_custom_labels(bucket_name, file_key)

    table.put_item(
        Item={
            'UserEmail': user_email,
            'FileName': file_key.split('/')[-1],
            'LabelsList': set(labels_list),
            'UploadTime': upload_time,
        }
    )

def update_existing_record(existing_record, file_key, upload_time, bucket_name):
    new_labels = detect_custom_labels(bucket_name, file_key)

    table.update_item(
        Key={'UserEmail': existing_record['UserEmail']},
        UpdateExpression='SET LabelsList = :labels, UploadTime = :time, FileName = :filename',
        ExpressionAttributeValues={
            ':labels': set(new_labels),
            ':time': upload_time,
            ':filename': file_key.split('/')[-1],
        }
    )    


def lambda_handler(event, context):
    file_key = event['Records'][0]['s3']['object']['key']
    user_email = extract_user_email(file_key)
    print("FILENAME", file_key)
    print("UserEMAIL", user_email)

    if user_email:
        process_image(event, user_email)
        return {
            'statusCode': 200,
            'body': json.dumps('Image processed and data stored in DynamoDB')
        }
    else:
        return {
            'statusCode': 400,
            'body': json.dumps('Failed to extract user_email from the file_key')
        }
    
    # detect_custom_labels('homepantry-east', 'V_aishu@gmail.com_2021-10-22.jpeg')
