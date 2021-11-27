let rec string_of_formula f = match f with 
  | Prop s -> s
  | And (g, h) ->  "("^string_of_formula g^" and "^string_of_formula h^")"
  | Or (g, h) -> "("^string_of_formula g^" Or "^string_of_formula h^")"
  | Neg d -> "Neg "^string_of_formula d;; 

let rec list_of_props f = match f with
  | Prop s -> [s]
  | And(g, h) -> (union_sorted (list_of_props g) (list_of_props h))
  | Or(g, h) -> (union_sorted (list_of_props g) (list_of_props h))
  | Neg d -> list_of_props d;;

let rec eval_formula f l = match f with
  | Prop s -> List.assoc s l
  | And(g, h) -> (eval_formula g l) && (eval_formula h l) 
  | Or(g, h) -> (eval_formula g l) || (eval_formula h l) 
  | Neg d -> not(eval_formula d l);;