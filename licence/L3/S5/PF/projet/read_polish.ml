open Types;;

(* Ici, via un chemin passé en argument, on récupère le contenu
 d'un fichier de type Polish dans une chaine de charactère. On va ensuite
 transférer le contenu de cette variable dans une liste de chaines de caractères 
 avec, pour chaque élément de cette liste, un "mot" identifié préalablement par 
 des caractères vides comme délimiteurs en début et fin
*)
let read_file (file:string) : (string list)=
  let ic = open_in file in
  let try_read () =
    try Some (input_line ic) with End_of_file -> None in
  let rec loop acc = match try_read () with
    | Some s -> loop (s :: acc)
    | None -> close_in ic; List.rev acc in
  loop [];;

(* Fonction appelée si l'instruction en préfix est de type READ.
  On renvoie donc e la suite de la ligne à lire*)
let collect_name line : name =
  match line with 
    | [] -> failwith("empty line")
    | e::line' -> e;;

(* Fonction pour évaluer si l'argument est un entier *)
let is_int str : bool=
  let verif_num n =
    try (int_of_string n |> string_of_int) = n
    with Failure _ -> false in 
  verif_num str;;

(* Ici on parcourt la ligne pour identifier les opérateurs qu'on va ajouter
  dans une structure Op avec ses valeurs *)
let rec collect_expr (line:string list) : expr =
  match line with
    | [] -> failwith("Unrecognized operator")
    | e::e'::l -> (match e with
      | "+" -> Op(Add, collect_expr (e'::l), collect_expr l)
      | "-" -> Op(Sub, collect_expr (e'::l), collect_expr l)
      | "*" -> Op(Mul, collect_expr (e'::l), collect_expr l)
      | "/" -> Op(Div, collect_expr (e'::l), collect_expr l)
      | "%" -> Op(Mod, collect_expr (e'::l), collect_expr l)
      | _ -> if is_int e then Num(int_of_string e) else Var(e))
    | e::l -> if is_int e then Num(int_of_string e) else Var(e)

let rec get_set_index (line: string list) (ind:int): int =
  match line with 
  | [] -> failwith("No affectation symbol found")
  | e::l -> (match e with
              | ":=" -> ind
              | _ -> get_set_index l (ind+1));;

(* Cette fonction retourne la chaine de caractères passée en argument à partir de l'index i *)
let rec get_string_from_i (str:'a list) (i:int) : 'a list =
  match str with
  |[] -> failwith("Empty")
  |e::l -> if i = 0 then l else get_string_from_i l (i-1);; 

(* retourne une initialisation de variable *)
let collect_set (line: string list) : instr =
  let index = get_set_index line 0 in
  Set((collect_name line) , (collect_expr (get_string_from_i line index ) ));;

(* Retourne l'indentation du couple  *)
let get_indentation (line:(int * string)) :int =
    let words = (String.split_on_char ' ' (snd line)) in 
    let rec aux (words: string list) acc = 
      match words with
      | [] -> acc
      | e::l -> if (String.equal e "") then (aux l (acc+1)) else acc in
      (aux words 0) / 2;;

(* Retourne le type d'opérateur de comparaison rencontré ainsi *)
let rec is_comp (str: string list) (ind:int) : (comp * int) = 
  (match str with
  | [] -> failwith("No comparator found")
  |e::l -> (match e with
            | "=" -> (Eq,ind)
            | "<" -> (Lt,ind)
            | "<=" -> (Le,ind)
            | ">" -> (Gt,ind)
            | ">=" -> (Ge,ind)
            | "<>" -> (Ne,ind)
            | _ -> is_comp l (ind+1)));;

let rec get_string_without_indentation (str: string list) = 
  match str with
  | [] -> []
  | e::l -> if (String.equal e "") then get_string_without_indentation l else str;;

let collect_cond (line:string list) : cond = 
  let op = is_comp line 0 in
    ((collect_expr line) , (fst op) , (collect_expr (get_string_from_i line (snd op))) );;

let rec collect_block (lines: (position * string) list) (ind:int) : (block * ((position * string) list) ) = 
  (match lines with
  | [] -> ([],[])
  | e::l -> if get_indentation e = ind 
            then 
              let first_instruction = (collect_instr lines) in
              let block = collect_block (snd first_instruction) ind in
              (((fst first_instruction)::(fst block)),(snd block))
            else
              ([],lines)) 

and

(* Ici on va chercher à collecter le type d'instruction du début de la ligne 
  passée en argument. Pour chaque type d'instruction identifiée, on crée la bonne
  structure avec les opérateurs et expressions associées *)
collect_instr (lines:(position * string) list ) : ( (position * instr) * ((position * string) list))=
  (match lines with
  | [] -> failwith("Empty line start collect instr")
  | e::l -> let line = snd e and 
              pos = fst e in
              let line_split = get_string_without_indentation (String.split_on_char ' ' line) and
              ind = get_indentation e in
            (match line_split with
             | [] -> failwith("Empty string split on char")
             | first::rest -> (match first with
                                | "READ" -> ( (pos,Read(collect_name rest)) ,l)
                                | "IF" -> (let condition = (collect_cond rest) in 
                                          let block_if = (collect_block l (ind+1)) in
                                          let block_else = (collect_else (snd block_if) (ind+1) ) in 
                                          ((pos,If(condition,(fst block_if),(fst block_else))),(snd block_else)) )  
                                | "WHILE" -> let condition = (collect_cond rest) and 
                                                block = (collect_block l (ind+1)) in 
                                                ( (pos,While(condition,(fst block))), (snd block))
                                | "PRINT" -> ( (pos,Print(collect_expr rest)) ,l)
                                | "COMMENT" -> (collect_instr l)
                                | _ -> ((pos,(collect_set line_split )),l) ))) 
and

collect_else (lines: (position * string) list) (ind:int) : (block * ((position * string) list)) =
  let rec build_collect_else_block (l:(position * string) list) (ind:int) : (block * ((position * string) list))=
    (match l with 
      | [] -> ([],l)
      | e::rest -> if get_indentation e = ind 
                    then 
                      let block = (build_collect_else_block rest ind ) in ((( fst (collect_instr l))::(fst block)),(snd block))
                    else
                      ([],l)
    ) in
  match lines with
  | [] -> ([],[])
  | e::l -> let block = (build_collect_else_block l ind) in ((fst block), (snd block));;

let read_polish (filename:string) : program = 
  let program = read_file filename in 
  let rec number_lines (prg: string list) acc : (position * string ) list =
      match prg with 
      | [] -> []
      | e::l -> (acc,e)::(number_lines l (acc+1)) in
  let lines_raw = number_lines program 1 in
  let rec browse_string_list (lines_to_parse:(position * string) list) : program=
    let res = (collect_instr lines_to_parse) in
    match (snd res) with
    | [] -> (fst res)::[]
    | _ -> (fst res)::(browse_string_list (snd res))
  in browse_string_list lines_raw;;
