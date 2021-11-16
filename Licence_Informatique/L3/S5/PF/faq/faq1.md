Programmation Fonctionnelle, Questions/Réponses Semaine 1
=========================================================

Pierre Letouzey (@irif.fr), avec l'aide précieuse d'un scribe anonyme (merci à elle ou lui)

# Organisation

### Page du cours ?

Dernières infos sur le cours : https://gaufre.informatique.univ-paris-diderot.fr/letouzey/pf5/
Lien court vers la même page : http://frama.link/pf5

Cette page est en fait un dépôt git, possibilité d'en voir l'historique des changements,
et même d'en récupérer une copie locale et de la mettre à jour (`git clone ...` puis des
`git pull`, on en reparlera).

### Suivi des étudiants ?

Inscrivez vous au moodle même s'il a pas prévu pour l'instant de s'en servir.
Inscrivez vous aussi à la mailling liste.
Dans les deux cas, détails en haut de la page du cours.

### Début des TP ?

Les TP commencent la semaine du 14/9. Amener vos PC si possible pour les TP, au moins au début.

### Qu'est que ces séances par visioconf ?

Les scéances à distances du mercredi et jeudi midi sont des séances de questions-réponses, pas des cours magistraux.
Lire les documents de cours (slides ou demo "sketch") mis à disposition chaque semaine.

Pas d'enregistrement de ces séances pour l'instant, mais probablement un fichier FAQ du style de celui-ci quelques jours après.

Possible de poser aussi des questions sur la mailing-list l3.pf5.info ou par mail direct

Sentez vous libre sur la séance à assister, vous pouvez même assister aux deux séances si vous le voulez. Tant que le système visio tient la charge...
Du moment que vous pouvez poser vos questions, tout est good. Pas forcément besoin de venir de la première à la dernière minute.

L'url utilisé pour ces séances changera sans doute au cours du semestre (p.ex. version de BBB avec authentification). Soyez vigilants.


### Notation du cours ?

Pour l'instant, l'évaluation du cours est toujours prévu 70% Examen + 30% Projet.
Examen en décembre ou début janvier. Soutenances de projet à ce moment-là.
Session2 : max(Exam2, 70% Exam2 + 30% Projet). A confirmer selon les conditions.

### Projet ?

Sujet du projet toujours en construction.

Idéalement il y aura une soutenance autour de début janvier où on vient avec son code et on l'explique. Sur place ou par visio, selon les possibles.

# OCaml, versions, installations ...

### Quoi installer sur sa machine ?

Pour les premiers TP, un navigateur web et du wifi suffiront, TPs sur une plateforme dédié :
LearnOCaml, http://pf5.ddns.net , merci de respecter les consignes d'utilisation données sur la page du cours
(choix de son pseudo, token à ne pas perdre, etc).

Au besoin, on peut aussi utiliser OCaml en ligne sur http://try.ocamlpro.org (accès libre).

Plus tard, pour des TP plus avancés et pour le projet, il y aura besoin d'une installation locale (avec compilateur, etc etc).
On fera le point à ce moment-là.

### Quelle version d'OCaml ?

Oui le prof utilise encore souvent OCaml 4.05 (version fournie par `sudo apt install ocaml` sur certaines Debian et Ubuntu).
Cela suffit largement pour débuter. Mais quelques petits détails peuvent changer dans les versions plus récentes, en particulier
dans la librairie standard fournie. On en reparlera, mais au minimum cela pourra aider que les binômes d'un projet utilisent la
même version.

# Programmation fonctionnelle

### Une programmation de haut niveau

Penser par équation mathématique, et donc par *réécriture*. Cela permet de s'abstraire des détails d'implémentation machine (instructions bas niveau, mémoire, etc).

Par exemple, si `f` est `fun x -> x + 1`, alors `f 3` vaut `(fun x -> x+1) 3` qui vaut `3+1` qui vaut `4`.

Voir aussi l'exemple du tri dans les slides (syntaxe idéalisée pour l'instant) :
`tri(x puis L) = (tri L filtré sur les nombre < x) ++ x ++ (L filtré tri sur les nombre > x)`
C'est une équation récursive. Pas besoin de savoir où sont ces listes en mémoire.

Gain : raisonnement bien plus facile pour se convaincre que le code est bon, pas d'état caché ou loin ou modifiable par autrui dans notre dos.

