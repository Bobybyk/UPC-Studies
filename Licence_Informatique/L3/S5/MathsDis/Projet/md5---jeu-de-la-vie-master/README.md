# Mathématiques discrètes
``L3 informatique - 2021``
# Projet : Jeu de la vie unidimensionnel

```
Consignes :
Le but du projet est de présenter une application dans laquelle les mathé-
matiques discrètes jouent un rôle fondamental.
Le format du rendu attendu sera celui d’un billet de blog présentant le
contexte, puis un apport personnel reposant sur les notions de mathématiques
discrètes vues en cours (exercice, quiz, ou programme processing). Une at-
tention particulière doit être apportée à la clarté de la présentation. Celle-ci
s’adresse à l’ensemble du groupe donc nécessite un effort de préparation et de
pédagogie.
Vos productions seront relues par d’autres étudiants. La note finale prendra
en compte la qualité de cette relecture.
```
```
Contenu Le sujet détaille quelques points à développer mais ceux-ci sont
proposés comme point de départ de votre travail. Vous êtes encouragés à dé-
velopper d’autres pistes en lien avec les mathématiques discrètes. De même,
la bibliographie conseillée est un point de départ. Vous pouvez vous appuyer
sur d’autres sources sur lesquelles vous porterez un œil critique et que vous
prendrez soin de citer correctement. Vous serez notés sur la compréhension du
sujet et le contenu du billet.
```
```
Charte de bonne conduite Lisez attentivement la charte de bonne conduite.
Portez une attention particulière à citer toutes vos sources, y compris les
images que vous utiliserez.
```
```
Calendrier Consultez la page Moodle du cours pour les dates des principales
étapes du projet.
```
# Bref descriptif du sujet

On considère une variante simple dujeu de la viede Conway.
Lesconfigurations du jeu sont les séquences binaires circulaires, dont on appelle les 1
cellules vivanteset les 0 cellules mortes. Vu que l’on considère des séquences circulaires,
chaque cellule possèdeexactementdeux cellules voisines.
On se donne la règle d’évolution suivante :

- une cellule vivante meurt si et seulement si au moins une de ses deux voisine est
    vivante.
- une cellule morte nait si et seulement si exactement une de ses deux voisine est
    vivante.

# Bibliographie conseillée

- https://fr.wikipedia.org/wiki/Automate_cellulaire

- https://fr.wikipedia.org/wiki/Jeu_de_la_vie

# Pistes de développement

- Écrire un programme qui, à partir d’une configuration initiale donnée, engendre
    les configurations suivantes, jusqu’à la détection d’un cycle.
- Caractériser les configurationsstables, celles qui ne sont pas modifiées par applica-
    tion de la règle d’évolution.
- La règle d’évolution peut être décrite par la fonctione:{ 0 , 1 }^3 →{ 0 , 1 }qui donne
    l’état suivant de la cellule centrale ( 0 pour morte, 1 pour vivante) en fonction des
    états précedents de cette cellule et de ses deux voisines. Par exemplee(0, 1 ,1) = 0
    ete(0, 0 ,1) = 1. Il existe donc 28 variantes possibles de ce jeu de la vie unidimen-
    sionnel, que vous pouvez explorer.
- Une configuration est catastrophique si la configuration qui la suit ne contient
    que des cellules mortes. Trouver, en fonction den, le nombre de configurations
    catastrophiques de taille n.