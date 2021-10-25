(* Toutes les fonction sont ici définies par des "let rec". Il est
   possible que dans vos solutions certaines fonctions ne soient pas
   récursives, car elles utilisent une fonction recursive auxiliaire
   définie localement. À vous d'enlever le mot clef "rec" dans ce
   cas. *)

let rec list_length l =
  match l with
  | [] -> 0
  | e::l' -> 1 + list_length l';;

let rec list_product l =
  match l with
  | [] -> 1
  | e::l' -> e * list_product l';;

let rec mem x l =
  match l with
  | [] -> false
  | e::l' -> if e = x then true else mem x l';;

let rec list_min l =
  match l with
  | [] -> failwith "error: empty list" 
  | [e] -> e
  | e::l' -> if e < list_min l' then e else list_min l';;

let rec last l =
  match l with
  | [] -> failwith "error: empty list"
  | [e] -> e
  | e::l' -> last l';;

let rec is_sorted l =
  match l with 
  | [] -> true
  | [e] -> true
  | e::e'::l' -> if e < e' then is_sorted (e'::l') else false;;

let rec list_sum l = 
  match l with
  | [] -> 0
  | e :: l' -> e + list_sum l';; 

let rec average l = 
  match l with
  | [] -> failwith "error: empty list"
  | e :: l' -> (e + list_sum l') / (list_length (l') + 1);; 

let rec nth l k =
  match l with
  | [] -> failwith "Error: empty list"
  | [e] -> if k = 0 then e else failwith "Error: position does not exist"
  | e::l' -> if k = 0 then e else nth l' (k-1);;

let rec range n m =
  if n = m then [n] else
  if n > m then
    if n = (m+1) then [n;m]
    else
      n :: (range (n-1) m) 
  else 
  if n = (m-1) then [n;m]
  else n :: (range (n+1) m);;