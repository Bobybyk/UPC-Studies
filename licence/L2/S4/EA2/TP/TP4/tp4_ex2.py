#!/usr/bin/env python3

#importe indirectement tp4, data et ea4lib
from tp4_ex1 import *
from random import randint
import matplotlib.pyplot as plt
import math


def permutation(n) :
    ''' retourne une permutation aléatoire de taille n selon la loi de
    probabilité uniforme '''
    l = [ (i + 1) for i in range(n) ]
    for i in range(n) :
        r = randint(i, n - 1)
        if i != r :
            l[i], l[r] = l[r], l[i]
    return l

#
# À COMPLÉTER !
#
def hauteur(arbre, r=None):
    ''' retourne la hauteur du sous-arbre de racine r de l'arbre 
    (en particulier, -1 pour l'arbre vide) '''
    if estVide(arbre) : 
      return -1
    if r == None : 
      r = racine(arbre)
    if estFeuilleVide(arbre, r) : 
      return 0

    gauche = droit = 0
    if not estFeuilleVide(arbre, filsGauche(arbre, r)) :
        gauche = hauteur(arbre, filsGauche(arbre, r))

    if not estFeuilleVide(arbre, filsDroit(arbre, r)) :
        droit = hauteur(arbre, filsDroit(arbre, r))

    if gauche > droit :
        return gauche
    else :
        return droit

#
# À COMPLÉTER !
#
def genererABRparInsertion(perm) :
    ''' renvoie un ABR construit par insertions successives des éléments
    de la permutation perm '''
    arbre = nouvelABRVide()
    for elt in perm :
        insertionABR(arbre, elt)
    return arbre
    
#
# À COMPLÉTER !
#
def statsHauteursABRparInsertion(n,m) :
    h = [m]
    for i in range(0, m) :
        perm = permutation(n)
        arbre = genererABRparInsertion(perm)
        h[i] = hauteur(arbre)
    return h



def tracer(limite, pas, m):
    '''trace la courbe des hauteurs, la hauteur moyenne et log(n) à base 2 '''
    lx, ly, ly_moy = [], [], []
    for i in range(1,limite,pas):
        tmp = statsHauteursABRparInsertion(i, m)
        if tmp == None: return
        lx.extend([i]*m)
        ly.extend(tmp)
        ly_moy.append(sum(tmp)/m)
    plt.plot([(math.log(i,2) if i>0 else 0) for i in range(limite)], color="blue")
    plt.plot(lx, ly, '.', color="orange")
    plt.plot(range(1,limite,pas), ly_moy, color="red")
    plt.ylabel('hauteur(n)')
    plt.xlabel('n = nombre noeuds')
    plt.title('Distribution des hauteurs d\'arbres aléatoires obtenus par n insertions')
    plt.show()
    return ()


#####################################################################
##  TESTS
#####################################################################

def testHauteur() :
  arbres = [arbreVide, arbre3ABR1, arbre3ABR2, arbre3ABR3, arbre10ABR1, arbre10ABR2, arbre100ABR1, arbre100ABR2] 
  res = [-1, 1, 2, 2, 9, 4, 12, 12] 
  score = 0
  print('Test hauteur')
  for i in range(len(arbres)):
    print (' - test %d/%d: ' % (i + 1, len(arbres)), end='')
    a = copier(arbres[i])
    if hauteur(a) == res[i]:
      printcol(" {ok}", "green")
      score += 1
    else:
        printcol(" {echec}", "red", end='')
        print(": obtenu ", hauteur(a), end='')
        print(" <> attendu ", res[i])
  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")



def testGenerer() :
  arbres = [arbreVide,arbreVide,arbreVide,arbreVide,arbreVide,arbreVide]
  elements = [[2,1,3],[1,2,3],[3,1,2],[1, 2, 4, 3] ,[1, 6, 3, 2, 5, 4], [4, 9, 8, 5, 6, 1, 3, 10, 7, 2]]
  res = res_generer()
  score = 0
  print('Test genereABRparInsertion')
  for i in range(len(arbres)):
    print (' - test %d/%d: ' % (i + 1, len(arbres)), end='')
    elt = elements[i]
    a = genererABRparInsertion(elt)
    if egalite(a,res[i]):
      printcol(" {ok}", "green")
      score += 1
    else:
        printcol(" {echec}", "red", end='')
        print(": obtenu ", a, end='')
        print(" <> attendu ", res[i])
  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")
    
if __name__ == '__main__':
    testHauteur()
    testGenerer()
    tracer(500,5,10)
    pass

