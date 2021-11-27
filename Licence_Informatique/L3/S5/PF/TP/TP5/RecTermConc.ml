let rev_append left right = 
  let rec reverse l1 l2 =
    match l1 with
    | [] -> l2
    | x::l' -> reverse l' (x::l2)
  in reverse left right;;

let append left right =
  rev_append (List.rev left) right;;
