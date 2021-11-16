(** An image is a partial function from R^2 to colors.
    The abscisses grow from left to right.
    The ordonates from bottom to top.
*)
type image

(** [empty] has no point at all. *)
val empty : image

(** [white] is white everywhere. *)
val white : image

(** [circle] is black on the unit circle. *)
val circle : image

(** [line x0 y0 x1 y1 w] is a black line from (x0, y0) to (x1, y1)
    and width [w]. *)
val line : float -> float -> float -> float -> float -> image

(** [scale d i] is [i] magnified by [d]. *)
val scale : float -> image -> image

(** [rotate a i] is [i] rotated by [a]. *)
val rotate : float -> image -> image

(** [move dx dy i] is [i] shift by [dx] and [dy]. *)
val move : float -> float -> image -> image

(** [blend i j] produces the image where [i] is over [j]. *)
val blend : image -> image -> image

(** [blends] generalizes [blend] to a list of images. *)
val blends : image list -> image

(** [render i] shows [i] on the screen using the Cairo-Gtk canvas. *)
val render : image -> unit
