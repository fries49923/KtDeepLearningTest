import tensorflow as tf
import numpy as np
import os

# y = -3x + 2
x = np.array([-1, 0, 1, 2, 3], dtype=float)
y = np.array([5, 2, -1, -4, -7], dtype=float) 

model = tf.keras.Sequential([
    tf.keras.layers.Dense(units=1, input_shape=[1])
])

model.compile(optimizer='sgd', loss='mean_squared_error')

model.fit(x, y, epochs=500)

print(model.predict([10.0]))


current_dir = os.path.dirname(os.path.abspath(__file__))
save_path = os.path.join(current_dir, "saved_model")
print("Save to:", save_path)
tf.saved_model.save(model, save_path)