### En fonctionnel, des fonctions prises au sérieux !

On peut recevoir en argument des fonctions, ou en renvoyer en réponse.
Exemple standard : la composition de fonctions

```ocaml
let compose f g = fun x -> f (g x);;

let triple_plus_un = compose (fun x -> x+1) (fun x -> 3*x);;

triple_plus_un 2;; (* reponse 7 *)
```

### Typage ?

Le typage est ici une aide précieuse. C'est ici une opération *statique*, faite avant tout calcul. Et on parle de *typage fort* : ne pas typer est une erreur fatale, le code ne sera même pas exécuté.

En OCaml, le typage est *inféré* automatiquement, en général pas de type à écrire dans notre code. Mais plus tard on pourra *déclarer* des types en plus, ou des signatures.

Exemple (on y reviendra évidemment par la suite et on détaillera abondamment) :

```ocaml
fun f x -> f x;;
- : ('a -> 'b) -> 'a -> 'b = <fun>
```

Ce `fun` attend une fonction et un argument, et fait l'application des deux. Ce code n'a pas trop d'intérêt en pratique, mais regardons son typage.

Ici `f` est appliqué à `x`, OCaml en déduit donc que c'est une fonction, de type pour l'instant quelconque `'a->'b`. Ce `'a` est une variable de type pour l'entrée de f, et `'b` une autre variable pour la sortie de `f`, ici aucun lien ne peut être affirmé entre ces `'a` et `'b`. On peut juste dire que `x` est de type `'a` (car argument de `f`). Dans le type du tout répondu par OCaml, on retrouve le type de `f` (premier argument du `fun`) puis celui de `x` (second argument du `fun`), puis la réponse du tout (à savoir le type de `f x`).

Autre exemple:

```ocaml
fun f x -> f (f x);;
- : ('a -> 'a) -> 'a -> 'a = <fun>
```

Ce code applique une fonction `f` deux fois de suite à un point `x` donné. C'est la composition de `f` avec elle-même, on parle aussi d'itéré deuxième, ou `f^2` en math.

Niveau typage, c'est presque la même histoire, sauf qu'ici le résultat de la première application `f x` est redonné en argument à un deuxième appel à `f`.
Donc le type entrée `'a` de `f` de tout à l'heure est maintenant connu par OCaml comme étant égal au type de sortie `'b` de `f`.
Le type affiché n'a plus alors que des `'a`.


### Manipulation de données structurées

Notion clé, l'immutabilité : une structure immutable ne change plus jamais une fois créée ! Mais on peut accéder à une sous-partie, ou bien faire une structure dérivée réutilisant tout ou partie. Quand une structure n'est plus accessible, récupération mémoire automatique.

La programmation fonctionnelle est très adaptée pour manipuler des données structurées, des langages, de la logique, des arbres, graphes, etc, etc.

### Récursivité ? Induction ?

Ici l'induction est la même chose que la récursivité. En math on parle aussi de récurrence. En OCaml : `let rec`.

C'est le style privilégié en programmation fonctionnelle pour remplacer les boucles (`for` ou `while`) qui sont intrinsèquement lié au style impératif, donc non disposibles en fonctionnel pur. Oui cela peut demander un effort de pensée si vous êtes trop accoutumés aux boucles et aux variables accumulateurs, mais ce n'est pas foncièrement plus dur.

Les fonctions récursives ont mauvaise réputation dans d'autres langages (typiquement Java) car ils les implémentent de façon peu efficace.
Ici cela se passera nettement mieux.

### Qu'est ce qu'un effet de bord ?

Lors d'un calcul, toute action qui laisse une trace visible (ou détectable), en marge du calcul du résultat principal.
Par exemple un "printf" mis dans un code pour avoir des informations de "debug", ou modifier une mémoire au cours du calcul.
En programmation fonctionnelle pure, pas d'effet de bord du tout. L'appel à une fonction donne uniquement un résultat, le reste du monde est resté le même à la sortie (en tout cas observationnellement). En particulier deux appels à la même fonction avec les mêmes arguments donneront le même résultat.

On verra plus tard que OCaml permet aussi de faire de la programmation impérative, et donc de faire des effets de bord.

### La programmation fonctionnelle est-elle répandue ?

