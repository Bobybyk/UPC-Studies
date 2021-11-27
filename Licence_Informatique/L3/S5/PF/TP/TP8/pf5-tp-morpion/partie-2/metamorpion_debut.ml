let plateau_initial (taille : int) : plateau =
  failwith "TODO"
;;

let sous_morpion_valide
    (plateau : plateau)
    (dernier_coup : coup option)
    ((i,j) : int * int)
  : bool =
  false
;;

let coup_legal
    (plateau : plateau)
    (dernier_coup : coup option)
    (coup : coup)
  : bool =
  false
;;

let prepare_coup
    (plateau : plateau)
    (dernier_coup : coup option)
    (nb_joueurs : int)
    ((x,y) : int * int)
  : coup option =
  None
;;
