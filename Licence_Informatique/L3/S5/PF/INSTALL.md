Installation d'OCaml sur vos machines
=====================================

A partir du TP7 (exercices sur le morpion), et pour le projet de
ce cours, il vous faudra disposer du compilateur OCaml sur une machine
locale. Le TP7 nécessitera également la bibliothèque `graphics`.

  - Si vous utilisez une machine des salles 2031/2032 (PC/Linux), 
    pas de souci, tout y est. Les Mac de la salle 2003 fournissent
    également OCaml, mais privilégiez plutôt vos propres ordinateurs.

  - Sur vos propres machines, tout dépend du système:
    * Sur une Ubuntu ou Debian : `sudo apt install ocaml ocaml-findlib`. Les autres Linux fournissent presque tous OCaml : https://ocaml.org/docs/install.html#Linux .
    * Si vous voulez avoir la dernière version d'OCaml, lisez la section sur l'[installation d'OPAM](#opam).
    * Sur Mac et Windows, privilégier une machine virtuelle Linux si vous avez ça. Si vous avez une installation récente de Windows, vous pouvez utiliser [WSL](https://docs.microsoft.com/fr-fr/windows/wsl/install#install).
      Sinon suivre les instructions de https://ocaml.org/docs/install.html .

  - Depuis chez vous, une autre possibilité est de se connecter
    au serveur de l'UFR nommé `lulu` par ssh, [détails ici](http://www.informatique.univ-paris-diderot.fr/wiki/doku.php/wiki/howto_connect).
    Ce serveur propose les mêmes programmes que les PC/Linux des salles
    2031/2032, et donc en particulier tout ce qu'il faut pour programmer
    en OCaml. En s'y connectant via `ssh -Y` vous pourrez même lancer
    des programmes graphiques, tant qu'ils ne sont pas trop gourmands
    en bande passante, et que vous avez un [serveur X](https://fr.wikipedia.org/wiki/X_Window_System)
    de votre côté.

## OPAM

OPAM est un outil qui vous permet de gérer facilement plusieurs installations
d'OCaml, et d'installer les bibliothèques. En particulier, OPAM vous permet
d'utiliser une version d'OCaml plus récente que celle disponible dans votre
distribution (par exemple, OCaml 4.08, installé par défaut dans Ubuntu 20.04,
est sortie il y a plus de 2 ans). 

Vous pouvez installer OPAM directement depuis votre dépôt (c’est-à-dire
`sudo apt install opam` depuis Ubuntu 20.04+ et Debian 10+).

Remarque : sur Ubuntu 18.04, même `opam` commence à dater, mieux vaut éviter la
version 1 qui n'est plus maintenue. Voir les 
[conseils suivants](https://opam.ocaml.org/doc/Install.html#Ubuntu) pour
installer `opam` depuis un "PPA" plutôt que la version d'origine du système.

Une fois OPAM est installé, vous pouvez suivre les instructions
[ici](https://ocaml.org/docs/install.html#OPAM) pour installer la version
d'OCaml que vous voulez. Par exemple, pour installer OCaml 4.12.1, il faut
exécuter
```
  opam init
  eval $(opam env)
  opam switch create 4.12.1
  eval $(opam env)
```
Exécutez `ocaml -version` pour vérifier si vous avez bien installé la bonne
version d'OCaml.

Attention : en cas d'utilisation d'OPAM, bien penser ensuite à exécuter
dans son terminal `eval $(opam env)` à chaque session. En particulier, cela ajoute
à son `$PATH` les chemins non-standards utilisés par OPAM lors de ses installations.
On peut éventuellement ajouter cela une fois pour toute à sa configuration shell
(p.ex. dans son `~/.bashrc`).

## Dune

L'outil `dune` est très commode pour superviser la compilation d'un programme OCaml.
Sur un système Debian ou Ubuntu récent, faire `sudo apt install ocaml-dune`.
Note : ce paquet s'appellait `dune` auparavant (par exemple sur Debian Buster).

Une autre possibilité est d'utiliser [opam](https://ocaml.org/docs/install.html#OPAM)
(voir plus haut) pour installer `dune`, via `opam install dune ocamlfind`. Comme expliqué
ci-dessus, bien penser alors à utiliser `eval $(opam env)` à chaque session pour ajuster
son `$PATH`.

En particulier, Ubuntu 18.04 ne proposait pas de paquet officiel pour cet outil `dune`.
Sur ce système il faudra donc utiliser OPAM pour install `dune`.
Pire, Ubuntu 18.04 suggérait alors un paquet nommé `whitedune` qui n'a **rien à voir**
avec OCaml. Mieux vaut le **désinstaller** si vous l'avez installé par erreur,
pour éviter toute confusion.

## Graphics

Sur MacOS, pour une installation native, commencer par installer le
serveur X nommé [XQuartz](https://www.xquartz.org/).

Si votre version d'OCaml est 4.08 ou moins, la bibliothèque `graphics` 
est fournie avec l'installation standard d'OCaml, a priori rien à faire de plus. 
C'est par exemple le cas d'Ubuntu 20.04. Attention alors à bien installer
le paquet `ocaml` et pas seulement `ocaml-nox` vu que ce dernier est
précisément OCaml *sans* graphics (nox c'est pour "No-X11").

Par contre si vous avez OCaml 4.09 ou plus récent, il faudra installer `graphics` 
en plus. Par exemple sur Ubuntu 21+ ou Debian 11+ : `sudo apt install libgraphics-ocaml-dev`.
Sinon, `graphics` peut s'installer via [opam](https://ocaml.org/docs/install.html#OPAM) :
`opam install graphics ocamlfind`. Voir plus haut la section sur OPAM
pour plus de détails.

## Editeur de code

Mon favori est `emacs` équipé du plugin `tuareg` (paquet nommé `tuareg-mode` 
sous Ubuntu et Debian, ou plus récemment `elpa-tuareg`), et éventuellement du plugin `merlin`.
Sinon il y a beaucoup d'autres éditeurs de code proposant un support pour OCaml, à vous de voir.
Par exemple
[Visual Studio Code](https://code.visualstudio.com/) avec l'extension
[OCaml Platform](https://marketplace.visualstudio.com/items?itemName=ocamllabs.ocaml-platform).
Pour l'utiliser via une connexion SSH (y compris localement avec WSL), installez l'extension
[Remote Development](https://marketplace.visualstudio.com/items?itemName=ms-vscode-remote.vscode-remote-extensionpack).

## Plus d'informations

Revoir le cours 3 sur les [Outils](slides/cours-03-outils.md) disponibles dans 
l'écosystème OCaml.
