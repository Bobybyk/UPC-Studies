# Schéma relationnel : bibliothèque 

* Livre (id, titre, éditeur)
* Auteur (id, nom)
* Auteurs (id-livre, id-auteur)
* Exemplaire Livre (id, rayon, disponibilité, id-livre)
* Client (id, nom, adresse)
* Abonné (id-client, carte-id)
* Emprunt en cours (id, id-client, d & h)
* Emprunt terminé (id, d & h début, d & h fin, id-client, commentaire)