import tensorflow as tf
import numpy as np
import pandas as pd

# Lade die CSV-Datei
data = pd.read_csv('modified_example.csv')

# Konvertiere die Daten in ein Numpy-Array
data = data.to_numpy()

# Trenne die Daten in Input- und Output-Daten
X = data[:, 1:6]
y = data[:, 6]

# Konvertiere die Output-Daten in einen One-Hot-Encoded-Array
y = tf.keras.utils.to_categorical(y, num_classes=2)

# Erstelle das Modell
model = tf.keras.Sequential()
model.add(tf.keras.layers.Dense(16, input_dim=5, activation='relu'))
model.add(tf.keras.layers.Dense(32, activation='relu'))
model.add(tf.keras.layers.Dense(64, activation='relu'))
model.add(tf.keras.layers.Dense(32, activation='relu'))
model.add(tf.keras.layers.Dense(2, activation='softmax'))

# Kompiliere das Modell
model.compile(loss='categorical_crossentropy', optimizer='adam', metrics=['accuracy'])

# Fitte das Modell an die Daten
model.fit(X, y, epochs=100, batch_size=32, validation_split=0.2)

# Speichere das Modell
model.save('model.h5')

# Überprüfe die Vorhersagen auf einige Beispiele
predictions = model.predict(X[:10])

# Konvertiere die Vorhersagen zurück in kategoriale Klassen
predictions = np.argmax(predictions, axis=1)

print(predictions)
