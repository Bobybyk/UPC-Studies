open Printf;;
open Types;;

let add_env name list_var : (name * int) list =
  if List.mem_assoc name list_var 
    then list_var
  else (name, 1)::list_var;; 

let rec is_var (e:expr) list_var list_var_unint : (((name * int) list) * ((name * int) list)) =
  match e with
    | Num(i) -> (list_var , list_var_unint)
    | Var(n) -> if List.mem_assoc n list_var then (list_var , list_var_unint) else (list_var , add_env n list_var_unint)
    | Op(o, e1, e2) -> let p = is_var e1 list_var list_var_unint in is_var e2 (fst p) (snd p)

let rec eval_instr_under_block (instr:instr) list_var list_var_unint : (((name * int) list) * ((name * int) list)) =
  match instr with 
    | Set(n,e) -> (list_var , (add_env n list_var_unint))
    | Read(n) -> (list_var , (add_env n list_var_unint))
    | Print(e) -> is_var e list_var list_var_unint
    | If(c, b1, b2) -> let p = browse_block b1 list_var list_var_unint in browse_block b2 (fst p) (snd p)
    | While(c, d) -> (browse_block d list_var list_var_unint)

and browse_block (block:block) list_var list_var_unint : (((name * int) list) * ((name * int) list)) =
  match block with
    | [] -> (list_var , list_var_unint)
    | instr::program' -> let p = eval_instr_under_block (snd instr) list_var list_var_unint in browse_block program' (fst p) (snd p)

let eval_instr (instr:instr) list_var list_var_unint : (((name * int) list) * ((name * int) list)) =
  match instr with 
    | Set(n,e) -> ((add_env n list_var) , list_var_unint)
    | Read(n) -> ((add_env n list_var) , list_var_unint)
    | Print(e) -> (list_var , list_var_unint) 
    | If(c, b1, b2) -> let p = browse_block b1 list_var list_var_unint in browse_block b2 (fst p) (snd p)
    | While(c, d) -> (browse_block d list_var list_var_unint)

let rec print_list_assoc list_var : unit =
  match list_var with
  | [] -> ()
  | e::l' -> printf "%s " (fst e) ; print_list_assoc l'

let rec browse_program_vars (program:program) list_var list_var_unint : unit =
  match program with
    | [] -> print_list_assoc list_var ; print_list_assoc list_var_unint ; printf "\n" ; print_list_assoc list_var_unint ; printf "\n"
    | instr::program' -> let p = eval_instr (snd instr) list_var list_var_unint in browse_program_vars program' (fst p) (snd p);;