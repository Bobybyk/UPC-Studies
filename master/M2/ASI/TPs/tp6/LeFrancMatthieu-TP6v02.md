**TP n°6 V n°2**

**Titre du TP :** Servlet Context Listener

**Date :** 11/03/2024

**Nom :** Le Franc

**Prénom :** Matthieu

**N° étudiant :** 71800858

**email :** matthieu.le-franc@etu.u-paris.fr

## Explications

N'ayant pas pleinement compris le TP précédent (tp5) avec les injections, je n'avais pas réalisé la partie avec wildfly. J'ai donc créé un nouveau projet pour le tp de cette semaine où j'ai pu mettre en place wildfly et le ContextListener.

On commence donc par installer JBoss sur eclipse web pour avoir accès à wildfly.

![JBoss](JBoss.png)

Ensuite on crée un nouveau projet web dynamique avec wildfly comme Target runtime.

![wildfly](wildfly.png)
![wildfly2](wildfly2.png)

Maintenant on peut créer un servlet context listener. On crée une nouvelle classe qui implémente notamment ServletContextListener et on implémente quelques méthodes intéressantes.

![listener](listener.png)
![listener_code](listener_code.png)

On peut ensuite créer un Servlet qui va utiliser le context listener.

![servlet](servlet_code.png)

J'ajoute un fichier ``beans.xml`` dans le dossier ``WEB-INF`` pour activer CDI (Context and Dependency Injection) qui permet de gérer les dépendances et que ce soit déployable.

![beans](xml.png)

Maintenant on peut lancer le serveur wildfly. On voit que le serveur se lance bien mais qu'il nous reste à exécuter le script ``add-user.sh`` pour ajouter un utilisateur.

![run](run.png)
![wildfly3](wildfly_page.png)

On exécute donc le script et on ajoute un utilisateur.

![add_user](config_wildfly.png)

Maintenant en relançant le serveur et en allant à l'url ``/archiTP6/MonServlet`` on voit que le context listener a bien été appelé.

![terminal](terminal.png)

![wildfly_servlet](wildfly_Servlet.png)

Voici le diagramme de séquence de l'implémentation du context listener.

![sequence](sequence.png)