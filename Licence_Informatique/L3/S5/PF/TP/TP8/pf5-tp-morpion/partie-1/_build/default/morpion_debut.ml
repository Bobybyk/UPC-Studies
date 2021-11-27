let plateau_initial (taille : int) : plateau =
  Matrix.init taille (fun(i,j)->None);;

let in_plateau x y size = x > 0 && x<size && y>0 && y<size
;;

let coup_legal (plateau : plateau) (coup : coup) : bool =
  if Matrix.get plateau coup.position = None then true 
  else false
;;

let prepare_coup
    (plateau : plateau)
    (dernier_coup : coup option)
    (nb_joueurs : int)
    (x , y : int*int)
  : coup option = 
  let size = Matrix.size plateau in
  if (in_plateau x y size)
  then 
    let js = match dernier_coup with
      | None -> 0 
      | Some c -> if c.joueur = nb_joueurs-1 then 0 else c.joueur+1 
    in Some {joueur = js ; position = (x, y)} 
  else None
;;