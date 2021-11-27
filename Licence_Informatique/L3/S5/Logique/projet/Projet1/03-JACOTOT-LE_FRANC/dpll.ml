open List
open Printf

(* fonctions utilitaires *********************************************)
(* filter_map : ('a -> 'b option) -> 'a list -> 'b list
   disponible depuis la version 4.08.0 de OCaml dans le module List :
   pour chaque élément de `list', appliquer `filter' :
   - si le résultat est `Some e', ajouter `e' au résultat ;
   - si le résultat est `None', ne rien ajouter au résultat.
   Attention, cette implémentation inverse l'ordre de la liste *)
let filter_map filter list =
  let rec aux list ret =
    match list with
    | []   -> ret
    | h::t -> match (filter h) with
      | None   -> aux t ret
      | Some e -> aux t (e::ret)
  in aux list []

(* print_modele : int list option -> unit
   affichage du résultat *)
let print_modele: int list option -> unit = function
  | None   -> print_string "UNSAT\n"
  | Some modele -> print_string "SAT\n";
     let modele2 = sort (fun i j -> (abs i) - (abs j)) modele in
     List.iter (fun i -> print_int i; print_string " ") modele2;
     print_string "0\n"

(* ensembles de clauses de test *)
let exemple_3_12 = [[1;2;-3];[2;3];[-1;-2;3];[-1;-3];[1;-2]]
let exemple_7_2 = [[1;-1;-3];[-2;3];[-2]]
let exemple_7_4 = [[1;2;3];[-1;2;3];[3];[1;-2;-3];[-1;-2;-3];[-3]]
let exemple_7_8 = [[1;-2;3];[1;-3];[2;3];[1;-2]]
let systeme = [[-1;2];[1;-2];[1;-3];[1;2;3];[-1;-2]]
let coloriage = [[1;2;3];[4;5;6];[7;8;9];[10;11;12];[13;14;15];[16;17;18];[19;20;21];[-1;-2];[-1;-3];[-2;-3];[-4;-5];[-4;-6];[-5;-6];[-7;-8];[-7;-9];[-8;-9];[-10;-11];[-10;-12];[-11;-12];[-13;-14];[-13;-15];[-14;-15];[-16;-17];[-16;-18];[-17;-18];[-19;-20];[-19;-21];[-20;-21];[-1;-4];[-2;-5];[-3;-6];[-1;-7];[-2;-8];[-3;-9];[-4;-7];[-5;-8];[-6;-9];[-4;-10];[-5;-11];[-6;-12];[-7;-10];[-8;-11];[-9;-12];[-7;-13];[-8;-14];[-9;-15];[-7;-16];[-8;-17];[-9;-18];[-10;-13];[-11;-14];[-12;-15];[-13;-16];[-14;-17];[-15;-18]]

(********************************************************************)

(* simplifie : int -> int list list -> int list list 
   applique la simplification de l'ensemble des clauses en mettant
   le littéral i à vrai *)
let simplifie i clauses =
        List.map (fun x -> List.filter (fun y -> -i <> y) x) (List.filter (fun z -> not (List.mem i z)) clauses);;
        (*
         * for each list inside the list of lists 'clauses', check if it contains i
         * return a list of lists containing only the clauses that do not contain the litteral i
         * then remove the negation of i within the remaining clauses
         *)

(* solveur_split : int list list -> int list -> int list option
   exemple d'utilisation de `simplifie' *)
(* cette fonction ne doit pas être modifiée, sauf si vous changez 
   le type de la fonction simplifie *)
let rec solveur_split clauses interpretation =
  (* l'ensemble vide de clauses est satisfiable *)
  if clauses = [] then Some interpretation else
  (* un clause vide est insatisfiable *)
  if mem [] clauses then None else
  (* branchement *) 
  let l = hd (hd clauses) in
  let branche = solveur_split (simplifie l clauses) (l::interpretation) in
  match branche with
  | None -> solveur_split (simplifie (-l) clauses) ((-l)::interpretation)
  | _    -> branche

(* tests *)
(* let () = print_modele (solveur_split systeme []) *)
(* let () = print_modele (solveur_split coloriage []) *)

(* solveur dpll récursif *)
    
(* unitaire : int list list -> int
    - si `clauses' contient au moins une clause unitaire, retourne
      le littéral de cette clause unitaire ;
    - sinon, lève une exception `Not_found' *)
let unitaire clauses =
        List.hd (List.find (fun l -> length l = 1) clauses);;
        (*
         * Find the first clause that contains only one element (if no clauses matches that statement, an exception 'not found' is thrown
         * Return the first (and only) element of that clause
         *)
    
  (* DONE *)
(* pur : int list list -> int
    - si `clauses' contient au moins un littéral pur, retourne
      ce littéral ;
    - sinon, lève une exception `Failure "pas de littéral pur"' *)

(* on regarde tous les littéraux de lit_wo_dl et on les compare à ceux de clauses 
pour s'assurer que l'inverse de chacun n'est pas présent. Si on ne trouve pas un littéral
inverse de l'autre, on le retourne car il est pur, sinon on rappelle la fonction.
Si on atteint la fin de la liste, retourne un failwith *)
let rec is_pur x clauses = 
  match clauses with
  | [] -> true
  | down_list::clauses' -> if(List.mem (-x) down_list) then false 
    else is_pur x clauses';; 
 
let rec list_of_lit_wo_dl clauses lit_wo_dl = 
  match lit_wo_dl with
    | [] -> raise (Not_found)
    | e::lit_wo_dl' -> if (is_pur e clauses) = true then e 
      else list_of_lit_wo_dl clauses lit_wo_dl';;

(* on cherche à copier tous les littéraux de "clauses" dans une liste "lit_wo_dl"
 en s'assurant avant chaque ajout que le littéral (+ ou -) considéré n'est pas déjà présent dans
 "lit_wo_dl" *)
let rec copy_wo_dl_bis clause lit_wo_dl =
  match clause with
    | [] -> lit_wo_dl
    | e::clause' -> if (List.mem e lit_wo_dl) || (List.mem e lit_wo_dl) then copy_wo_dl_bis clause' lit_wo_dl else copy_wo_dl_bis clause' (e::lit_wo_dl);;

(* on parcourt les clauses et on appelle copy_wo_dl_bis avec chaque clause de clauses 
  et enfin, lorsque clausesIndex a été parcouru, on appekke list_of_clauses avec la
  copie des littéraux sans doublons et la liste des clauses *)
let pur clauses =
  let rec copy_wo_dl clauses clausesIndex lit_wo_dl =
  match clausesIndex with
    | [] -> list_of_lit_wo_dl clauses lit_wo_dl
    | down_list::up_list' -> copy_wo_dl clauses up_list' (copy_wo_dl_bis down_list lit_wo_dl) in copy_wo_dl clauses clauses [];;

(* end of pur sequence *)


(* solveur_dpll_rec : int list list -> int list -> int list option *)
let rec solveur_dpll_rec clauses interpretation =
  if clauses = [] then Some interpretation
  else if mem [] clauses then None 
  else try
    let cl_uni = (unitaire clauses) in (solveur_dpll_rec (simplifie cl_uni clauses) (cl_uni::interpretation) )
      with Not_found -> 
        try let lit_pur = (pur clauses) in (solveur_dpll_rec (simplifie lit_pur clauses) (lit_pur::interpretation) )
          with Not_found -> let l = List.hd (List.hd clauses) in
            let branche = solveur_dpll_rec (simplifie l clauses) (l::interpretation) in
              match branche with
                | None -> solveur_dpll_rec (simplifie (-l) clauses) ((-l)::interpretation)
                | _ -> branche

let () =
  let clauses = Dimacs.parse Sys.argv.(1) in
  print_modele (solveur_dpll_rec clauses [])
