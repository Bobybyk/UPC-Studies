**TP n°4 V n°1**

**Titre du TP :** Virtual Machine Apache Tomcat Servelet

**Date :** 22/02/2024

**Nom :** Le Franc

**Prénom :** Matthieu

**N° étudiant :** 71800858

**email :** matthieu.le-franc@etu.u-paris.fr

## Explications

J'ai réutilisé la configuration de la VM des précédents TPs.

Pour réaliser ce TP, j'ai utilisé l'IDE eclipse web car il permet de créer un projet de type servelet, simplifiant la réalisation des différentes étapes ainsi que le déploiement. J'ai donc commencé par créer un projet de type "Dynamic Web Project" avec comme environement de runtime "Apache Tomcat v9.0" (version que j'ai précédemment installée sur ma VM).

![dynamic_web](dynamic_web.png)

![tomcat](tomcat.png)

Maintenant le projet initialisé, on peut créer via eclipse un servelet (new -> servelet)

![hello](Hello.png)

Du code de base est généré automatiquement à la création, je l'ai simplement modifié pour afficher "Hello World" suivi de mon nom et prénom dans la page web.

![code](code.png)

En lançant le servelet via eclipse (run as -> run on server) on peut voir un premier résultat dans le navigateur web.

![run_eclipse](run_eclipse.png)
![web](web_eclipse.png)

Maintenant pour mettre en ligne le servelet (en production), j'ai créé un fichier .war (web archive) en exportant le projet (export -> war file) et je l'ai déposé dans le dossier webapps de tomcat.

![war](war.png)

Maintenant on peut déployer tout avec ``catalina.sh run``

![run_prod](run_prod.png)

Voici l'affichage de la page web depuis la machine hote (j'avais configuré cette redirection de port dans les TPs précédents)

![web_prod](web_prod.png)

Maintenant, regardons les pages gestionnaire et état du serveur. Pour pouvoir y accèder, j'ai ajouté un utilisateur avec le role **manager-gui** dans le fichier tomcat-users.xml. On peut bien voir, sur la page **manager** par exemple, que le servelet est bien déployé (archiTP4).

![gestionnaire](gestionnaire.png)
![etat](etat.png)