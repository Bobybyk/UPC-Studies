let bis s = s^s;;


let times8 s =
  let d  = s^s in
  let dd = d^d in
  dd^dd;;

let times8_bis s = bis(bis(bis(s)));;
    