Cf plus bas pour la dissémination d'OCaml ou d'autres langages fonctionnels comme Haskell.

Au delà, la plupart des langages de programmation modernes proposent maintenant des idées venant de la programmation fonctionnelle (clôtures en Java, 8 p.ex). Car ces idées aident le programmeur !

Voir aussi Scala par exemple pour faire du fonctionnel tout en restant compatible avec la JVM et les bibliothèques Java.

L'intéret de la programmation fonctionnelle est de pouvoir raisonner/ prouver du code car on s'abstrait de l'état de la machine.

Ce serait pas mal de relire le document introductif à la fin du semestre pour se donner un avis sur la programmation fonctionnelle, une fois l'avoir pratiqué.

# OCaml

### Déjà, où ne *pas* mettre OCaml ?

Dans les avions et autres endroits "embarqués", en général : pas de garanties sur la quantité de mémoire utilisée, et la gestion automatique de mémoire (ramasse-miette, alias "garbage collector", GC) entraîne des micro-pauses de temps en temps, donc pas de garanties "temps-réel".

Par contre OCaml est tout indiqué pour des outils permettant d'analyser, raisonner, valider du code critique (avionique, etc).
Voir par exemple https://ocaml.org/learn/success.html#The-ASTR-E-Static-Analyzer

### A part ça, un langage généraliste mais axé fonctionnel dès le départ !

