import tensorflow as tf
import PIL
import numpy as np

loadModel = tf.keras.models.load_model('./ML/wsmodel-24052022-mobilenet+GAP10')

loadModel.summary()

imagePath="./ML/image/bottle2.jpg"
img = PIL.Image.open(imagePath)
size = (224, 224)
img = img.resize(size)

img = np.array(img, dtype="float32")
img /= 255.

input = np.array(np.expand_dims(img,0))

print(loadModel.predict(input))
