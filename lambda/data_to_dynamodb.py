import csv
import pandas as pd
import json
import boto3
import uuid
import time
from collections import defaultdict
from boto3.dynamodb.conditions import Key, And

Aws_access_key_id = 'Your_access_key'
Aws_secret_access_key = 'Secret_Access_Key'


s3_client = boto3.client('s3')
rekognition_client = boto3.client('rekognition')
dynamodb = boto3.resource('dynamodb', aws_access_key_id=Aws_access_key_id,
                          aws_secret_access_key=Aws_secret_access_key, region_name='Region_name')
table = dynamodb.Table('Table_Name')
filename = 'csv_file_location'
df = pd.read_csv(filename)
count = 0
with open(filename) as file_obj:
    reader_obj = csv.reader(file_obj)
    for row in reader_obj:
        if count < 1:
            count += 1
            continue
        count += 1
        item = {
            'recipeID': row[9],
            'isVeg': row[11],
            'cuisine': row[3],
            'translatedRecipeName': row[0],
            'translatedIngredients': row[1],
            'cleanedIngredients': row[6],
            'translatedInstructions': row[4],
            'ingredientCount': int(row[8]),
            'totalTimeInMins': int(row[2]),
            'imageURL': row[7]
        }

        res = table.put_item(Item=item)
