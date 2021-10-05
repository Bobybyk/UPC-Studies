let rec size a = match a with
  | Nil -> 0
  | Node(n, g, d) -> (size g) + (size d) + 1;; 

let rec depth a = match a with
  | Nil -> 0
  | Node(n, Nil, Nil) -> 1
  | Node(n, g, d) -> max (depth g) (depth d) + 1;; 

let rec sum a = match a with
  | Nil -> 0
  | Node(n, g, d) -> n + sum g + sum d;;

let rec contains x a = match a with
  | Nil -> false
  | Node(n, g, d) -> if n = x or (contains x g) or (contains x d)
      then true
      else false;;

let rec elements a = match a with
  | Nil -> []
  | Node(n, g, d) -> [n]
  | Node(n, g, d) -> (elements g) @ (elements d);;

(*let rec perfect a = *)
