open Metamorpion_debut
open Metamorpion_victoire
open Graphics
open Graphics_extra

let affiche_case (contenu : int option) ((x,y) : int * int) : unit =
  failwith "TODO"

let main () =
  create_window 800 800;
  clear_graph ();
  affiche_case (Some 1) (300,300);
  synchronize ();
  close_after_event ()

let _ = main ()
