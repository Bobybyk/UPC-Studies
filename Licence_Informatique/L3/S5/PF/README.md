# Introduction à la programmation fonctionnelle

Ce dépôt git contient les ressources pédagogiques du cours de troisième
année de licence d'Informatique de l'Université de Paris (ex-Diderot) intitulé
"Programmation Fonctionnelle" (PF5).

## Objectifs du cours

Voir [OBJECTIFS.md](OBJECTIFS.md)

## Organisation du cours

- Le responsable du cours est Pierre Letouzey. 
- Les chargés de TD sont Balthazar Bauer, Giovanni Bernardi, Pierre Letouzey, Alliaume Lopez, Jean-Marie Madiot, Vincent Padovani et Daniel Szilagyi.
- Le cours est évalué par un projet (30%) et par un examen final (70%).

## Inscrivez-vous !

- Inscrivez-vous sur https://moodle.u-paris.fr/course/view.php?id=1641
- La mailing-liste dédiée au cours est `l3.pf5.info@listes.u-paris.fr`. Inscrivez-vous via https://listes.u-paris.fr/wws/subscribe/l3.pf5.info


## Cours

Mercredi 8h30-10h30, Amphi 1A. Premier cours le mercredi 15 septembre 2021.

Documents à venir progressivement. Pour l'instant les curieux peuvent jeter un oeil aux [anciens transparents](slides), ou aux anciennes [Questions-Réponses](faq).

