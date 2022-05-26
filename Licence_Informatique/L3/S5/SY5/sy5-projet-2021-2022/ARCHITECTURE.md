# Mode d'emploi:
- Le script `bash create_fifos_and_dirs.sh` crée tous les répertoires nécessaires à la bonne exécution du programme en créant les tubes aux bons endroits et en créant les répertoires nécessaires au stockage des tâches et des fichiers compilés
- La compilation du programme s'effectue via la commande `make`:
	* `make` seul génère saturnd ET cassini
	* `make cassini` génère uniquement cassini
	* `make saturnd` génère uniquement saturnd
	* `make distclean` supprime tous les exécutables générés ainsi que les fichiers de compilation
- `./cassini -h` affiche le omde d'emploi de cassini
	
# 1. Architecture Saturnd:

Le programme **saturnd** se compose essentiellement d'une boucle infinie commençant par un **poll()** surveillant le descripteur de fichier associé au tube de requête et à un tube anonyme créé au prélable.

Lorsque que le client **cassini** envoie une requête sur le tube, le programme sort du poll et effectue le traitement des tâches (voir ci-dessous).

Le daemon peut également être réveillé par la réception du signal **SIGCHLD** lui signalant qu'un de ses processus vient de se terminer. Un **handler** est donc mis en place afin de redéfinir le comportement du processus lors de la réception de ce signal. La réception du signal SIGCHLD provoque donc l'écriture d'un octet sur le tube anonyme afin de tirer le processus de son sommeil et lui permettre de **collecter le statut du fils** ayant terminé.

Le champ timeout de poll() est initialisé au **temps restant avant la prochaine minute**, car une tâche ne peut pas être exécutée à une fréquence plus élevée que toutes les minutes.

Pour des raisons de simplicité de mise en place de l'historique des valeurs de retour des exécutions d'une tâche, **une tâche n'est pas exécutée si sa précédente exécution n'a pas terminée**.

Le stockage des tâches sur le disque s'effectue à l'aide de l'**arborescence suivante**, pour chaque tâche:

```
< id >/

	| -> exec_time 

	| -> stdout

	| -> stderr

	| -> cmd

	| -> times_exit-codes
```

Lors de sa création, chaque tâche est également stockée dans une **liste chaînée** dans la mémoire afin de pouvoir avoir accès aux différentes tâches facilement. On **met à jour cette liste** ainsi que l'architecture disque à chaque création/supression de tâche.

Chaque tâche exécutée est également stockée dans une liste chaînée afin de pouvoir facilement **faire correspondre valeurs de retour et tâche correspondante**. Lorsque que le statut d'un processus terminé est collecté, on retire la tâche de la liste.

# 2. Architecture Cassini: 

Le programme cassini se décompose en deux phases d'exécution.

## Première phase: formatage et envoi de la requête

Dans un premier temps, le programme met en forme la requête dans un buffer en fonction des arguments fournis par l'utilisateur avant d'écrire cette requête sur le tube, préalablement ouvert, **en une seule fois**. Le tube de requête est ensuite fermé.

## Deuxième phase: lecture de la réponse et interprétation:

Le tube de réponse est ensuite ouvert et la réponse envoyée par le daemon est ensuite lue petit à petit en **fonction des champs attendus**. Si nécessaire un affichage est ensuite fourni à l'utilisateur (dans le cas de la création d'une tâche, on affiche l'ID de cette tâche).

Plusieurs structures sont utilisées dans cassini afin de faciliter la manipulation des différentes composantes d'une requêtes, comme:
 
* le type **custom_string** permettant de créer et de manipuler les string tels que définis dans le fichier **protocole.md** 
* le type **commandline** permettant de représenter une ligne de commande avec tous ses argument séparés.