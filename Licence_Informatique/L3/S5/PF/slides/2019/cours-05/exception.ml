exception NotFound of int

let index_of' v vs =
  let rec aux i = function
    | [] -> None
    | x :: xs -> if v = x then Some i else aux (i + 1) xs
  in
  aux 0 vs

let index_of v vs =
  let rec aux i = function
    | [] -> raise (NotFound v)
    | x :: xs -> if v = x then i else aux (i + 1) xs
  in
  aux 0 vs

let before x y l =
  index_of x l < index_of y l

let safe_before x y l =
  try
    before x y l
  with NotFound x ->
     false
    

let before' x y l =
  match index_of' x l, index_of' y l with
  | Some i, Some j -> i < j
  | None, _ | _, None -> false

