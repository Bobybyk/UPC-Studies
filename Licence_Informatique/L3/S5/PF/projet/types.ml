(*****************************************************************************)
(** Syntaxe abstraite Polish (types imposés, ne pas changer sauf extensions) *)

(** Position : numéro de ligne dans le fichier, débutant à 1 *)
type position = int

(** Nom de variable *)
type name = string

(** Opérateurs arithmétiques : + - * / % *)
type op = Add | Sub | Mul | Div | Mod

(** Expressions arithmétiques *)
type expr =
  | Num of int
  | Var of name
  | Op of op * expr * expr

(** Opérateurs de comparaisons *)
type comp =
| Eq (* = *)
| Ne (* Not equal, <> *)
| Lt (* Less than, < *)
| Le (* Less or equal, <= *)
| Gt (* Greater than, > *)
| Ge (* Greater or equal, >= *)

(** Condition : comparaison entre deux expressions *)
type cond = expr * comp * expr

(** Instructions *)
type instr =
  | Set of name * expr
  | Read of name
  | Print of expr
  | If of cond * block * block
  | While of cond * block
and block = (position * instr) list

(* Un programme Polish est un bloc d'instructions *)
type program = block

(* Fonction pour évaluer si l'argument est un entier *)
let is_int str : bool=
  let verif_num n =
    try (int_of_string n |> string_of_int) = n
    with Failure _ -> false in 
  verif_num str;;

(* Ici on évalue la valeur de vérité de deux valeurs en fonction d'un opérateur logique *)
let eval_comp condition =
  let compare val1 comp_type val2 =
    match comp_type with
      | Eq -> val1 = val2
      | Ne -> val1 = val2
      | Lt -> val1 < val2
      | Le -> val1 <= val2
      | Gt -> val1 > val2
      | Ge -> val1 >= val2
    in match condition with (val1, comp_type, val2) -> compare val1 comp_type val2;;
