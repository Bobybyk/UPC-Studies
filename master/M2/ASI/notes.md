On a besoin de composants pour gérer les dépendances et que ce soit déployable.
Exemple de plateforme web : NodeJS (le runtime)
Déploiement FTP

Exemple web server : Apache (états-unis), NGINX (russie), IIS (microsoft), Lighttpd

composant : conception modulaire permettant de segmenter les fonctionnalités d'un système, on ajoute des fonctionnalités à un morceau de code par l'extérieur sans le modifier.

L'objet sait ce qu'il est (pattern state), pas besoin de l'évaluer, il communique son été. La factorie est la seule class où on évalue les objets qui y sont créés
On crée un objet par état et on utilise le polymorphisme pour appeler la méthode de l'état.