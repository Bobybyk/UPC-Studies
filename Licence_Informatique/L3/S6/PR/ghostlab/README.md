# GHOSTLAB

**Lien du dépôt gitlab :** https://gitlab.com/SirHenryAllen/ghostlab.git

## Répartition du travail

### Hugo :
* **Côté Serveur:**
    * Parsing des requêtes provenant du client
    * Traitement des requêtes 
    * Formatage des réponses pour le client
    * Envoi des réponses au client
    * Gestion des structures de données du serveur (verrous, linked list etc...)
    * Architecture serveur:
      * Découpage de l'exécution en différentes phases chacune englobée dans un thread
      * Arborescence du projet
* **Côté Client:**
  * Détection des murs pour mise à jour de l'interface graphique

### Matthieu :

* **Partie client :**
    
    * Réception et parsing des commandes venant du serveur
    * Traitement des commandes et stockage des informations venant du serveur
    * Parsing des commandes client (console et interface graphique)
    * Formatage et envoi des commandes au serveur
    * Création de l'interface graphique
        * page de connexion
        * page de gestion des parties
        * interface de jeu
    * Système de stockage de l'historique des parties jouées (commande ``historical``)
    * Système de debogage

* **Partie serveur :**
    * Conversion d'octets en little-endian (pour envoie de **h** et **w**)

### Bastian :

* **Partie serveur :**

    * Création des labyrinthes (fichiers .lab)
    * parsing des fichiers .lab et création d'un labyrinthe dans la structure game
    * réception et traitement de la commande ``GLIS?`` et envoi de la réponse au client

---

## Compiler le projet

### Serveur

Lancer la commande `make` depuis le répertoire `src/main/c/server` 

---

### Client

Lancer le script `compile.sh` contenu dans le répertoire `src` depuis ce même répertoire

## Executer le projet

### Serveur

Exécuter le fichier `server` généré lors de la compilation avec en argument le port désiré.


**Exemple :**

Pour lancer le serveur en écoute sur le port 12345:

```bash
./server 12345
```


### Client 

Exécuter le script `launch.sh` contenu dans le répertoire `src` (depuis ce même répertoire).

OU

Exécuter la commande suivante: `java main.java.client.Client` (depuis le répertoire `src`).

Les arguments suivants sont optionnels et peuvent par la suite être renseignés dans l'interface graphique:

- IP: L'IP du serveur auquel se connecter

- Port: Le port du serveur a utiliser

- Pseudo: Le pseudo a utiliser dans une partie. Ce pseudo doit faire exactement 8 caractères


**Exemple :**

```java
java main.java.client.Client ghostlab-server.com 12345 USERNAME
```

```bash
./launch.sh
```

**Pour supprimer les .class (depuis src):**

```bash
./remove_class.sh
```

## Comment utiliser le client 


### Partie utilisateur 

Au lancement, si l'utilisateur n'a pas précisé d'informations de connexion, il est invité à entrer les informations du serveur.

Une fois connecté, le volet `Game Manager` devient accessible et permet de:

- créer une partie
- rejoindre une partie
- lister les parties existantes et les informations relatives à celles-ci
- quitter la partie dans laquelle se trouve le client
- démarrer la partie

Une fois la partie lancée, le volet `game` permet de se déplacer dans la labyrinthe en ayant un retour visuel de ce labyrinthe. Pour une visualisation optimale, penser à agrandir la fenêtre.

Si le joueur rencontre un mur lors de ses déplacements, celui-ci est automatiquement ajouté sur la carte du labyrinthe.

Lorsque les fantômes se déplacent, seul le dernier fantôme à s'être déplacé est affiché sur la carte.

### Partie développeur

* Nous avons créé un système de débogage permettant d'activer ou de désactiver certains retours.
* Nous avons créé des commandes permettant d'effectuer des actions côté client, indépendament du serveur (help, kill, killclient...)

Il existe des commandes pour effectuer ces actions depuis le programme

**Pour plus d'information :** utiliser la commande **help** (ou le bouton **aide**) dans le programme 

## Architecture du serveur 


Le serveur attend en boucle des connexions et délègue le traitement du protocole à un thread.
Au cours des interactions client/serveur, plusieurs threads se succèdent dans le traitement des requêtes du client: chaque thread représentant une phase de la durée de vie d'une partie.
Les différentes phases sont les suivantes:

1. le client vient de se connecter
2. le client a créé ou rejoint une partie
3. la partie a été lancée
4. la partie est terminée 

Chaque partie possède:

- son propre verrou pour empêcher les modifications concurrentes sur la liste des joueurs.
- son propre socket UDP utilisé pour multi-caster et envoyer les messages privés

L'IP de multi-diffusion est unique au serveur, seul le port change pour chaque partie créée.

Les labyrinthes sont récupérés depuis des fichiers respectant les règles suivantes:

- Une première ligne contenant deux entiers `i` et `j,` `i  représentant la largeur et `j` la hauteur
- `j` lignes de chacune `i` colonnes composées de `1` ou de `0`, `1` représentant un mur et `0` un chemin
- Une ligne vide





