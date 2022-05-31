# HerAi - AI-based Waste Bank

### Project Theme : Environmental Conservation, Disaster Resilience, and Climate Change

### Description :

### Dataset : Please visit our dataset [here](https://drive.google.com/file/d/1EiD0SNOjb9N-5J1-tTOY57jL52MWw2B1/view?usp=sharing). 

<br />

## 1. Machine Learning
In this repo, files contain in `ML` directory is the final architecture that most suitable with our project. We have done several research and development to build this project model. To see in more details, please visit this [repository](https://github.com/samleonnn/HerAi-Model-Development).

Tasks for Machine Learning team:
- [x] Collecting and Cleaning Dataset
- [x] Build Model
- [x] Train and Test Model
- [x] Model Deployment in Android Application
- [x] Test Model in Production

Several references and sources as follows are use in collecting the dataset:
- [Waste Classification data by SASHAANK SEKAR](https://www.kaggle.com/datasets/techsash/waste-classification-data)
- [waste_pictures by 且听风吟](https://www.kaggle.com/datasets/wangziang/waste-pictures)
- [Most Common Recyclable and Non-Recyclable Objects by ASHWIN SHRIVASTAV](https://www.kaggle.com/datasets/ashwinshrivastav/most-common-recyclable-and-nonrecyclable-objects)
- [Garbage Classification (12 classes) by MOSTAFA MOHAMED](https://www.kaggle.com/datasets/mostafaabla/garbage-classification?select=garbage_classification)

As result, our dataset contains:
- `Non-Recyclable` category (label `0`): `7,000` Train images + `2,324` Validation images
- `Organic` category (label `1`): `7,249` Train images + `1,475` Validation images
- `Recyclable` category (label `2`): `7,000` Train images + `2,626` Validation images

The final architecture that most suitable with our project is using [MobileNet](https://arxiv.org/pdf/1704.04861.pdf) transfer learning. Take a look on the following diagram.
<br /><br />
![Machine Learning Architecture Diagram](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/diagram.jpg?raw=true)

We decided to use [Global Average Pooling](https://github.com/christianversloot/machine-learning-articles/blob/main/what-are-max-pooling-average-pooling-global-max-pooling-and-global-average-pooling.md#global-average-pooling) compare to Flatten to reduce the model size and make it more lighter for mobile deployment, but still maintain high accuracy. Besides, using Global Average Pooling is better for classification task because it finds global representative feature from every slice.

We train it for 10 epochs on our dataset and it tooks ~ 3046 seconds or ~ 50.77 minutes. As a results, we achieve `95.34%` train accuracy, `90.04%` validation accuracy, and `93.93%` test accuracy. Please see the file [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/HerAi%20Waste%20Segregation%20Model%20Development.ipynb) to see more details on the process.

We saved the model in Saved Model format with `29.6 mb` file size. Then, we convert it into regular .tflite file without any optimization and gave us `19.2 mb` file size. We are not satisfied yet for the model size since it will be deployed on an Android application. Therefore, we convert it again with optimization on latency and Float16 quantization. Then, we achieve `9.6 mb` file size. Therefore, we agree to use this quantize model.

Before we deploy the model into the Android application directly, we test the .tflite model in a Python script [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/blob/main/ML/testTFLite.py). Please follow the following instruction to use our model in the Python script.
1. Open an image and resize it to `(224, 224)` size.
2. Convert the image into an `numpy.array` with `float32` data type.
3. Divide every value inside the array by 255. This is needed because our model was built using image normalization to reduce the computation of high numeric values.
4. Load the .tflite model using `tensorflow.lite.Interpreter` and pass the model path as the argument.
5. Expand the dimension of image array and use `axis = 0` as the argument.
6. Pass the tensor index and the expanded dimension image into `set_tensor`.
7. Call `invoke()` function to start inference.
8. Get the output in an array of 3. Get the highest value position and match with the label position.

After we test in the Python script and it works well, now it time for us to deploy it in the Android app. We have made a dummy Android app only for image classification using our `wsmodelGAPQuant2.tflite` model. Please visit it [here](https://github.com/heriirianto/Capstone-Project-Bangkit2022/tree/main/ML/WasteSegregation2). The overall deployment steps are the same with the Python script deployment above. Please follow the following instruction to understand more on how to deploy the .tflite model directly in the Android app. Assuming you already have your Android project.
1. Open your Android Studio, Click on `File` > `New` > `Other` > `TensorFlow Lite Model`.
2. You will be prompted a window to select the location of your .tflite file. Please select the location of your model in `Model location` part. Click on `Finish`.
3. Your model now will be located under `app/ml` directory in Android Studio. New tab will also appear contain the .tflite file that we import. It will contain several lines of code to help you use the model. Copy and paste the code in the part of the app to start inference.
4. Take a picture to be predict. 
> Notes: make sure you resize the picture into `(224, 224, 3)` size. Also, to do inference, make sure it is in `ByteBuffer` format, not `Bitmap`. We also need to normalize the image or divide each value of the pixels by 255. Please refer to the `convertBitmapToByteBuffer` function that resize, normalize, and convert it into `ByteBuffer` format. This function make an empty ByteBuffer and copy the new resized and normalized value inside it.
5. Call the `convertBitmapToByteBuffer` function and pass the image in the bitmap format as the argument. Put the result in a variable.
6. Load the new ByteBuffer image in the `inputFeature0.loadBuffer` as the argument.
7. Do the inference in `model.process(inputFeature0)` and obtain the results in `output` variable.
8. Once the inference done, we can get the result in `outputFeature0` variable. To see the the result, we have to convert it into `floatArray`. The biggest value (closest to 1) position will be the result, please refer to the label above.
<br /><br />

tambahin dengan panduan penggunaan aplikasi, task apa saja yang dikerjakan, maupun keterangan lainnya
