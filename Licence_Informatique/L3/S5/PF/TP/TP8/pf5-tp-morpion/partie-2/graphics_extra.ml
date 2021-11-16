open Graphics

let draw_string_at x y s =
  moveto x y;
  (try set_font "-bitstream-*-*-r-normal-*-40-*-*-*-*-*-*-*" with _ ->
   try set_font "-*-fixed-medium-r-semicondensed-*-50-*-*-*-*-*-iso8859-1"
   with _ -> ());
  draw_string s

let create_window w h =
  open_graph (" " ^ string_of_int w ^ "x" ^ string_of_int h);
  auto_synchronize false

let close_after_event () =
  ignore (wait_next_event [Button_down ; Key_pressed])
