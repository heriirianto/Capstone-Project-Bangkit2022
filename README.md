# HerAi - AI-based Waste Bank

<img src="https://user-images.githubusercontent.com/67790713/173225290-230cf483-28c4-4317-94ed-dab8a0ca1ec3.png" alt="HerAI Logo" style="display: inline-block; margin: 0 auto; max-width: 500px">

### Project Theme: Environmental Conservation, Disaster Resilience, and Climate Change

### Description:
#### HerAi is not just an ordinary waste bank. We are a waste bank that give a new knowledge to increase Indonesian awareness on waste segregation. We create an Android application that allows user to take a picture, predict the waste image and get the result between Organic, Recyclable, or Non-Recyclable. If it is a Recyclable waste, we offer user to sell their waste through our app. Otherwise, we ask users to seperate the waste between Organic and Non-Recyclable. Our goal is to reach SDG number 12 on Responsible Consumption and Production in Indonesia. Please support us to achieve this goal and bring back the real meaning behind a waste bank!

### Dataset: Please download our dataset [here](https://drive.google.com/file/d/1EiD0SNOjb9N-5J1-tTOY57jL52MWw2B1/view?usp=sharing). 

<br />

## Team Member of C22-PC414

| Name                           | Student ID | Learning Path                |
|--------------------------------|------------|------------------------------|
| Ilham Gunadi                   | A2263F2282 | Mobile Development (Android) |
| Muhammad Naufal Habibie Dwihar | C7006G0569 | Cloud Computing              |
| Heri Irianto                   | C7120P1516 | Cloud Computing              |
| Wulan Anjenita Kurniati        | M2006F0470 | Machine Learning             |
| Muhammad Alfares               | M2308F2636 | Machine Learning             |
| Samuel                         | M7306G2616 | Machine Learning             |

<sub>*Based on student ID order</sub>
<br />

