type 'a t

val init : int -> (int * int -> 'a) -> 'a t

val get : 'a t -> (int * int) -> 'a

val size : 'a t -> int

val set : 'a t -> (int * int) -> 'a -> unit
