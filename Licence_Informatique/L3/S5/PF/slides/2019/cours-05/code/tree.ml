(** A tree has from 2 to 5 branches. *)
let subtree_range = (2, 5)

(** We will generate random tree so we initialize the random generator. *)
let _initialize = Random.self_init ()

(** [random_range (start, stop)] returns a number between start and stop. *)
let random_range (start, stop) = start + Random.int (stop - start + 1)

(** [between low hi d] returns values from [low] to [hi] separated by [d]. *)
let rec between low hi d =
  if hi < low then [] else low :: between (low +. d) hi d

(** [steps n low hi] separates [low, hi] into [n] elements. *)
let steps n low hi =
  between low hi ((hi -. low) /. float_of_int n)

(** [subtree tree height angle] computes the branch of [tree] by
   rooting a rotated and smaller [tree] at the top of [tree]. *)
let subtree tree height angle =
  tree
  |> Image.scale (0.4 +. Random.float 0.4)
  |> Image.rotate angle
  |> Image.move 0. height

(** [make_tree d w h] computes a tree of depth [d], height [h]
    and width [w] recursively. *)
let make_tree depth width height =
  let trunk = Image.line 0. 0. 0. height width in
  let rec aux depth =
    if depth = 0 then trunk else
      let directions = steps (random_range subtree_range) (-1.) 1. in
      let tree = aux (depth - 1) in
      trunk :: List.map (subtree tree height) directions |> Image.blends
  in
  Image.white |> Image.blend (aux depth)

(** Let's draw a tree!*)
let main =
  make_tree 8 0.01 0.25 |> Image.move 0.5 0. |> Image.render
