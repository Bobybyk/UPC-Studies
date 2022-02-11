let liste = ref [] in
let ch = open_in (Sys.argv.(1)) in
  let lexbuf = Lexing.from_channel ch in
  let rec f (t:Token.token ) =
    match t with
    | DIGIT i -> (liste := i::(!liste) ) ; f (Lexeur2.lexeur lexbuf)
    | OTHER -> f (Lexeur2.lexeur lexbuf)
    | EOF -> List.iter print_int !liste; print_newline()
  in 
  f (Lexeur2.lexeur lexbuf);;
