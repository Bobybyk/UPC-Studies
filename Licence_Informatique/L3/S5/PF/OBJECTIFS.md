# Objectifs du cours PF5 Programmation Fonctionnelle

Ce cours vise à vous familiariser avec l'approche fonctionnelle de la
programmation, qui est une des grandes approches de la programmation
avec la programmation procédurale (dite aussi impérative) et la
programmation orientée objet.

Cette approche se caractérise par :

- l'usage intensif de fonctions récursives travaillant sur des **structures de données
  inductives** (listes, arbres, ...) ;

- une méthodologie qui consiste **à contrôler voire à supprimer toutes
  formes d'état** dans les programmes pour simplifier le raisonnement
  et augmenter le parallélisme ;

- une décomposition centrée sur les schémas de calcul (itération,
  accumulation, filtrage, composition, réduction, ...) à travers
  l'écriture de **fonctions d'ordre supérieur**

Nous suivrons particulièrement l'approche *typée* de la programmation
fonctionnelle, c'est-à-dire que l'on ne considérera que des programmes
fonctionnels qui ont été validés par typage avant leur exécution. Cette
validation garantit que le programme ne peut pas produire d'erreur
d'exécution (accès à une mémoire désallouée, incompatibilité entre
les opérations et leurs arguments, ...).

Cette approche répond à des problèmes de l'ingénierie du logiciel:

- d'abord, le typage garantit la **sûreté de l'exécution** ;

- ensuite, le haut niveau d'abstraction des programmes fonctionnels
  simplifient la **vérification de la correction des programmes** car
  ils sont très proches de leur spécification ;

- par ailleurs, l'approche fonctionnelle de la programmation améliore la
  **robustesse au changement** en favorisant l'écriture de composants
  logiciels généraux et composables ;

- enfin, l'usage de composants logiciels sans état permet de dupliquer
  sans effort les unités de calcul et de stockage et ainsi favoriser
  un **passage à l'échelle** des systèmes informatiques.

La programmation fonctionnelle existe depuis les débuts de l'informatique
mais elle connaît un essor important depuis quelques années.

La plupart des mécanismes de la programmation fonctionnelle sont
aujourd'hui intégrés dans les langages de programmation les plus
utilisés : Python, Java, Javascript... mais il manque encore quelques
mécanismes importants pour l'utiliser pleinement. C'est pourquoi on
étudiera dans ce cours le langage de programmation OCaml qui donne
accès à l'ensemble des mécanismes de la programmation fonctionnelle.
