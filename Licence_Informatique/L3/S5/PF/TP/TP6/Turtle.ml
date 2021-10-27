
let make_turtle x y = {x=x; y = y; angle = 0.; path = Path.empty |> Path.moveto x y};;

let forward dist trace t = let angle = t.angle *. Float.pi /. 180.  in
  let xn = t.x +. dist *. cos(angle) in
  let yn = t.y +. dist *. sin(angle) in
  {x=xn; y=yn; angle= t.angle; path = if trace then t.path |> Path.lineto xn yn  
                                 else t.path |> Path.moveto xn yn}
;;

let rec run cmds t = 
  match cmds with
  | [] -> t
  | x :: list -> match x with
    |Line (f) -> run list (forward f true t)
    |Move (f) -> run list (forward f false t)
    |Turn (f) -> run list {x=t.x; y = t.y; angle = t.angle +. f ; path = t.path}
    |Repeat(i, commands) -> if i = 0  then run list t else run (Repeat (i-1, commands)::list)  (run commands t) 
            
let triangle size = failwith "TODO"

let square size = failwith "TODO"

let polygon n size = failwith "TODO"

let spiral size factor angle n = failwith "TODO"
