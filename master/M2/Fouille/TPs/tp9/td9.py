import tensorflow as tf

"""
Voici étape par étape les différentes implémentations du modèle que j'ai suivi dans le tuto tensorflow
"""

model1 = tf.keras.Sequential(
  [
      tf.keras.layers.Input(shape=(28*28,)),
      tf.keras.layers.Dense(200, activation='relu'),
      tf.keras.layers.Dense(60, activation='relu'),
      tf.keras.layers.Dense(10, activation='softmax')
  ])

model1.compile(optimizer='adam',
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model1.summary()

# Ajout d'une couche de dropout pour la régularisation du modèle (taux de dropout de 25%) pour éviter l'overfitting
model2 = tf.keras.Sequential(
  [
      tf.keras.layers.Input(shape=(28*28,)),
      tf.keras.layers.Dense(200, activation='relu'),
      tf.keras.layers.Dropout(0.25),
      tf.keras.layers.Dense(100, activation='relu'),
      tf.keras.layers.Dropout(0.25),
      tf.keras.layers.Dense(60, activation='relu'),
      tf.keras.layers.Dropout(0.25),
      tf.keras.layers.Dense(10, activation='softmax')
  ])

model2.compile(optimizer=tf.keras.optimizers.Adam(lr=0.01),
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Implémentation d'un réseau de neurones convolutifs
model3 = tf.keras.Sequential(
  [
      tf.keras.layers.Reshape(input_shape=(28*28,), target_shape=(28, 28, 1)),

      # Première couche convolutive avec activation ReLU et padding 'same' pour garder la même taille de l'image
      tf.keras.layers.Conv2D(kernel_size=3, filters=12, activation='relu', padding='same'),
      tf.keras.layers.Conv2D(kernel_size=6, filters=24, activation='relu', padding='same', strides=2),
      tf.keras.layers.Conv2D(kernel_size=6, filters=32, activation='relu', padding='same', strides=2),
      tf.keras.layers.Flatten(),
      tf.keras.layers.Dense(200, activation='relu'),
      tf.keras.layers.Dense(10, activation='softmax')
  ])

model3.compile(optimizer=tf.keras.optimizers.Adam(lr=0.01),
              loss='categorical_crossentropy',
              metrics=['accuracy'])

model3.summary()

# Définition du modèle
model4 = tf.keras.Sequential(
  [
      # Reshape pour transformer l'entrée en image 28x28 avec un seul canal
      tf.keras.layers.Reshape(input_shape=(28*28,), target_shape=(28, 28, 1)),
      
      # Première couche convolutive avec activation ReLU
      tf.keras.layers.Conv2D(kernel_size=3, filters=12, use_bias=False, padding='same'),
      tf.keras.layers.BatchNormalization(center=True, scale=False),
      tf.keras.layers.Activation('relu'),
      
      # Deuxième couche convolutive avec activation ReLU et sous-échantillonnage (strides=2)
      tf.keras.layers.Conv2D(kernel_size=6, filters=24, use_bias=False, padding='same', strides=2),
      tf.keras.layers.BatchNormalization(center=True, scale=False),
      tf.keras.layers.Activation('relu'),
      
      # Troisième couche convolutive avec activation ReLU et sous-échantillonnage (strides=2)
      tf.keras.layers.Conv2D(kernel_size=6, filters=32, use_bias=False, padding='same', strides=2),
      tf.keras.layers.BatchNormalization(center=True, scale=False),
      tf.keras.layers.Activation('relu'),
      
       # Aplatir les données avant de les passer à la couche dense
      tf.keras.layers.Flatten(),
      
      # Couche dense avec activation ReLU
      tf.keras.layers.Dense(200, use_bias=False),
      tf.keras.layers.BatchNormalization(center=True, scale=False),
      tf.keras.layers.Activation('relu'),
      
      # Ajout d'une couche de dropout pour la régularisation
      tf.keras.layers.Dropout(0.3),

      # Couche de sortie avec activation softmax pour la classification
      tf.keras.layers.Dense(10, activation='softmax')
  ])

# Compilation du modèle avec l'optimiseur Adam, la fonction de perte categorical_crossentropy et la métrique accuracy
model4.compile(optimizer=tf.keras.optimizers.Adam(lr=0.01),
              loss='categorical_crossentropy',
              metrics=['accuracy'])

# Affichage du résumé du modèle
model4.summary()