open Vg
open Gg

type image =
  Vg.image

let empty =
  I.void

let white =
  I.const Color.white

let circle =
  let gray = I.const (Color.gray 0.5) in
  let circle = P.empty |> P.circle (P2.v 0.5 0.5) 0.4 in
  I.cut circle gray

let line x0 y0 x1 y1 w =
  let rel = true in
  let path =
    P.empty |>
    P.sub (P2.v x0 y0) |>
    P.line ~rel (P2.v (x1 -. x0) (y1 -. y0))
  in
  let area = `O { P.o with P.width = w } in
  I.const Color.black |> I.cut ~area path

let scale s =
  I.scale (P2.v s s)

let rotate =
  I.rot

let move dx dy =
  I.move (P2.v dx dy)

let blend =
  I.blend

let rec reduce f = function
  | [] -> empty
  | [x] -> x
  | x :: xs -> f x (reduce f xs)

let blends = reduce blend

let render image =
  ignore(GMain.init ());

  let aspect = 1.618 in
  let dim = 100. in
  let size = Size2.v (aspect *. dim) dim (* mm *) in
  let view = Box2.v P2.o (Size2.v aspect 1.) in
  let res = 300. /. 25.4 (* 300dpi in dots per mm *) in
  let w = int_of_float (res *. Size2.w size) in
  let h = int_of_float (res *. Size2.h size) in

  let w = GWindow.window ~title:"Image" ~width:w ~height:h () in
  ignore(w#connect#destroy ~callback:GMain.quit);

  let d = GMisc.drawing_area ~packing:w#add () in

  let expose _ev =
    let ctx = Cairo_gtk.create d#misc#window in
    Cairo.scale ctx res res;
    let surface = Cairo.get_target ctx in
    let target = Vgr_cairo.target ctx in
    let warn w = Vgr.pp_warning Format.err_formatter w in
    let r = Vgr.create ~warn target `Other in
    ignore (Vgr.render r (`Image (size, view, image)));
    ignore (Vgr.render r `End);
    Cairo.Surface.flush surface;
    Cairo.Surface.finish surface;
    true
  in

  ignore(d#event#connect#expose ~callback:expose);

  w#show ();
  GMain.main ()
