open AST

let parse content =
  let lexbuf = Lexing.from_string content in
  Parser.program Lexer.token lexbuf

type value =
  | VInt of int
  | VFun of identifier * term * env
  | VRecord of (label * value) list
  | VKApp of constructor * value list

and env = (identifier * value) list

let eval : AST.program -> (identifier * value) list =
fun p ->
  let rec program env p =
    let rec fold env values = function
      | [] -> values
      | d :: ds ->
         let (env, v) = toplevel_definition env d in
         fold env (v :: values) ds
    in
    fold env [] p
  and toplevel_definition env (ToplevelValue vdef) =
    let (x, v) = value_definition env vdef in
    let env = (x, v) :: env in
    (env, (x, v))
  and value_definition env = function
    | SimpleValue (x, t) ->
      (* let x = t *)
       (x, term env t)
    | RecFunction (_f, _xs, _t) ->
       (* let rec f x1 x2 ... = t *)
       failwith "todo"
  and term (env : (identifier * value) list) : term -> value = function
    | Lit (LInt x) ->
       VInt x
    | Var x ->
       List.assoc x env
    | Let (d, t) ->
       let (x, v) = value_definition env d in
       term ((x, v) :: env) t
    | Lam (x, t) ->
       (* fun x -> t *)
       VFun (x, t, env)
    | App (a, b) ->
       begin match term env a with
       | VFun (x, t, env) ->
          let vb = term env b in
          term ((x, vb) :: env) t
       | _ ->
          failwith "programme mal typé!"
       end
    | Record rs ->
       VRecord (List.map (fun (l, e) -> (l, term env e)) rs)

    | Proj (e, l) ->
       begin match term env e with
       | VRecord vs ->
          List.assoc l vs
       | _ ->
          failwith "programme mal typé"
       end
    | _ ->
       failwith "todo"
  in
  program [] p
