open Types;;
open Printf;;

let is_num (e:expr) : bool = 
        match e with
        | Num(i) -> true
        | _ -> false;;

let is_empty (l: 'a list) : bool =
        match l with
        | [] -> true
        | _ -> false;;

let get_value (e:expr) : int = 
        match e with
        | Num(e) -> e
        | _ -> failwith("Error: not a num");;

let calculate_num (e:expr) : expr =
        match e with 
        | Op(o,e1,e2) -> let a = get_value e1 and
                        b =  get_value e2 in
                (match o with
                | Add -> Num((a + b))
                | Sub -> Num((a - b))
                | Mul -> Num((a * b))
                | Div -> Num((a / b))
                | Mod -> Num((a mod b)))
        | _ -> failwith("calculate num: no Op found");;

(* simplifies an expression in which not all operands are nums *)
let calculate_constants (e:expr) : expr =
        match e with 
        | Op(o,e1,e2) -> if is_num e1 && is_num e2 then calculate_num e         (* if both sides are a Num, simplify the expr *)
                         else (match o with                                     (* else check for the Op *)
                                | Add -> if not(is_num e1) && is_num e2         (* if Add, simplify only when adding zero *)
                                                then if (get_value e2) = 0 then e1
                                                else e
                                        else if (is_num e1) && not(is_num e2)
                                                then if (get_value e1) = 0 then e2
                                                else e
                                        else e
                                | Div -> if (is_num e1) && not(is_num e2) (* if Div, simplify when dividing zero *)
                                                then if (get_value e1) = 0 then Num(0)
                                                else e
                                        else e
                                | Mul -> if not(is_num e1) && is_num e2         (* if Mul, simplify when multiplying by one and zero*)
                                                then if (get_value e2) = 0 then Num(0) 
                                                else if (get_value e2) = 1 then e1
                                                else e
                                        else if (is_num e1) && not(is_num e2)
                                                then if (get_value e1) = 0 then Num(0)
                                                else if (get_value e1) = 1 then e2
                                                else e
                                        else e

                                | _ -> failwith("TODO")) 
        | _ -> e;;
        
(* simplify an expression *)
let rec simplify_expr (e: expr) : expr =
        (match e with
        | Op(o,e1,e2) -> let ne1 = (is_num (simplify_expr e1)) and
                             ne2 =  (is_num (simplify_expr e2)) in
                         if ne1 && ne2          (* if both expressions are a Num *) 
                         then 
                                 calculate_num e 
                         else if ne1 && not(ne2)        (* if only the left expr is a Num *)
                         then 
                                (calculate_constants e) 
                         else if not(ne1) && ne2        (* if only the right expr is a Num *)
                         then
                               (calculate_constants e) 
                         else                           (* if neither are a Num *)
                                e 
        | _ -> e);;

(* This function is used to simplify the expressions inside a condition *)
let simplify_condition (c:cond) : cond = 
        match c with
        | (e1,c,e2) -> ((simplify_expr e1),c,(simplify_expr e2));;

(*handles the constants propagatioon in order to simplify expressions *)
let rec propagate_aux (p: (position * instr) ) : (position * instr) = 
        match (snd p) with
        | Set(n,e) -> ((fst p),Set(n,(simplify_expr e)))
        | If(c,b1,b2) -> if (is_empty b2) 
                                then ((fst p), If((simplify_condition c),(propagate b1),[]))
                                else ((fst p),If((simplify_condition c),(propagate b1),(propagate b2)))
        | While(c,b) -> ((fst p),While((simplify_condition c),(propagate b)))
        | _ -> p

and propagate (p:program) : program = 
        match p with
        | [] -> failwith("Empty program")
        | e::[] -> (propagate_aux e)::[]
        | e::l -> (propagate_aux e)::(propagate l);;

let is_comp_only_nums (c:cond) : bool =
        match c with
        | (e1,c,e2) -> (is_num e1) && (is_num e2);;

(* dead code suppression aux function *)
let dead_code_aux (bl: (position * instr)) : block =
        match (snd bl) with                                     (* evaluate the condition truth value *)
        | If(c,b1,b2) -> if is_comp_only_nums c then (if eval_comp c then b1 else b2) else bl::[]        (* for the if chooses the right block *)
        | While(c,b) -> if is_comp_only_nums c then (if eval_comp c then bl::[] else []) else bl::[]     (* for the while removes or not the block*)
        | _ -> bl::[];;

(* dead code suppression main function *)
let rec dead_code_sup (p:program) : program = 
        match p with
        | [] -> failwith("Empty program")
        | e::[] -> (dead_code_aux e)@[]
        | e::l -> (dead_code_aux e)@(dead_code_sup l);;

(* Main function usable froom outside *)
let simplify (p:program) : program = 
        dead_code_sup (propagate p);;
