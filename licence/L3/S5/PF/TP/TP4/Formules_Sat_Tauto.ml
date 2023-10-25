let add_to_all x ll = List.map (fun l -> x::l) ll;;

let rec interpretations_props l = match l with
  | [] -> [[]]
  | e::l' -> let ll = (interpretations_props l') in (add_to_all (e, true) ll)
                                                    @(add_to_all(e, false) ll);;

let interpretations f = interpretations_props(list_of_props f);;

let satisfiable f = f;;
let tautology f = f;;
