20210205154314
#l2
#ol4
#logic

# OL4 CMTD 2 : Relations binaires et réécriture

## Relations binaires

**Relation binaire** : entre 2 éléments

**Id **: identité => un élément sur lui-même

**R°S** (R rond S) : {(d1, d3) tels que il existe d2 avec (d1,d2) dans S et (d2,d3) dans R}
=> On lit à partir de la droite

Une relation est :
- **réflexive** si Id est dans R 
        - si pour chaque élément il y a une liaison vers lui-même
- **transitive** si R°R est dans R 
        - si on fait 2 fois la relation, on reste dans R
        - si on peut aller de 1 à 2 et de 2 à 3, alors on peut aller de 1 à 3 directement

**Clotûre transitive R+** d'une relation = la relation qu'on obtient en composant R avec lui-même un nombre fini de fois
- R_0 = R
- R_ n+1 = R_n U (R_n ° R_n)
 R+ est **transitive** car c'est la plus petite relation transitive qui contienne R

**Clotûre reflexive et transitive Rétoile**
- R_0 = R U Id (pour que ce soit transitif)
- R_n+1 = R_n U (R_n ° R_n)
    - rq : comme on ajoute Id au début, on fait du surplace. Donc on peut simplifier en R_n+1 = R_n ° R_n

**Terminaison**
Les relations binaires représentent un **processus de calcul** : on écrit d -> d' pour dire (d, d') dans D
D termine ssi toute suite d0 -> d1 -> … est **finie**


