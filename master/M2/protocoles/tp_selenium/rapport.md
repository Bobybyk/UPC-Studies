**Nom :** Le Franc

**Prénom :** Matthieu

**N°étudiant :** 71800858

**Email :** matthieu.le-franc@etu.u-paris.fr

# TP - micro projet Selenium

## Partie 1 : recherche prénom + nom

*tests_assert.side*

J'ai pu aisément effectuer ce premier exercice sans blocages, peu importe la vitesse du test. Un éventuel blocage arrive peut être si le chargement de la page est trop long ? Dû à un problème quelconque entrainant un temps de réponse jugé trop important par sélénium ?

## Partie 2 : recherche avec assertion 

*tests_assert.side*

Partant du moteur de recherche "duckduckgo" j'ai ajouté une assertion pour vérifier la présence d'un élément.
On vérifie si le logo de la page d'accueil (au dessus de la bar de recherche) est présent après l'ouverture de la page avec :
```
assert element present
```
Le résultat de cette commande est toujours ***OK*** car le logo de la page d'accueil est toujours présent.

## Partie 3 : vérification de la présence d'un élément

*tests_assert.side*

Ici on veut s'assurer si en effectuant une recherche avec 2 mots, un troisième mot est présent.
Pour cela on va récupérer la **value** de la **target** considéré, après l'avoir rempli, grâce à la commande :
```
store value
```
Cette valeur est stockée dans la value ***len*** que l'on va passer à la commande :
```
execute script
```
Ce mini script va retourner le nombre de mots de la recherche dans la **value** ***len*** que l'on passe enfin en **target** de la commande :
```
assert
```
La **value** que l'on donne à cette assertion est 3, car on veut vérifier la présence de 3 mots dans la recherche.
Le retour de cette commande est ***Failed: Actual value '2' did not match '3'*** car len vaut 2, ce qui signifie que la recherche ne contient que 2 mots et pas 3.

J'ai utilisé un petit script pour compter le nombre de mots, avant de réaliser l'assertion, car je ne suis pas parvenu à utiliser correctement une expression régulière dans selenium IDE. 

## Partie 4 : comportement unique

*test_UIO_1*

Ce test se trouve dans un autre projet selenium car il semble que la convention soit : un url = un projet selenium.

Nous nous rendons sur un site de prestations web : www.pleex.fr

Il existe sur ce site 6 offres avec pour chacunes d'elles 6 boutons "prendre contact". Chaque bouton amène vers un formulaire au bas de la page qui est ensuite pré-rempli avec un contenu différent en fonction du bouton cliqué.

Voici donc les phases majeures du test :

On clique sur le lien "prendre contact" de la première offre en haut de la page (celle pour un site vitrine).
Le formulaire au bas de la page vient d'être rempli avec un contenu spécifique à l'offre sélectionnée, on exécute maintenant la commande :
```
store value
```
avec comme **target** l'id du formulaire pour récupérer son contenu dans une **value** ***content***

Maintenant, on utilise la commande :
```
assert
```
pour vérifier si ***content*** contient bien la chaîne de caractère unique spécifique à l'offre sélectionnée.

Avec cette séquence d'action, on arrive à un résultat unique (le formulaire prérempli avec un certain contenu) qui n'est pas obtenable autrement qu'en passant par le lien "prendre contact" de l'offre choisie