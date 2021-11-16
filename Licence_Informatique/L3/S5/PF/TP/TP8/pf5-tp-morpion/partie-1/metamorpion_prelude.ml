type plateau = int option Matrix.t Matrix.t

type coup =
  { joueur : int
  ; position_dim_2 : int * int
  ; position_dim_1 : int * int
  }

include Morpion_victoire

let gagnant_dim_1 = gagnant

let termine_dim_1 = termine
