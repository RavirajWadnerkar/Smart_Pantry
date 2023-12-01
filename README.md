# Smart_Pantry


- **University Name**: [San Jose State University](http://www.sjsu.edu/)

- **Course**: [CMPE-281 Cloud Technologies](https://catalog.sjsu.edu/preview_course_nopop.php?catoid=13&coid=116077)

- **Professor**: [Sanjay Garje ](https://www.linkedin.com/in/sanjaygarje/)

- **Students**: [Upasana Kumar](https://www.linkedin.com/in/upasana-kumar/)
 [Vidhi Agarwal](https://www.linkedin.com/in/vidhi-ag/)    [Aishwarya Manoharan](https://www.linkedin.com/in/aishwaryamano/) [Raviraj Tushar Wadnerkar](https://www.linkedin.com/in/raviraj-wadnerkar/)

- **Download the app** : [Download App](https://main.dtut8i3i124b5.amplifyapp.com/)
- Hardware Setup Video
https://drive.google.com/file/d/1pvJmQJotw5UiLCw-lSS7m_On854O9Ax8/view?usp=sharing

App Setup Video
https://drive.google.com/file/d/1UWmV8bVD3bv8Zy8Ev0t4WH1UximZC6D-/view?usp=sharing


## Project Introduction:
Introducing **Smart Pantry**, the innovative mobile application that transforms kitchen management by blending the convenience of IoT with the robust capabilities of cloud services. Our system features a Raspberry Pi with a camera to scan and identify ingredients in your kitchen, smoothly integrated with AWS IoT Core for immediate data capture. Leveraging Amazon Rekognition, the app adeptly organizes pantry items and suggests recipes aligned with your food preferences. The architecture upholds secure access through Amazon Cognito, while AWS Lambda and DynamoDB work in tandem, processing inputs to customize your culinary journey. Smart Pantry simplifies meal preparation by utilizing what's available or inspiring new recipes that cater to your palate, making it your essential kitchen partner.


## Features List:

1. Our App shows a recommended list of recipes based on user food preference selected at the time of Registration.
2. The App also shows a preferred list of recipes based on ingredients present in the User Pantry.
3. Smart Pantry also gives users a list of ingredients present in the pantry.

## Software Components Used
1. Android Studio
2. Raspbian OS
3. AWS CLI
## Hardware Components Used
1. Raspberry Pi 4B
2. Camera Module
3. SD Card

## Sample Demo Screenshots of the App
### Landing Page

<img width="345" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/144417727/45f5f182-e734-4fd8-8a22-dcbfe09d3d79">

### Login Page

<img width="345" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/144417727/1bc48825-5853-40a9-9564-3aac82dffcfc">

### Register

<img width="345" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/144417727/83ec5757-f26f-4911-b529-69d9f33209a5">

### HomePage with recipe based on your food preference

<img width="401" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/144417727/e8f97b20-2081-463b-973e-948596b597eb">

<img width="836" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/143249088/22593507-cb85-478b-a9fa-256d2436e193">
<img width="598" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/143249088/87a5ed8c-d9a1-4115-867b-fd5cc15f34b5">

Image clicked from the camera module athat got saved into S3 bucket
<img width="655" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/143249088/e4c75b22-c2e9-417a-a44e-01a75f316f7a">

# Architechture Diagram
<img width="655" alt="image" src="https://github.com/avidhi2100/Smart_Pantry/assets/143249088/4ecbe4bc-c933-45d6-92dd-ab36b463b24f">

# Pre-requisites Set Up
 1. AWS Amplify: Create a android project in Android Studio and do amplify init to initialize the amplify project in the AWS account.
 2. AWS IoT Core: Create a thing in AWS IoT Core Policy and a Certificate. Create a IoT thing and assign the policy. Download the certificates and the keys.
 3. AWS IoT Rule: Create a rule that listens to a topic, to which the raspberry pi will send the image data.
 4. Lambda Function: Lamda is used to connect several AWS services.
 5. AWS S3: S3 buckets are used to store the pantry imagesof users. It is also used to store images for labelling.
 6. S3 Cross Region Replication: S3 Cross region replication done to ensure Disaster Recovery
 7. DynamoDB: The tables are configured as part of the amplify project.
 8. AWS SageMaker: It is used for labelling our house pantry images.
 9. AWS Rekognition: A custom labelling model is used to train the model with images labelled from sagemaker. Then the trained model is used to detect ingredients whenever a new image is uploaded to the pantry bucket.
 10. Raspberry pi: Use a SD card to install Raspbian OS onto the raspberry pi and connecte a camera module to it. On the raspberry pi install the aws sdks like boto3 and awsiotsdk, which will be used to create a python script. The python script captures the image, encodes it and sends it to the topic.
