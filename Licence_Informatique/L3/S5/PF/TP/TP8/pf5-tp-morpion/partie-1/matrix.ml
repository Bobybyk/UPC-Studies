type 'a t = 'a array array

let init taille f =
  Array.init taille (fun i -> Array.init taille (fun j -> f (i,j)))

let get m (i, j) = m.(i).(j)

let size m = Array.length m

let set m (i,j) v = m.(i).(j) <- v
