(* Note : en OCaml un opérateur "puissance" `**` pour les float,
   mais pas d'équivalent sur les int par défaut. *)

2.0 ** 10.;;

(* Par contre, pour les puissances de 2, on peut utiliser : *)

(* ## Les opérateurs bit-à-bit ## *)

(* lsl : logical shift left.
   n lsl k = n*2^k, avec perte des bits du haut si dépassement *)

1 lsl 10;; (* 2^10 = 1024 *)

3 lsl 2;; (* donne 0b1100 = 12 *)

(* lsr : logical shift right.
   n lsr k = n/2^k, au moins pour n>=0. Attention au destin du signe sinon.

   asr : arithmetical shift right. Comme lsr, mais préserve le signe *)

12 lsr 2;; (* 0b1100, décalé 2x à droite, redonne 0b11 = 3 *)

(-1) lsr 1;; (* que des bits à 1, sauf le signe devenu 0 :
                c'est max_int, le plus grand int disponible en OCaml *)

(* Note : ceci permet de voir que ce sketch s'exécute en mode 32 bits... *)

max_int;;

-12 asr 2;; (* division par 4 *)

(** Combinaison logique bit par bit de deux nombres : land, lor, lxor *)

12 land 8;; (* 0b1100 mis avec 0b1000 bit à bit : seul un seul bit survit ici *)

(** intérêt possible : faire des "masque", i.e. selectionner un ou quelques bits
    d'un nombre. Exemple : test de parité. *)

let is_even n = (n land 1 = 0) ;;
is_even 15;;
is_even 12;;

(* ## Usage des simples point-virgules ## *)

(* Les doubles point-virgules ;; finissent une phrase OCaml,
   par contre les simples ; servent à séparer des *actions* (impératives)
   au sein de la même phrase. Comme on n'utilise pas le style impératif pour le
   moment, vous n'en avez pas encore besoin. Exemple d'utilisation pour plus
   tard: *)

for i = 1 to 10 do print_int 8 ; print_int 7 done;;

(* Exemple de message d'erreur obscure mentionnant `unit`, le type des actions.
   En fait c'est juste que ce `if` nécessite un `else`, sinon c'est une action
   (avec l'action vide `()` dans le cas `else`). *)

(* if true then 3;; *)

(* Exemple de `if` sans `else` *)

if true then print_int 3;;

if true then print_int 3 else ();;

();;



(* ## Types sommes et types algébriques ## *)

type montype = A of int | B of bool;; 

(* A et B sont ici les *constucteurs* du type montype.
   Vous pouvez choisir leurs noms, obligatoirement avec une majuscule au début. *)

A 3;;

B false;;

(* Exemple de fonction sur ce type *)
let mafonc t = match t with
| A n -> n
| B b -> if b then 1 else 0
;;

(* Maintenant, avec un peu de récursivité : montyperec intervient dans sa propre
   définition *)
type montyperec = A of int * montyperec | B ;;

(* Notez au passage que B n'a ici aucun argument, alors que A en a maintenant 2
   (on utilise alors une syntaxe proche des paires pour donner ces arguments) *)

B;;
let ex = A (3, (A (5, B)));;

(* Disposition en mémoire : des *blocs* (sortes de tableaux), avec soit des
   valeurs immédiates, soit des pointeurs vers d'autres blocs.
   Cf les listes (simplement) chaînées dans d'autres langages.

   ex = BLOCA(3,.)
                |
                |
                V
                BLOCA(5,.)
                        |
                        |
                        V
                        BLOCB()

    Chaque suivi de pointeur a un coût (un accès en mémoire).
    Accéder au début d'une structure est quasi-gratuit (p.ex. tête d'une liste),
    par contre aller au fond d'une structure a un coût proportionnel aux nombres
    de pointeurs à suivre (p.ex. coût "linéaire" pour calculer la longueur d'une
    liste, ou aller voir son dernier element).
*)

(* Intérêt de cette représentation : on peut facilement réutiliser une ancienne
   structure par exemple dans une structure plus grande (on parle de *partage* ).
   Attention, ceci n'est sans risque *que* si la structure partagée est
   *immutable* (i.e. en lecture seule), ce qui est le cas par défaut en OCaml.
*)

let expluslong = A(9, ex)

(* Occupation mémoire de expluslong : BLOCA(9,.)
                                              |
                                              |
                                              V
                                              les anciens blocs de ex.
   Donc un seul bloc réellement alloué. *)

(* On peut aussi rebrancher sur des sous-blocs d'une ancienne structure *)
let variante = match ex with
| B -> failwith "impossible"
| A (n,sousex) -> A(10,sousex);;

(* Schéma mémoire à la fin :

   expluslong = BLOCA(9,.)
                        |
                        V
                 ex = BLOCA(3,.)
                              |
                              |
     variante = BLOCA(10,.)   |
                         |    |
                         V    V
                         BLOCA(5,.)
                                 |
                                 V
                                 BLOCB()
*)

(* Un type algébrique récursif sans cas de base:
   A priori on ne sais plus comment en construire le moindre, donc ce n'est pas
   utilisable. *)
type infini = Infi of int * infini

(* En fait on peut, mais via un *cycle* dans notre structure, obtenu via un
   `let rec` sur la donnée, pas sur une fonction. A éviter, c'est très dur à
   bien manipuler sans boucler à l'infini. *)
let rec exinfini = Infi (1,exinfini)
(* En mémoire : BLOCInfi(1,.)
                     /\    |
                     |_____|
*)

(* ## Et les arbres ? ## *)

(* Un type algébrique peut faire référence à lui-même plusieurs fois.
   Ceci permet ainsi de faire p.ex. une structure d'arbre binaire,
   avec un sous-arbre gauche et un sous-arbre droit à chaque noeud,
   en plus d'une valeur entière par exemple. *)

type arbre = Noeud of int * arbre * arbre | Feuille;;

(* En mémoire, un Noeud sera un bloc à trois cases : un entier puis deux
   pointeurs vers d'autres arbres *)

(* ## Les listes OCaml ## *)

(* OCaml : les listes "officielles" de OCaml sont comme `montyperec`,
   mais avec [] comme cas d'arrêt au lieu de B précédemment, et :: au lieu de A.
   Et également avec du *polymorphisme* : on peut y mettre des éléments d'un
   certain type au lieu de forcément int auparavant. Homogénéité : tous les
   élements d'une même liste ont le même type.

   Avec un OCaml récent, voici une définition possible des listes (mais ne pas
   le faire, sinon conflit ensuite avec la définition "builtin" dans OCaml).
   type 'a list = [] | (::) of 'a * 'a list
*)

(* Une définition possible de List.length *)
let rec length l = match l with
| [] -> 0
| x :: q -> 1 + length q;;

(* Paraphrase en bon français :
   pour calculer la longueur d'une liste l,
   si cette liste l est vide, c'est 0
   sinon nommons x l'élément de tête de l et nommons q la queue de l
   (cf. mes pointeurs ci-dessus), et alors la réponse est la longueur de
   q plus 1. *)

length [];;

length [7];; (* [7] = 7::[] donc length [7] = 1 + length [] = 1 + 0 = 1 *)

length [7;2;8];; (* [7;2;8] = 7 :: (2 :: (8 :: [])) *)

(* Quelques variantes posibles: *)
(* Primo, x ne sert nul part, on peut donc le remplacer par le motif anonyme _
*)

let rec length l = match l with
| [] -> 0
| _ :: q -> 1 + length q;;

(* Secondo, la première variable l n'est pas réutilisée ensuite, on peut donc
   réutiliser le même nom pour la sous-liste q (attention, style à manier avec
   précaution, plus délicat à lire) *)

let rec length l = match l with
| [] -> 0
| _ :: l -> 1 + length l;;

(* Tertio, quand le seul destin d'un argument (ici l) est de subir un `match`,
   on peut abréger cela via le mot-clé `function`.
   Ce `function` résume donc ici `fun l -> match l with ...` *)

let rec length = function
| [] -> 0
| _ :: q -> 1 + length q;;

(* Autre style : recursivité "terminale".
   Comme on me l'a fait remarqué jeudi, le List.length officiel d'OCaml n'est
   pas tout-à-fait défini comme ci-dessous. La définition complète est faite
   en deux temps. D'abord une fonction prenant une liste, mais aussi un nombre
   d'éléments déjà visités auparavant (on parle d'accumulateur, d'où le nom acc).
*)
let rec length_aux l acc = match l with
| [] -> acc
| _ :: q -> length_aux q (1+acc);;

(* Et maintenant length n'est qu'un appel à length_aux avec 0 comme
   accumulateur *)
let length l = length_aux l 0;;

(* Intérêt de ce style : meilleur compilation par OCaml, un chouia plus rapide
   donc,mais surtout peut fonctionner avec des "grosses" listes (au delà de
   quelques dizaines de milliers d'élements l'autre style a des soucis).
   On y reviendra. *)

(* Au fait, où sert le `fun` ?
   Si on définit une fonction et qu'on la nomme immédiatement, on a vu la
   syntaxe `let mafonction x = ...` qui évite d'écrire
   `let mafonction = fun x -> ...`.
   Par contre, `fun` nous permet de définir des fonctions "anonyme", p.ex. pour
   les utiliser immédiatement, comme dans List.map.
*)

fun x -> x+1;;

fun x y -> x+y;;
(* synonyme de (+) *)
(+);;

List.map;;

List.map (fun x -> x = 0);;

List.map (fun x -> x = 0) [1;0;3];;

List.map (fun x -> x + 1) [1;0;3];;

List.map ((+) 1) [1;0;3];;

(* ## Ebauche de fonction calculant le minimum d'une liste d'entiers ## *)

(* A compléter ... *)

(* Note : failwith permet de bloquer le calcul via une *exception*, cf le
   `throw` de Java. Pratique quand on n'a pas de réponse légitime dans
   un certain cas, ou que l'on a pas fini son programme. On en reparlera. *)

let rec minlist l = match l with
| [] -> failwith "vide n'a pas de minimum"
| [x] -> x  (* ou bien | x::[] -> x *)
| x :: q -> (* a chercher : le minimun entre x et le minimum de q *)
            failwith "TO DO";;

(*let _ = minlist [];; *)

(*let _ = minlist [3;4];; *)


(* ## Une erreur classique avec la syntaxe des listes ## *)

length [1,2,4];; (* Pourquoi 1 comme réponse et non 3 ? *)

length [(1,2,4)];; (* Car les virgules fabriquent un triplet, qui est compté
                      comme un unique élément de liste *)

(* Voir aussi le type de la liste, qui n'est pas le ̀`int list` qu'on attendrait
   pour [1;2;3] *)

[1,2,3];;

(* ## Listes de listes ## *)

(* Dans une liste, on peut mettre des élements de tout type OCaml, y compris
   des listes. Tant que l'on reste homogène, i.e. que tous ces éléments ont
   bien un même type. *)

let list_list = [[]; [1;2]; []];; (* NB: int list list = (int list) list *)

length list_list;; (* seulement le 1er niveau de liste importe ici *)

(* Exemple de manipulation d'une liste de liste : tabuler les longueurs des
   listes internes *)

let rec lengths ll = match ll with
| [] -> []
| l :: ll' -> length l :: lengths ll' ;;

(* Désolé pour le cafouillage en direct à propos de cette fonction, c'était
   juste des ;; qui manquaient *)

lengths list_list;;

(* Sinon on peut utiliser les fonctions toutes faites d'OCaml: *)

List.map List.length list_list;;
