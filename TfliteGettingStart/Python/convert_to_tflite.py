import tensorflow as tf
import os

current_dir = os.path.dirname(os.path.abspath(__file__))
saved_model_dir = os.path.join(current_dir, "saved_model")
tflite_model_path = os.path.join(current_dir, "model.tflite")

converter = tf.lite.TFLiteConverter.from_saved_model(saved_model_dir)
tflite_model = converter.convert()

with open(tflite_model_path, "wb") as f:
    f.write(tflite_model)

print("Done")