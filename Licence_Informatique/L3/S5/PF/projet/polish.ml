open Printf;;
open Types;;
open Reprint;;
open Read_polish;;
open Eval_polish;;
open Simpl_option;;
open Eval_vars;;
  
(** Projet Polish -- Analyse statique d'un mini-langage impératif *)

(** Note : cet embryon de projet est pour l'instant en un seul fichier
    polish.ml. Il est recommandé d'architecturer ultérieurement votre
    projet en plusieurs fichiers source de tailles raisonnables *)


(***********************************************************************)

(* tests *)
(* let file_content = read_file "exemples/abs.p";; *)
(* let () = List.iter (printf "%s ") file_content;; *)

(* absolute value function *)
let condi = (Var("n"),Lt,Num(0));;
let block1 = [(3,Set("res",Op(Sub,Num(0),Var("n"))));(0,Print(Var("Test")))];;
let block2 = [(5,Set("res",Var("n")))];;
let ifs = If(condi,block1,block2);;
let abs = [(1,Read("n"));(2,ifs);(6,Print(Var("res")))];;
let test = [(1,Set("a",Num(6))); (2,Set("b",Num(6))) ; (3,Set("c",Op(Add,Var("a"),Var("b")))) ; (4,Print(Var("c")))];;
(* reprint_polish abs 0;
printf "\n";; *)


(***********************************************************************)

let print_polish (p:program) : unit = reprint_polish p 0;;

let eval_polish (p:program) : unit = browse_program p [];;

let eval_vars (p:program) : unit = browse_program_vars p [] [];;

let usage () =
  print_string "Polish : analyse statique d'un mini-langage\n";
  print_string "Le programme prend les arguments suivants: <option> <fichier>\n";
  print_string "Options disponibles:\n";
  print_string "\t--reprint: Affiche le code contenu dans le fichier fourni en argument sans les commentaires\n";
  print_string "\t--eval: Exécute le programme\n";
  print_string "\t--vars: Affiche la liste des variables du programme et si celles-ci peuvent être accédées avant leur initialisation\n";
  print_string "\t--simpl: Effectue des simplifications évidentes sur le code\n"

let main () =
  match Sys.argv with
  | [|_;"--simpl";file|] -> print_polish (simplify  (read_polish file))
  | [|_;"--reprint";file|] -> print_polish (read_polish file)
  | [|_;"--eval";file|] -> eval_polish (read_polish file)
  | [|_;"--vars";file|] -> eval_vars (read_polish file)
  | _ -> usage ()

(* lancement de ce main *)
let () = main ()
