# TP 8

## Exercice 1

**1. Réaliser une implémentation atomique d’une liste pour cette implémentation. Préciser les points de linéarisation**

Voir fichier ``AtomicList.java`` pour l'implémentation atomique de la liste. Les points de linéarisation sont dans les méthodes **add** et **remove** lorsqu'on fait un **compareAndSet**.

**2. Quelle propriété de progression ont add? remove? contains ?**

La propriété de progression de **add**, **remove** et **contains** est qu'on a la garantie d'achèvement. Même dans un environnement où plusieurs threads accèdent et modifient simultanément la structure de données, chaque opération est garantie de s'exécuter et de terminer. Cela est dû à l'utilisation de la méthode **compareAndSet** qui garantit l'atomicité et qu'on évitera l'utilisation de conditions d'attente.

**Le reste des réponses avec les implémentations se trouve dans les différents fichiers .java. Pour lancer l'exécution, la fonction main() se trouve dans ``Launch.java`` ainsi que les différents tests et comparaisons**