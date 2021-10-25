let listn1 n = 
  let rec recList i l =
    if i > n then l
    else recList (i+1) (i::l) in recList 0 [];; 

let length1 l =
  let rec recLength n l = 
    match l with
    | [] -> n
    | e::l' -> recLength (n+1) l'
  in recLength 0 l;;
