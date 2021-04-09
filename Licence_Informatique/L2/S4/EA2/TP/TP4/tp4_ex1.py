#!/usr/bin/env python3

from tp4 import *
from data import *
from ea4lib import printcol

#
# À COMPLÉTER !
#
def parcoursInfixe(arbre, i=None) :
  ''' renvoie la liste des étiquettes du sous-arbre de racine i en ordre
  infixe (et de l'arbre entier si i vaut None) '''
  return None


#
# À COMPLÉTER !
#
def estUnABR(arbre) :
  if (arbre[1] != None):
    arbreGauche = arbre[1]
    if arbreGauche[0] > arbre[0]:
      return False
    estUnABR(arbreGauche)
  elif (arbre[2] != None):
    arbreDroit = arbre[2]
    if arbreDroit[0] < arbre[0]:
      return False
    estUnABR(arbreDroit)
  return True


#
# À COMPLÉTER !
#
def minimumABR(arbre, noeud=None) :
  ''' retourne l'indice du noeud d'étiquette minimale dans le sous-arbre
  de racine noeud de arbre (en supposant que arbre est un ABR) '''
  if estVide(arbre) : 
    return None
  if noeud == None : 
    noeud = arbre[0]
  while filsGauche(arbre, noeud) != None :
    noeud = filsGauche(arbre, noeud)
  return pere(arbre, noeud)

  
#
# À COMPLÉTER !
#
def maximumABR(arbre, noeud=None) :
  ''' retourne l'indice du noeud d'étiquette maximale dans le sous-arbre
  de racine noeud de arbre (en supposant que arbre est un ABR) '''
  if estVide(arbre) : 
    return None
  if noeud != None : 
    noeud = arbre[0]
  while filsDroit(arbre, noeud) != None :
      noeud = filsDroit(arbre, noeud)
  return pere(arbre, noeud)

#
# À COMPLÉTER !
#
def rechercheABR(arbre, elt) :
  ''' retourne l'indice du noeud contenant elt, ou la feuille où la recherche
  échoue (en supposant que arbre est un ABR) '''
  return aux(arbre, racine(arbre), elt)

def aux(arbre, noeud, elt) :
    if estFeuilleVide(arbre, noeud) :
        return noeud
    if etiquette(arbre, noeud) == elt :
        return noeud
    elif etiquette(arbre, noeud) < elt :
        return aux(arbre, filsDroit(arbre, noeud), elt)
    else :
        return aux(arbre, filsGauche(arbre, noeud), elt)

#
# À COMPLÉTER !
#
def insertionABR(arbre, elt) :
  if elt == None : 
    return
  indice = rechercheABR(arbre, elt)
  if estFeuilleVide(arbre, indice) :
      insertionAux(arbre, racine(arbre), elt)

def insertionAux(arbre, noeud, elt) :
  if estFeuilleVide(arbre, noeud) :
    setEtiquette(arbre, noeud, elt)
    gauche = nouvelleFeuilleVide(arbre, noeud)
    droit = nouvelleFeuilleVide(arbre, noeud)
    setFilsGauche(arbre, noeud, gauche)
    setFilsDroit(arbre, noeud, droit)
  elif elt < etiquette(arbre, noeud) :
    insertionAux(arbre, filsGauche(arbre, noeud), elt)
  else :
    insertionAux(arbre, filsDroit(arbre, noeud), elt)



#####################################################################
##  TESTS
#####################################################################

def contientABR(arbre, elt) :
  ''' teste si arbre contient elt (supposant que arbre est un ABR)'''
  noeud = rechercheABR(arbre, elt)
  return False if noeud==None else etiquette(arbre, noeud) == elt

def testData():
  return  [arbreVide, arbre3ABR1, arbre3ABR2, arbre3ABR3, arbre10ABR2, arbre100ABR1, arbre100ABR2]

def testEstABR():
  print('Test estUnABR:')
  tests=[(completeArbreBinaire((0,[[7,1,2,0],[4,None,None,0],[10,3,4,0],[6,None,None,2],[12,None,None,2]])), False), (arbre10ABR1, True), (arbre100ABR2, True),(arbre100notABR, False)]
  for i, (a, val) in enumerate(tests):
    print (' - test %d/%d: ' % (i + 1, len(tests)), end='')
    res = estUnABR(a)
    if res != val:
      printcol(" {echec}", "red", end='')
      print(": obtenu ", res, end='')
      print(" <> attendu ", val)
    else:
      printcol(" {ok}", "green")

def testResults():
  return [[minimumABR, 0,[None, 1, 0, 2, 7, 5, 3]],
          [maximumABR, 0, [None, 2, 2, 0, 2, 16, 120]],
          [rechercheABR, 1, [1,27,3,57,4,100,200],[0,6,2,3,6,16,156]],
        #  [contientABR, 1, [1,27,3,57,4,100,200],[False,False,True,False,True, True,False]]                   
]

def testAll() :
  tst = testResults()
  arbres = testData()
  print('Arbres : ')
  for j in range(len(arbres)) :
    print(arbres[j])
    dessineArbreBinaire(arbres[j],'./arb_'+str(j))
 
  for i in range(len(tst)) :
    fname = tst[i][0]
    farg = tst[i][1]
    fres = tst[i][2 + farg]
    score = 0
    print('Test %s:' % fname.__name__)
    for j in range(len(arbres)) :
      a = arbres[j]
      print (' - test %d/%d: ' % (j + 1, len(arbres)), end='')
      res = fres[j]
      if (farg == 0) :
        res = fname(a)
      elif (farg == 1) :
        res = fname(a,tst[i][2][j])
      if (res == fres[j]) :
        printcol(" {ok}", "green")
        score += 1
      else :
        printcol(" {echec}", "red", end='')
        print(": obtenu ", res, end='')
        print(" <> attendu ", fres[j])
    printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")

def testInsertion():
  arbres = [arbreVide, arbre3ABR1, arbre3ABR2, arbre3ABR3, arbre100ABR1, arbre100ABR2]
  elements = [4,4,2,10,27,123]
  res = res_insertion()
  score = 0
  print('Test Insertion')
  for i in range(len(arbres)):
    print (' - test %d/%d: ' % (i + 1, len(arbres)), end='')
    a = copier(arbres[i])
    elt = elements[i]
    insertionABR(a,elt)
    if egalite(a,res[i]):
      printcol(" {ok}", "green")
      score += 1
    else:
        printcol(" {echec}", "red", end='')
        print(": obtenu ", a, end='')
        print(" <> attendu ", res[i])
  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")

def testInsertion2():
  elements=[7,3,9,65,12,1,5,8,13]
  arbre = nouvelABRVide()
  for elt in elements:
    insertionABR(arbre, elt)
  dessineArbreBinaire(arbre,"monarbre")
  res = arbreBinaireDeFichier("arbre_insertions.txt")
  print("Test insertions successives a partir d'un arbre vide:", end='')
  if egalite(arbre,res):
      printcol(" {ok}", "green")
  else:
      printcol(" {echec}", "red", end='')
      print(": obtenu ", arbre, end='')
      print(" <> attendu ", res)
    
if __name__ == '__main__':
  testEstABR()
  testAll()
  testInsertion()
  testInsertion2()
