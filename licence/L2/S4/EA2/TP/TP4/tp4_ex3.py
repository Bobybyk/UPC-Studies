#!/usr/bin/env python3

#importe indirectement tp4, data, ea4lib et tp4_ex* pour * < 2
from tp4_ex2 import *

#
# À COMPLÉTER !
#
def suppressionABR(arbre, elt, alea=False) :
  ''' supprime le noeud d'étiquette elt dans l'arbre; en version
  déterministe, remplacement par le prédécesseur; sinon, à pile ou face '''


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

  printcol ('  score {%d/%d} ' % (score, len(arbres)), "cyan")
    
if __name__ == '__main__':
  testSuppression()
