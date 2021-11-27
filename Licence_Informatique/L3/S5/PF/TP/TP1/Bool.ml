let is_zero x = x = 0;; 

let msg_zero x = if x = 0 then "zero" else "not zero";;

let my_max a b = if a>=b then a else b;;

let max_triple a b c = 
  let m = my_max a b in
  let m2 = my_max m c in
  m2;;
  
let max_quadruple a b c d = 
  let m = max_triple a b c in
  let m2 = my_max m d in
  m2;;
