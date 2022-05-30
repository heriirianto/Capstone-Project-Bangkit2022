import tensorflow as tf

path = "./ML/wsmodel-24052022-mobilenet+GAP10"

converter = tf.lite.TFLiteConverter.from_saved_model(path)
converter.optimizations = [tf.lite.Optimize.OPTIMIZE_FOR_LATENCY]
converter.target_spec.supported_types = [tf.float16]
tflite_model = converter.convert()

with open('./ML/wsmodelGAPQuant2.tflite', 'wb') as file:
  file.write(tflite_model)