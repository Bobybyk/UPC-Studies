*Matthieu Le Franc : 71800858*

*Hugo Jacotot : 71802786*

# TP 8

## Exercice 1

**1. Réaliser une implémentation atomique d’une liste pour cette implémentation. Préciser les points de linéarisation**

Voir fichier ``AtomicList.java`` pour l'implémentation atomique de la liste. Les points de linéarisation sont dans les méthodes **add** et **remove** lorsqu'on fait un **compareAndSet**.

**2. Quelle propriété de progression ont add? remove? contains ?**

La propriété de progression de **add**, **remove** et **contains** est qu'on a la garantie d'achèvement. Même dans un environnement où plusieurs threads accèdent et modifient simultanément la structure de données, chaque opération est garantie de s'exécuter et de terminer. Cela est dû à l'utilisation de la méthode **compareAndSet** qui garantit l'atomicité et qu'on évitera l'utilisation de conditions d'attente.

**3. Réutiliser le programme du TP précédent qui lancent 3 threads et les threads ajoutent et enlèvent des éléments avec cette implémentation.**

Voir fichier ``Launch.java`` et méthode **run** de ``ThreadSet``.

## Exercice 2

**Tester et comparer les 3 implémentations (gros grain, grain fin et CAS) en supposant différents pourcentages entre les appels de contains, add et remove.**

Voir fichier ``Launch.java``, output après exécution :

```
-----EXERCICE 2 : TEST AND COMPARE-------

résultat add true, id:3
résultat add false, id:3
résultat add true, id:4
résultat add false, id:4
résultat add true, id:5
résultat add false, id:5
résultat add true, id:6
résultat add true, id:7
résultat contains false, id:3
résultat add true, id:8
résultat add true, id:9
résultat add true, id:10
résultat contains true, id:4
résultat add true, id:11
résultat add true, id:12
résultat contains true, id:5
résultat add false, id:6
résultat add true, id:7
résultat remove true, id:3
résultat add true, id:8
résultat add true, id:9
résultat add true, id:10
résultat remove true, id:4
résultat add true, id:11
résultat add true, id:12
résultat remove true, id:5
résultat contains true, id:6
résultat contains true, id:7
résultat contains false, id:8
résultat contains true, id:9
résultat contains false, id:10
résultat contains true, id:11
résultat contains false, id:12
résultat remove true, id:6
résultat remove true, id:7
résultat remove true, id:8
résultat remove true, id:9
résultat remove true, id:10
résultat remove true, id:11
résultat remove true, id:12
6 ms
```