Quelques faux-amis du débutant OCaml
====================================

Voici quelques fragments de code OCaml qui ne sont pas forcément faux
en soi. Ils peuvent même être acceptés par OCaml comme bien typés, au
moins dans certains contextes. Mais soit leur type peut surprendre,
soit c'est l'évaluation qui pose problème. Méfiance !

Pour essayer ces fragments : https://sketch.sh/s/lHst8UznxiTfmsXHzn3oZ5/

## Double application

Attention avec:
```ocaml
f (a b)
```

Pour essayer ce fragment en une phrase complète : `fun f a b -> f (a b)`.
Quel type vous répond OCaml ? Non `f (a b)` n'est pas une fonction `f`
appliquée à deux arguments `a` et `b`. Ceci serait `f (a) (b)` en général,
ou ici `f a b` (pour des variables `a` et `b`, pas besoin de parenthèses
autour). En tout cas `f (a b)` n'est possible que si `a` est aussi une fonction. 

C'est plus flagrant sur un exemple concret : `max (1 2)` est illégal car `1`
n'est pas une fonction. Ici il fallait écrire `max 1 2` ou encore `max (1) (2)`.

Note : on peut aussi rencontrer `f (a,b)` lorsque `f` est une fonction
recevant une paire (p.ex. `fst (1,2)`) mais c'est plus rare en OCaml.

Note : en changeant un peu les lettres, `fun f a b -> f (a b)` peut aussi
s'écrire `fun f g x -> f (g x)`. C'est la composition des fonctions `f` et `g`.


## Application et nombres négatifs

Attention avec:
```ocaml
f -3
```

Pour essayer ce fragment en une phrase complète : `fun f -> f -3`.
Son type ? `int -> int`. Ceci montre que l'on n'est pas ici en train d'appliquer
la fonction `f` à `-3`, mais de faire la soustraction entière `(f)-(3)`.

A comparer à `f (-3)`, que l'on peut essayer en une phrase complète `fun f -> f (-3)`,
et qui est bien l'application.

Sur un exemple concret : `succ -3` est illégal car `succ` n'est pas un entier,
par contre `succ (-3)` est bien le successeur de `-3`, c'est-à-dire `-2`.

Bref, attention aux constantes négatives, on doit souvent les parenthéser.


## Comparaisons

Attention avec:
```ocaml
a == b
```

Pour essayer ce fragment en une phrase complète : `fun a b -> a == b`.
Cela permet de voir que comme les autres comparaisons d'OCaml, `==`
est *polymorphe* : son type est `'a -> 'a -> bool`. Mais même si `==`
est le test d'égalité usuel dans beaucoup de langages de
programmation, en OCaml il a un rôle très particulier (et rarement
utile), ce qu'on appelle l'égalité *physique* : `a == b` sera vrai si
`a` et `b` sont exactement au même emplacement mémoire. On parle aussi
de comparaison de pointeurs. Le test d'égalité usuel en OCaml, c'est
plutôt `=`. Sur les types numériques et les booléens, `==` et `=` se
comportent pareil. Par contre sur toutes les structures (paires,
listes, ...)  on pourra voir des différences significatives. Par
exemple `(1,2) == (1,2)` donnera `false`, vu que les deux paires
`(1,2)` précédentes ne sont pas partagées en mémoire. Par contre ces
deux paires ont des sous-éléments égaux 2 à 2, donc `(1,2) = (1,2)`
répondra `true`.

Bref: sauf cas très spécifique, c'est `=` qu'il vous faut comme test d'égalité
en OCaml, pas `==`.

Note: l'égalité `=` est *récursive* (elle descend visiter toutes les portions
internes des structures qu'elle compare). Cette comparaison n'est donc pas forcément
instantanée. De plus utiliser `=` sur des fonctions donne une exception.

Attention également à la négation de ces tests : `!=` est la négation de `==`, c'est
donc plutôt `<>` (la négation de `=`) que vous aurez à manipuler.

Au fait, des parenthèses autour d'un opérateur permet d'en faire une fonction *préfixe* : 
`1=2` mais `(=) 1 2`, de même pour `1+2` mais `(+) 1 2`. 
Sauf pour `(*)` ... qui commence un commentaire ! Ecrire alors plutôt `( * )`.

## Condition sans else

Attention avec:
```ocaml
if a then b
```

Cette syntaxe est légale, mais l'absence de `else` est compris par OCaml comme
un `else ()`, c'est-à-dire l'action vide (de type `unit`). Et alors le code
dans le `then` (ici `b`) doit alors être aussi de type `unit`.

Bref: tant que l'on est dans du code fonctionnel, tout `if` doit avoir son `then`
et son `else`. Et si un message d'erreur vous parle du type `unit` dans du code
fonctionnel, vérifiez que vos `else` sont bien là.


## Récursivité et let

Quel est le souci avec :
```ocaml
let rec fact n =
  let next = fact (n-1) in
  if n = 0 then 1 else n * next
```

Ce code a bien le bon type, mais son exécution ne termine pas. 
Ici le `if` permettant de tester s'il faut s'arrêter est fait trop tard,
après le lancement de l'appel récursif `fact (n-1)`. Donc même `fact 0`
lance `fact (-1)` qui lance `fact (-2)`, etc. On boucle déjà avant même
d'avoir fait la moindre comparaison `if n = 0 ...`.
C'est bien de penser à utiliser `let` pour mieux disposer ses programmes, 
mais il ne faut pas que cela change leurs déroulements.

Bref : dans une fonction récursive, tester le cas d'arrêt le plus tôt possible.

## Match et comparaison de variables

Quel est le souci avec :
```ocaml
let rec appartient x l =
  match l with
  | [] -> false
  | x :: _ -> true
  | y :: ys -> appartient x ys
```

Ce code est accepté par OCaml, mais des indices signalent qu'il y a un souci :

 - un *warning* nous indique que la dernière ligne du match est "unused"
 
 - le type obtenu est `'a -> 'b list -> bool` au lieu du `'a -> 'a list -> bool`
   que l'on désirait pour une fonction cherchant un élément dans une liste.

En fait, cette fonction ignore son premier argument, et répond `true` dès
que la liste n'est pas vide !

Dans un *motif* (ou *pattern*, i.e. ce qu'il y a entre `|` et `->`), les variables
sont juste du nommage de la partie correspondante de la structure, et non
des comparaisons avec des variables antérieures. Ici la ligne du milieu définit
une "nouvelle" abréviation x (qui cache l'ancienne, celle de l'argument de la fonction).

Deux manières de corriger ça :
 
  - soit enlever la ligne du milieu et mettre un `if` ou un `||` tout à la fin:

```ocaml
let rec appartient x l =
  match l with
  | [] -> false
  | y :: ys -> x = y || appartient x ys
```
  
  - soit mettre une condition booléenne en plus dans le cas du milieu, mot-clé `when` :

```ocaml
let rec appartient x l =
  match l with
  | [] -> false
  | y :: _ when x = y -> true
  | y :: ys -> appartient x ys
```
