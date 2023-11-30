from PIL import Image
import io
import subprocess
import boto3
import base64
import json

def resize_image(image_path, max_size=(640, 480)):
    with Image.open(image_path) as img:
        img.thumbnail(max_size)
        resized_image_path = "resized_image.jpg"
        img.save(resized_image_path)
    return resized_image_path

def capture_image_and_publish(file_path="pantryImage.jpg", topic="pantry_topic"):
    # Capture image using libcamera-still
    command = ["libcamera-still", "-t", "5000", "--autofocus-on-capture", "--autofocus-range", "macro", "-o", file_path]
    subprocess.run(command, check=True)

    resized_image_path = resize_image(file_path)

    # AWS IoT Core configuration
    aws_iot_endpoint = "https://a1l1lslp0bql2-ats.iot.us-west-1.amazonaws.com"
    root_ca_path = "/home/pi/smartpantry/AmazonRootCA1.pem"
    cert_path = "/home/pi/smartpantry/device.pem.crt"
    key_path = "/home/pi/smartpantry/private.pem.key"
    client_id = "iotconsole-84f4f428-0280-4fa0-9e05-7e84c2f1a273"

    # Create an AWS IoT client
    iot_client = boto3.client('iot-data', region_name='region-name', aws_access_key_id='*****', aws_secret_access_key='********')

     # Read resized image data
    with open(resized_image_path, "rb") as image_file:
        image_data = image_file.read()
    base64_encoded_data = base64.b64encode(image_data).decode("utf-8")
    message_payload = {
      "image_data": base64_encoded_data,
      "uuid":"ed04cc43-0967-48b5-904c-300d4b32a293"
    }

    # Convert the dictionary to a JSON string
    json_payload = json.dumps(message_payload)

    # Publish image to AWS IoT Core
    response = iot_client.publish(
        topic=topic,
        qos=1,
        payload=json_payload
    )

    print(f"Image published to AWS IoT. Message ID: {response}")

if __name__ == "__main__":
    capture_image_and_publish()
