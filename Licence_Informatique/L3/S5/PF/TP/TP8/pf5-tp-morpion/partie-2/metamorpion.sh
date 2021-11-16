#!/bin/sh

if [ -e 8.1_morpion_debut.ml ]; then
    mv -v 8.1_morpion_debut.ml morpion_debut.ml
fi
if [ -e 8.2_morpion_victoire.ml ]; then
    mv -v 8.2_morpion_victoire.ml morpion_victoire.ml
fi
if [ -e 8.3_metamorpion_debut.ml ]; then
    mv -v 8.3_metamorpion_debut.ml metamorpion_debut.ml
fi
if [ -e 8.4_metamorpion_victoire.ml ]; then
    mv -v 8.4_metamorpion_victoire.ml metamorpion_victoire.ml
fi

if dune --version ; then
  dune exec ./metamorpion.exe
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
  ocamlc -c matrix.mli matrix.ml morpion_victoire.ml metamorpion_prelude.ml
  ocamlc -c -open Metamorpion_prelude metamorpion_debut.ml metamorpion_victoire.ml
  ocamlc $GRAPHICS -c graphics_extra.ml
  ocamlc $GRAPHICS -c -open Graphics_extra -open Metamorpion_prelude -open Metamorpion_debut -open Metamorpion_victoire metamorpion.ml
  ocamlc $GRAPHICS graphics.cma graphics_extra.cmo matrix.cmo \
       morpion_victoire.cmo metamorpion_prelude.cmo \
       metamorpion_debut.cmo metamorpion_victoire.cmo \
       metamorpion.cmo -o metamorpion.byte
  ./metamorpion.byte
fi
