#!/bin/sh
## Pour convertir les fichiers markdown en pdf, installer pandoc puis lancer ce script
for i in ../*.md; do pandoc -V linkcolor -H header.tex $i -o $(basename $i .md).pdf; done