## 1. Machine Learning
In this repository, files contain in the `ML` directory are the final architecture that is most suitable for our project. We have done some research and development to build this project model. To see more details on model development, please visit this [repository](https://github.com/samleonnn/HerAi-Model-Development).

Tasks for the Machine Learning team:
- [x] Collecting and Cleaning Dataset
- [x] Build Model
- [x] Train and Test Model
- [x] Model Deployment in Android Application
- [x] Test Model in Production

Several resources as follows are used in making our dataset:
- [Waste Classification data by SASHAANK SEKAR](https://www.kaggle.com/datasets/techsash/waste-classification-data)
- [waste_pictures by 且听风吟](https://www.kaggle.com/datasets/wangziang/waste-pictures)
- [Most Common Recyclable and Non-Recyclable Objects by ASHWIN SHRIVASTAV](https://www.kaggle.com/datasets/ashwinshrivastav/most-common-recyclable-and-nonrecyclable-objects)
- [Garbage Classification (12 classes) by MOSTAFA MOHAMED](https://www.kaggle.com/datasets/mostafaabla/garbage-classification?select=garbage_classification)

As result, our dataset contains:
- `Non-Recyclable` category (label `0`): `7,000` Train images + `2,324` Validation images
- `Organic` category (label `1`): `7,249` Train images + `1,475` Validation images
- `Recyclable` category (label `2`): `7,000` Train images + `2,624` Validation images

Total: `21,249` Train images and `6,423` Validation images

The final architecture that is most suitable for our project is using [MobileNet](https://arxiv.org/pdf/1704.04861.pdf) transfer learning. Take a look at the following diagram.
<br /><br />
![Machine Learning Architecture Diagram](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/diagram.jpg?raw=true)

We decided to use [Global Average Pooling](https://github.com/christianversloot/machine-learning-articles/blob/main/what-are-max-pooling-average-pooling-global-max-pooling-and-global-average-pooling.md#global-average-pooling) compare to Flatten to reduce the model size and make it lighter for mobile deployment, but still, maintain high accuracy. Besides, using Global Average Pooling is better for classification tasks because it finds global representative features from every slice.

We train it for 10 epochs on our dataset and it took ~3046 seconds or ~50.77 minutes. As a result, we achieve `95.34%` train accuracy, `90.04%` validation accuracy, and `93.93%` test accuracy. Please see the file [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/HerAi%20Waste%20Segregation%20Model%20Development.ipynb) to see more details on the process.

We saved the model in SavedModel format with a `33.4 MB` file size. Then, we convert it into a regular TFLite file without any optimization and gave us a `18.7 MB` file size. We are not satisfied yet with the model size since it will be deployed on an Android application. Therefore, we convert it again with optimization on latency and Float16 quantization. Then, we achieve a `9.37 MB` file size. Therefore, we agree to use this quantized TFLite model.

Before we deploy the model into the Android application directly, we test the TFLite model in a Python script [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/testTFLite.py). Please follow the following instruction to use our model in the Python script.
1. Open an image and resize it to `(224, 224)` size.
2. Convert the image into a `numpy.array` with `float32` data type.
3. Divide every value inside the array by 255. This is needed because our model was built using image normalization to reduce the computation of high numeric values.
4. Load the TFLite model using `tensorflow.lite.Interpreter` and pass the model path as the argument.
5. Expand the dimension of the image array and use `axis = 0` as the argument.
6. Pass the tensor index and the expanded dimension image into `set_tensor`.
7. Call `invoke()` function to start inference.
8. Get the output in an array of 3. Get the highest value position and match it with the label position.

After we test in the Python script and it works well, now it is time for us to deploy it in the Android app. We have made a dummy Android app only for image classification using our `wsmodelGAPQuant2.tflite` model. Please visit it [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/tree/main/ML/WasteSegregation2). The overall deployment steps are the same as the Python script deployment above. Please follow the following instruction to understand more about how to deploy the TFLite model directly in the Android app. Assuming you already have your Android project.
1. Open your Android Studio, Click on `File` > `New` > `Other` > `TensorFlow Lite Model`.
2. You will be prompted by a window to select the location of your TFLite file. Please select the location of your model in the `Model location` part. Click on `Finish`.
3. Your model now will be located under the `app/ml` directory in Android Studio. A new tab will also appear to contain the TFLite file that we import. It will contain several lines of code to help you use the model. Copy and paste the code into the part of the app to start inference.
4. Take a picture to be predicted in Bitmap format. 
> Notes: make sure you resize the picture into `(224, 224, 3)` size. Also, to do inference, make sure it is in `ByteBuffer` format, not `Bitmap`. We also need to normalize the image or divide each value of the pixels by 255. Please refer to the `convertBitmapToByteBuffer` function that resizes, normalizes, and converts it into `ByteBuffer` format. This function makes an empty ByteBuffer and copy the new resized and normalized value inside it.
5. Call the `convertBitmapToByteBuffer` function and pass the image in the bitmap format as the argument. Put the result in a variable.
6. Load the new ByteBuffer image in the `inputFeature0.loadBuffer` as the argument.
7. Do the inference in the `model.process(inputFeature0)` and obtain the results in the `output` variable.
8. Once the inference is done, we can get the result in the `outputFeature0` variable. To see the result, we have to convert it into `floatArray`. The biggest value (closest to 1) position will be the result, please refer to the label above.
<br /><br />


## 2. Cloud Computing

Files contained in CC folder in this repository were one of our CC part implementation that will provide the back-end, cloud resource-connection, database, API, and other required items needed for this project. 

Task for the Cloud Computing team:

1. Create [GitHub Repository](https://github.com/heriirianto/Capstone-Project-Bangkit2022) and [Google Drive Storage](https://drive.google.com/drive/folders/1iT7KNqGtBp7iDeqWLpvp57qZw3tGHUhO?usp=sharing) for project development

2. Designing Database Model
    <br>
    ![HerAi Database Design](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/CC/readme_CC_assets/DB_Design.jpg?raw=true)

    This database design were created based on the project plan and android app design. It consists of 9 tables with their own attributes. After this design is created, we create SQL queries on local dummy database and test it. Make sure all the details, keys or attributes were correct, which then we will save the query in an [.sql](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/CC/DB_CREATE.sql) file so it's ready to be run on our Cloud Database later.  

3. Design and create API for the Android app

4. Configure Firebase Authentication System to be use on the app  

    Most apps need to know the identity of a user. Knowing a user's identity allows an app to securely save user data in the cloud and provide the same personalized experience across all of the user's devices. In our application we authenticate the users with Firebase Authentication using their Google Accounts. How we built it? Click [here](https://firebase.google.com/docs/auth/android/google-signin) to see the step. We choose Firebase Authentication because Firebase Authentication provides backend services, easy-to-use SDKs, and ready-made UI libraries to authenticate users to the app. Besides that it supports authentication using passwords, phone numbers, popular federated identity providers like Google, Facebook and Twitter, and more.
       
5. Create, config, and manage Compute Instance and Cloud Database Storage

We knew that Google Cloud Platform (GCP) provides 

6. Connect both Instances
7. Clone GitHub Repository into our Compute Instance
8. API testing on local and then on running online server (Compute Instance) 
<br /><br />


## 3. Android Development
### Clone Repository
1. Download code from branch "main" **OR**
2. Clone Repository. Click on dropdown Code and **copy** HTTPS link <br/>
```
git clone https://github.com/heriirianto/Capstone-Project-Bangkit2022.git
```

### Featured Technologies
* [Kotlin](kotlinlang.org): Kotlin is Modern, concise and safe programming language. Easy to pick up, so you can create powerful applications immediately.
* [TensorFlow Lite](https://www.tensorflow.org/lite): TensorFlow Lite is an open source deep learning framework for on-device inference.
* [CameraX](https://developer.android.com/training/camerax): CameraX is a Jetpack library, built to help make camera app development easier.
* [Jetpack Compose](https://developer.android.com/jetpack/compose): Jetpack Compose is Android's modern toolkit for building native UI. It simplifies and accelerates UI development on Android.
* [Retrofit](square.github.io): Retrofit is a type-safe HTTP client for Android and Java.
* [Firebase](https://firebase.google.com/): Firebase is a Backend-as-a-Service (BaaS) app development platform that provides hosted backend services such as a realtime database, cloud storage, authentication, crash reporting, machine learning, remote configuration, and hosting for your static files.

### Task for the Mobile Development Team
1. Designing UI/UX prototype of the application,
2. Implementing UI/UX design to Android Studio using Kotlin,
3. Configure Firebase to Android Studio,
4. Connect login page to Firebase,
5. Integrate CameraX library to camera page,
6. Integrate machine learning model (TFLite) to Android app,
7. Implementing retrofit library to connect with API, and
8. Build APK.

### Screen Shots
![screenshots](https://user-images.githubusercontent.com/67790713/173224939-af8f8584-b3d4-40df-8d56-94636116fd19.png)

<br /><br />