#### Cours 1 (15/9/2021) :
  - Un document d'[introduction au fonctionnel et à OCaml](slides/2020/cours-01-yann.pdf) par Yann Régis-Gianas. 
  - Un tour d'horizon d'OCaml : [sketch](https://sketch.sh/s/H3xyXu6P3YdaHMqOVYXq6b/) dynamique et [markdown](slides/2020/cours-01-tour.md) statique.
  - *Attention* : si vous expérimentez avec [sketch](https://sketch.sh), bien cliquer sur "ML" au lieu de "RE" au début de chaque nouvelle page, pour choisir la syntaxe OCaml et non ReasonML.

#### Cours 2 (22/9/2021) :
  - Les types de données OCaml : [sketch](https://sketch.sh/s/RjxDVUFPNMiZqKxDtzdezN/) et [markdown](slides/2020/cours-02-types.md).
  - Au programme : types de base, types composés (paires, n-uplets), types personnalisés (p.ex types sommes ou types algébriques), etc.
  - Note : les liens ci-dessus sont ceux de 2020 mais correspondent quasiment à ce qui a été montré en cours cette année, avec des explications écrites en plus.

#### Cours 3 (29/9/2021) :
  - Compléments sur les listes OCaml : fichier [ml](slides/2021/cours-03-listes.ml)
  - Retour sur les arbres et autres types algébriques OCaml : voir la fin du cours 2
  - Les [outils OCaml](slides/2020/cours-03-outils.md) : toplevel, compilateurs, etc. 
  - Le programme complet [fib.ml](slides/2021/cours-03/fib.ml) vu en fin de cours.

#### Cours 4 (6/10/2021) :
  - Les fonctions OCaml : document [markdown](slides/2021/cours-04-fun.md).
  - En particulier, syntaxe et typage des fonctions, applications partielles, fonctions curryfiées ou décurryfiées, etc

#### Cours 5 (13/10/2021) :
  - Fonctions encodant des entiers, des suites infinis : [fichier ml](slides/2021/cours-04-funbis.ml).
  - Les fonctions récursives terminales : [markdown](slides/2020/cours-05-tailrec.md) ou [sketch](https://sketch.sh/s/6k9ft6DS3nA6xQjVNa4v1g).

#### Cours 6 (20/10/2021) :
  - Type option et exceptions : [markdown](slides/2020/cours-06-exn.md) ou [sketch](https://sketch.sh/s/LNYzzbJLVpKgRIEYW5l2pM).

#### Cours 7 (27/10/2021)
  - Entrées/sorties et graphisme : [markdown7a](slides/2020/cours-07a-io.md), [markdown7b](slides/2020/cours-07b-graphics.md), [pdf](slides/2020/cours-07-io-graphics.pdf) d'origine. Auteur initial de ce cours : R. Treinen.

#### Cours 8 (10/11/2021)
  - Structures impératives (références, records mutables, tableaux) : [markdown](slides/2020/cours-08-imperatif.md) ou [sketch](https://sketch.sh/s/odCwxaMbe7e5NxgNAboW9e).

## Travaux pratiques (via Learn-OCaml)

| Groupe    | Enseignant  | Créneau         | Salle        |
|-----------|-------------|-----------------|--------------|
| Groupe 1  | B. Bauer    | Mar 10:45-12:45 | 2031 (+2032) |
| Groupe 2  | V. Padovani | Mar 14:00-16:00 | 2031 (+2032) |
| Groupe 3  | A. Lopez    | Lun 08:30-10:30 | 2031 (+2032) |
| Groupe 4  | G. Bernardi | Mar 11:15-13:15 | 2001 (+2003) |
| Groupe 5  | JM. Madiot  | Mar 15:00-17:00 | 2003         |
| MathInfo1 | D. Szilagyi | Mer 14:00-16:00 | 2003 (+2001) |
| MathInfo2 | P. Letouzey | Jeu 16:15-18:15 | 2032 (+2001) |

Début des TP la semaine du 20 septembre 2021.

Les premiers TP utilisent https://pf5.ddns.net , une instance de la plateforme **Learn-OCaml** : 
- A la première connexion, choisissez un identifiant ("pseudonyme" ou "nickname") qui commence par votre groupe puis votre nom de famille, par exemple Info3-Skywalker ou MathInfo2-Solo.
- Le secret est pf5
- Notez absolument votre TOKEN. C'est grâce à lui que vous pourrez retrouver votre session lors de votre prochaine connexion.

#### TP7 et TP8 : Morpion

Même si le TP7 commence sur Learn-OCaml comme d'habitude, la suite de ce TP7 puis le TP8 nécessitent d'avoir OCaml sur sa machine.

- Voir [INSTALL.md](INSTALL.md) pour les conseils d'installation d'OCaml sur sa machine. Sur les systèmes recommandés (Debian ou Ubuntu récentes) il s'agit essentiellement d'un `sudo apt install ocaml ocaml-findlib ocaml-dune` (et peut-être aussi `libgraphics-ocaml-dev`).
- Le code fourni pour ces TP est dans le dépôt git [pf5-tp-morpion](https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-tp-morpion), que l'on peut récupérer via:
```
git clone https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-tp-morpion.git
```


## Projet

**Polish** : étude d'un mini-langage impératif (lecture, évaluation, analyse statique)

- Voir le [dépot git](https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-projet) dédié à ce projet, qu'il faudra "forker".
- Dans ce dépôt, voir en particulier le [sujet](https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-projet/blob/master/projet.pdf),
  les [CONSIGNES](https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-projet/blob/master/CONSIGNES.md) et l'aide sur
  [git et GitLab](https://gaufre.informatique.univ-paris-diderot.fr/pf5-profs/pf5-projet/blob/master/GIT.md).
- Voir aussi [INSTALL.md](INSTALL.md) pour les conseils d'installation d'OCaml sur sa machine.

## Anciens examens

- [examen 2018/2019](exams/examen1819.pdf) écrit par Michele Pagani.
  Correction : [markdown](exams/exam1819.md), [sketch](https://sketch.sh/s/dgfrHHkNzdUuf3VYTRO3Vy/).

- [examen 2019/2020](exams/examen1920.pdf) par Y. Regis-Gianas. Correction : [markdown](exams/exam1920.md).

- [examen 2020/2021](exams/examen2021.pdf). [Eléments de correction](exams/exam2021.ml).

## Références et bibliographie

OCaml ailleurs sur internet ou dans des livres. Ceci n'est clairement pas exhaustif, et privilégie les ressources accessibles gratuitement.

- Le site principal sur OCaml : https://ocaml.org
- Le manuel d'OCaml : https://ocaml.org/releases/latest/manual.html
- Une bibliographie complète : https://ocaml.org/learn/books.html
- Les [notes de cours](http://www.enseignement.polytechnique.fr/profs/informatique/Jean-Christophe.Filliatre/14-15/INF549/ocaml.pdf) de J.C. Filliâtre à l'X. L'essentiel d'OCaml en 50 pages!
  Et aussi un petit [résumé de la syntaxe](http://www.enseignement.polytechnique.fr/profs/informatique/Jean-Christophe.Filliatre/14-15/INF549/memo-java-ocaml.pdf) comparée avec celle de Java.

#### Quelques Livres

- [Développement d'applications avec OCaml](https://www-apr.lip6.fr/~chaillou/Public/DA-OCAML/index.html), E. Chailloux, P. Manoury, B. Pagano. 2002 mais encore très pertinent!

- [Apprendre à programmer avec OCaml](http://programmer-avec-ocaml.lri.fr/), S. Conchon, J.C. Filliâtre, 2014. Seulement partiellement disponible en ligne, mais beaucoup d'exemples et d'exercices.

- [Real World OCaml](https://dev.realworldocaml.org/), Y. Minsky, A. Madhavapeddy, J. Hickey, 2013. Je le mentionne car ce livre est très connu, mais **attention** même si ce livre parle évidemment d'OCaml il utilise dès le début une bibliothèque alternative (`Base` et `Core`) qui diffère sensiblement de la bibliothèque standard d'OCaml.

- [Unix System Programming in OCaml](http://ocaml.github.io/ocamlunix/), X. Leroy, D. Rémy, 2010.

- [Le langage Caml](http://caml.inria.fr/pub/distrib/books/llc.pdf) P. Weis, X Leroy, 1993-1999.
  Le tout premier livre sur Caml. **Attention** ce livre traite de Caml Light, le précurseur d'OCaml, il y a donc de sensibles différences par endroit (syntaxe, bibliothèques). Mais cela reste une lecture passionnante.
