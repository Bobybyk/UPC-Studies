Pour le placement des mots nous avons choisi de :
 - placer tous les mots du fichier par ordre (prioritairement) décroissant
 - alterner entre un placement horizontal et vertical pour éviter que des mots se croisent involontairement.

Pour ce faire, nous trions les mots dans une liste par ordre décroissant.
Ensuite, si l'on peut placer un mot par sa première lettre sur le dernier mot ajouté, nous l'ajoutons.
Sinon, on poursuit les tentatives en allant du plus long mot au plus petit jusqu'à :
 - soit arriver à épuisement des mot disponible
 - soit arriver dans un cas où tous les mots ont été testés et aucun ne peut être placé
