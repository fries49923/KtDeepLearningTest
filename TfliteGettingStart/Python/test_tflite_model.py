import tensorflow as tf
import numpy as np
import os

current_dir = os.path.dirname(os.path.abspath(__file__))

tflite_model_path = os.path.join(current_dir, "model.tflite")

interpreter = tf.lite.Interpreter(model_path=tflite_model_path)
interpreter.allocate_tensors()

input_details = interpreter.get_input_details()
output_details = interpreter.get_output_details()

input_data = np.array([[10.0]], dtype=np.float32)

interpreter.set_tensor(input_details[0]['index'], input_data)
interpreter.invoke()

output_data = interpreter.get_tensor(output_details[0]['index'])
print("Result:", output_data)
