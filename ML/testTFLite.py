import tensorflow as tf
import PIL
import numpy as np

TFLiteModel = "./ML/wsmodelGAPQuant2.tflite"

interpreter = tf.lite.Interpreter(model_path = TFLiteModel)
interpreter.allocate_tensors() 

inputDetails = interpreter.get_input_details()
outputDetails = interpreter.get_output_details()

imagePath = "./ML/image/bottle.jpg"
img = PIL.Image.open(imagePath)
size = (224, 224)
img = img.resize(size)

img = np.array(img, dtype="float32")
img /= 255.

inputShape = inputDetails[0]['shape']
inputTensor= np.array(np.expand_dims(img,0))
index = inputDetails[0]["index"]
interpreter.set_tensor(index, inputTensor)
interpreter.invoke()
output = interpreter.get_tensor(outputDetails[0]['index'])
pred = np.squeeze(output)

print(pred)