OCaml propose également de l'impératif (on parle aussi de style déclaratif, voir plus tard les "références", les tableaux mutables, etc).
Et de la programmation objet (que l'on utilisera pas ici), de l'asynchrone, etc. Citation anonyme : "Enfin on peut presque tout faire en Caml".

Pour nous c'est surtout un langage où le style fonctionnel est mis en avant dès le début, avec du typage fort et des structures de données immutables par défaut. Mais sans être aussi radical dans ces choix qu'un langage fonctionnel "pur" comme Haskell.


### OCaml est surtout utilisé pour la recherche ?

C'est la réputation de ce langage, "un truc d'universitaire". Comme souvent avec les idées reçues, il y a un fond de vérité, voir par exemple l'usage d'OCaml dans des outils logiques comme [Coq](https://coq.inria.fr). Mais on est maintenant bien au delà de ça, voir par exemple https://ocaml.org/learn/success.html . En particulier beaucoup de start-up ou même d'entreprises déjà bien établies embauchant des programmeurs OCaml, même en île-de-france.

### Est-ce qu'OCaml est portable de machine en machine ?

Au niveau du code source, sur le coeur du langage, très peu de soucis de portabilité. Attention juste aux entiers disponibles par défaut : `max_int` vaut `2^62-1` sur une machine 64-bits mais `2^30-1` sur une machine 32-bits. Note : un bit sert pour le signe (comme pour les autres langages) mais un bit est réservé par OCaml pour distinguer les nombres usuels des pointeurs machines (ça c'est spécifique à OCaml). Si besoin, il y a des modules `Int32` et `Int64` qui sont d'une taille précise *partout* (quitte à être un peu plus lent si ce n'est pas ce que le CPU local fournit). Il existe aussi des libraries d'entiers de taille arbitraire (le vieux `bignum` ou plus récemment `zarith`) si on a besoin de *très* gros nombres.

Maintenant, quelques librairies sont spécifiques au système d'exploitation. Exemple typique, la librairie `Unix` d'OCaml a quelques fonctionnalités manquantes ou différentes sous Windows.

Bref, niveau source, OCaml est un peu moins universellement portable que Java, mais tellement plus que C.

Maintenant, niveau programme final :

Quand la vitesse n'est pas une priorité, OCaml propose une compilation vers du `bytecode` (idéalisation d'un assembleur, cf `ocamlc` et ses fichiers `*.cmo`). En théorie un même fichier `.cmo` peut tourner partout si on a le bon "runtime", en pratique c'est trop sensible à la version d'OCaml pour être vraiment utilisé ainsi.

En pratique, pour faire de véritables programmes autonomes, on utilise le compilateur "natif" `ocamlopt` (produisant de l'assembleur de la machine cible). Très efficace, mais le binaire obtenu est spécifique à chaque architecture, même quand le source du programme est portable.

### Efficacité ?

Cela dépend évidemment des usages. Mais pour des besoins "typiques" tels que les notres, et en utilisant le compilateur "natif" `ocamlopt`, des vitesses souvent très satisfaisantes (un peu plus lent qu'en C, mais dans le même ordre de grandeur). Même avec une gestion 100% automatique de la mémoire ("garbage collector")! Par contre quelques points spécifiques (comme l'utilisation intensive de nombres flottants) peut pénaliser OCaml. Si on utilise le compilateur "bytecode" `ocamlc`, s'attendre à un ralentissement d'un facteur 10 à 100. En particulier ne pas faire de "benchmark" sérieux dans le "toplevel" OCaml (qui utilise le "bytecode"). Enfin, attention, pas encore d'implémentation efficace du "multithreading" en OCaml (travail en cours), pour profiter du parallélisme des machines actuelles en OCaml il faudra lancer plusieurs processus (`fork`).

# Syntaxe OCaml

### Pourquoi ces `;;` ?

`;;` permet de finir les phrases mais OCaml est intelligent et on aura plus besoin de mettre de ";;" lorsque l'on apprendra a utiliser les `let`.
Pour l'instant, dans le doute, mettre partout ces `;;` partout.

### Plusieurs styles pour écrire des fonctions nommées ?

Les deux phrases suivantes sont compris 100% pareil par OCaml:

```ocaml
let identity = fun x -> x
let identity x = x
```	

Idem pour les quatres suivantes :

```ocaml
let f x y = x + y
let f x = fun y -> x + y
let f = fun x y -> x + y
let f = fun x -> fun y -> x + y
```

La préférence du prof : en général, aller au plus court, donc les arguments à gauche du `=`.

### Appel de fonction sans parenthèse ?

Tant que l'argument est "simple" (une constante positive ou bien une variable), l'usage est de ne *pas* parenthéser,
juste mettre un blanc. Mettre des parenthèses ne seraient pas une erreur, juste un style un peu lourd.
Par contre, pour un argument plus complexe (lui-même un calcul, appel de fonction, nombre négatif, etc), les parenthèses
sont nécessaires pour désambiguer.

```ocaml
let f x = 2 * x;;
let c = 3;;

f 5;;  (* argument constant *)
f (-5);; (* argument constant, mais le - oblige à parenthéser sinon OCaml lira ((f)-(5)) *)
f c;; (* argument : une variable *)
f c + f 5;;
f(c) + f(5);; (* possible aussi, mais pas "idiomatique" en OCaml *)
f (c + f 5);; (* les parenthèses changent radicalement le sens de cette expression, resultat 26 au lieu de 16 *)
```

Tout ceci vaut également pour les fonctions à plusieurs arguments : `f 1 2` ou `f x y` mais `f (x + 1) y`.
Dans cette dernière expression, les parenthères sont indispensables, `f x + 1 y` serait une addition entre un appel `f x` et ... une erreur, vu que `1 y` est *mal typé*, 1 n'étant pas une fonction.

Là encore, si vous avez un doute, vous pouvez toujours mettre une paire de parenthèses autour de *chaque* argument, par exemple `f (x+1) (y)` ou même `f(x+1)(y)`. Pas forcément très joli aux yeux d'un habité du langage, mais toujours correct.

On verra par la suite qu'une syntaxe comme `f(1,2)` existe aussi en OCaml, mais avec une autre signification: fonction recevait une *paire* `(1,2)`.

### Et le match ?

match c'est à peu près l'equivalent de switch dans Java, mais aux stéroides.

quand on a un match avec une liste, on pourra analyser les différents cas, exemple le cas d'une liste avec deux éléments exactement.

Note : la fonction `remove_repetiton` utilise plusieurs possibilités avancées du `match` pour que vous ayez une petite idée de ses possibilités, c'est tout-à-fait normal de ne pas comprendre tous les détails pour l'instant.

### Pourquoi dire qu'un `let x = ...` n'allouera peut-être pas de mémoire ?

Par exemple dans le cas d'un `let` local, avec en outre que des constantes derrière :

```
let x = 3+3
in x+x;;
```

Ici la compilation a toutes les informations, il n'a pas besoin de sauvegarder ces valeurs.
Un compilateur intelligent remplacera le code ci-dessus par 12 car ça ne change pas la sémantique.
