#!/usr/bin/env python3

#importe indirectement tp4, data, ea4lib et tp4_ex* pour * < 2
from tp4_ex2 import *
from random import *

def suppressionABR(arbre, elt, alea=False) :
  ''' supprime le noeud d'étiquette elt dans l'arbre; en version
  déterministe, remplacement par le prédécesseur; sinon, à pile ou face '''
  i = rechercheABR(arbre, elt)
  if etiquette(arbre, i) != elt : return # l'element n'est pas dans l'arbre


  if estFeuilleVide(arbre, filsGauche(arbre, i)) and estFeuilleVide(arbre, filsDroit(arbre, i)) :
    if i == racine(arbre) :
      return nouvelABRVide()
    nettoieTab(arbre, [filsGauche(arbre, i), filsDroit(arbre, i)])
    setEtiquette(arbre, i, None)
    setFilsGauche(arbre, i , None)
    setFilsDroit(arbre, i, None)

  elif estFeuilleVide(arbre, filsGauche(arbre, i)) and not estFeuilleVide(arbre, filsDroit(arbre, i)) :
    setPere(arbre, filsDroit(arbre, i), pere(arbre, i))
    setFilsDuPere(arbre, i)
    if estRacine(arbre, i) :
      setRacine(arbre, filsDroit(arbre, i))
    nettoieTab(arbre, [filsGauche(arbre, i), i])

  elif not estFeuilleVide(arbre, filsGauche(arbre, i)) and estFeuilleVide(arbre, filsDroit(arbre, i)) :
    setPere(arbre, filsGauche(arbre, i), pere(arbre, i))
    setFilsDuPere(arbre, i)
    if estRacine(arbre, i) :
      setRacine(arbre, filsGauche(arbre, i))
    nettoieTab(arbre, [i, filsDroit(arbre, i)])
  else :
    if alea :
      a = random(0, 2)
      if a == 0 :
        supprWithSuc(arbre, i)
      else :
        supprWithPred(arbre, i)
    else :
      supprWithSuc(arbre, i)

def setFilsDuPere(arbre, i) :
    if (estFilsGauche(arbre, i)) :
      setFilsGauche(arbre, pere(arbre, i), filsGauche(arbre, i))
    else :
      setFilsDroit(arbre, pere(arbre, i), filsDroit(arbre, i))



def supprWithPred(arbre, i) :
  pred = findPred(arbre, i)
  setPere(arbre, filsGauche(arbre, pred), pere(arbre, pred))
  setFilsDuPere(arbre, i)
  if estRacine(arbre, i) :
    setRacine(arbre, filsGauche(arbre, i))
  setEtiquette(arbre, i, etiquette(arbre, pred))
  nettoieTab(arbre, [i, filsDroit(arbre, i)])

def supprWithSuc(arbre, i) :
  suc = findSuc(arbre, i)
  setPere(arbre, filsDroit(arbre, suc), pere(arbre, suc))
  setFilsDuPere(arbre, i)
  if estRacine(arbre, i) :
    setRacine(arbre, filsDroit(arbre, i))
  setEtiquette(arbre, i, etiquette(arbre, suc))
  nettoieTab(arbre, [filsGauche(arbre, i), i])

def findPred(arbre, noeud) :
  if not estFeuilleVide(arbre, filsGauche(arbre, noeud)) :
    return maximumABR(arbre, filsGauche(arbre, noeud))
  while (not estFeuilleVide(arbre, pere(arbre, noeud))) and estFilsGauche(arbre, noeud) :
    noeud = pere(arbre, noeud)
  return noeud

def findSuc(arbre, noeud) :
  if not estFeuilleVide(arbre, filsDroit(arbre, noeud)) :
    return minimumABR(arbre, filsDroit(arbre, noeud))
  while (not estFeuilleVide(arbre, pere(arbre, noeud))) and estFilsDroit(arbre, noeud) :
    noeud = pere(arbre, noeud)
  return noeud

#####################################################################
##  TESTS
#####################################################################

def testSuppression():
  arbres = [arbre3ABR1, arbre3ABR1, arbre10ABR2, arbre10ABR2, arbre100ABR1, arbre100ABR1, arbre100ABR1, arbre100ABR1]
  elements = [1, 4, 3, 7, 55, 1, 49, 43]
  res = res_suppression()
  score = 0
  print('Test Suppression')
  for i in range(len(arbres)):
    print (' - test %d/%d: ' % (i + 1, len(arbres)), end='')
    a = copier(arbres[i])
    elt = elements[i]
    suppressionABR(a,elt)
    if egalite(a,res[i]):
      printcol(" {ok}", "green")
      score += 1
      #dessineArbreBinaire(a,'obtenu_'+str(i))
    else:
        printcol(" {echec}", "red", end='')
        #print(": obtenu ", a, end='')
        #print(" <> attendu ", res[i])
        dessineArbreBinaire(a,'obtenu_'+str(i))
        dessineArbreBinaire(res[i], 'attendu_'+str(i))
        dessineArbreBinaire(arbres[i], 'depart_'+str(i))

  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")

if __name__ == '__main__':
  testSuppression()
