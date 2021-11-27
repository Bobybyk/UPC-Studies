#!/bin/sh
    
if [ -e 8.1_morpion_debut.ml ]; then
    cp -f 8.1_morpion_debut.ml morpion_debut.ml
fi
if [ -e 8.2_morpion_victoire.ml ]; then
    cp -f 8.2_morpion_victoire.ml morpion_victoire.ml
fi

if dune --version ; then
  dune exec ./morpion.exe
else
  echo "Attention : dune non disponible. Essai de compilation directe..."
  set -e
  if [ -e $(ocamlc -where)/graphics.cma ]; then
    GRAPHICS=""
  else
    GRAPHICS="-I $(ocamlfind query graphics)"
  fi
  mkdir -p _build
  cp -f *.mli *.ml _build/
  cd _build
  ocamlc -c matrix.mli matrix.ml morpion_prelude.ml
  ocamlc -c -open Morpion_prelude morpion_debut.ml morpion_victoire.ml
  ocamlc $GRAPHICS -c graphics_extra.ml
  ocamlc $GRAPHICS -c -open Graphics_extra -open Morpion_prelude -open Morpion_debut -open Morpion_victoire morpion.ml
  ocamlc $GRAPHICS graphics.cma graphics_extra.cmo matrix.cmo morpion_prelude.cmo morpion_debut.cmo morpion_victoire.cmo morpion.cmo -o morpion.byte
  ./morpion.byte
fi