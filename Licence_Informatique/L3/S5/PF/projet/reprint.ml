open Types;;
open Printf;;

let is_empty (ls: 'a list) : bool = List.length ls = 0;;

let rec reprint_polish (program:program) (ind_nbr:int) : unit= 
        let rec print_indentation ind =
                if ind > 0 then (printf "  " ; print_indentation (ind-1)) else printf "" and
        print_expr (expr:expr) =
                (match expr with 
                | Num(i) -> printf "%d " i
                | Var(n) -> printf "%s " n
                | Op(o,e1,e2) -> print_op o ; print_expr e1 ; print_expr e2) and
        print_op (op:op) = 
                (match op with
                | Add -> printf "+ " 
                | Sub -> printf "- " 
                | Mul -> printf "* "
                | Div -> printf"/ " 
                | Mod -> printf "%% ") and 
        print_cond (cond:cond) = 
                (match cond with
                | (e1,c,e2) -> print_expr e1 ; print_comp c ; print_expr e2) and
        print_comp (comp:comp) =
                (match comp with
                | Eq -> printf "= "
                | Ne -> printf "<> "
                | Lt -> printf "< "
                | Le -> printf "<= "
                | Gt -> printf "> "
                | Ge -> printf ">= ") and
        print_block (block:block) ind_nbr = 
                printf "\n" ; reprint_polish block ind_nbr and
        print_instr (instr:instr) ind_nbr =
                print_indentation ind_nbr ;
              (match instr with
                | Set(n,e) -> (printf "%s := " n ) ; (print_expr e)
                | Read(n) -> printf "READ %s" n 
                | Print(e) -> printf "PRINT " ; print_expr e 
                | If(c,b,b2) -> printf "IF " ; print_cond c ; print_block b (ind_nbr+1);  if not(is_empty b2) then (printf "\n" ; print_indentation ind_nbr ; printf "ELSE " ; print_block b2 (ind_nbr+1))
                | While(c,b) -> printf "WHILE " ; print_cond c ; print_block b (ind_nbr+1) ) in
        match program with
        | e::[] -> print_instr (snd e) ind_nbr
        | e::l -> print_instr (snd e) ind_nbr; printf "\n"  ; reprint_polish l ind_nbr
        | _ -> printf "";;
