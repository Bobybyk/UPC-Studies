#!/usr/bin/env python3

import sys

version = sys.version_info
if version.major < 3:
    sys.exit(
        "Python2 n'est PLUS supporté depuis le 1er Janvier 2020, merci d'installer Python3"
    )

import random
from time import process_time as clock

try:
    import matplotlib.pyplot as plt
except ModuleNotFoundError:
    sys.exit("Le module matplolib est nécessaire pour ce TP.")

############################################################
# Exercice 1.1
#
# Tri selection
#

def min(T, a, b):
    minVal = a
    for i in range(a+1, b):
        if T[i] < T[minVal]:
            minVal = i
    return minVal

def triSelection(T):
    for i in range(len(T)-1):
        j = min(T, i, len(T))
        T[i],T[j] = T[j], T[i]
    return T

############################################################
# Exercice 1.2
#
# randomPerm prend en paramètre un entier n et renvoie une
# permutation aléatoire de longueur n
#

def randomPerm(n):
    tab = [0] * n
    for i in range(n):
        tab[i] = i+1  
    for i in range(len(tab)-1):
        j = random.randint(i, n-1)
        tab[i], tab[j] = tab[j], tab[i]
    return tab

############################################################
# Exercice 1.3
#
# testeQueLaFonctionTrie prend en paramètre une fonction de
# tri f et l’applique sur des permutations aléatoires de
# taille i variant de 2 à 100 et vérifie que le résultat est
# un tableau trié
#

def testeQueLaFonctionTrie(f):
    size = random.randint(2,100)
    T = f(randomPerm(size))

    for i in range(len(T)-1):
        if T[i] > T[i+1]:
            return False
    return True

############################################################
# Exercice 1.4
#
# randomTab prend des entiers n, a et b et renvoie un tableau
# aléatoire de taille n contenant des entiers compris entre
# les bornes a et b.
#

def randomTab(n,a,b):
    T = [0]*n
    for i in range(n):
        T[i] = random.randint(a, b)
    return T

############################################################
# Exercice 1.5
#
# derangeUnPeu prend des entiers n, k et un booleen rev et
# effectue k échanges entre des positions aléatoires sur la
# liste des entiers de 1 à n si rev vaut False ou sur la
# liste des entiers n à 1 si rev vaut True.
#

def derangeUnPeu(n,k,rev):
    T = [ n - i for i in range(n) ] if rev else [ i + 1 for i in range(n) ]
    if (rev == False):
        while(k != 0):
            i = random.randint(1, n)-1
            j = random.randint(1, n)-1
            T[i], T[j] = T[j], T[i]
            k-=1
    if (rev == True):
        while(k != 0):
            i = random.randint(1, n)-1
            j = random.randint(1, n)-1
            T[i], T[j] = T[j], T[i]
            k-=1
    return T


############################################################
# Exercice 2.1
#
# Trois variantes du tri par insertion : Avec échanges successifs,
# insertion directe à la bonne position, et avec recherche
# dichotomique de la positio
#

def triInsertionEchange(T):
    for i in range(1, len(T)): 
        k = T[i] 
        j = i-1
        while j >= 0 and k < T[j] : 
                T[j + 1] = T[j] 
                j -= 1
        T[j + 1] = k
    return T

def triInsertionRotation(T):
    for i in range(1, len(T)):
        j = i
        for k in range(i,0,-1):
            if(T[k-1]>T[i]):
                j -= 1
        toMove = T[i]
        T = decaler(T,debut = k, fin = i)
        T[j] = toMove
    return T

def decaler(T, debut, fin):
    for i in range(fin,debut,-1):
        T[i] = T[i-1]
    return T

def triInsertionRapide(T):
    n = len(T)
    if n < 2:
        return T
    else:
        m = n//2
        return fusion(triInsertionRapide(T[:m]), triInsertionRapide(T[m:]))

def fusion(T1, T2):
    if T1 == []:
        return T2
    elif T2 == []:
        return T1
    elif T1[0] < T2[0]:
        return [T1[0]] + fusion(T1[1:], T2)
    else:
        return [T2[0]] + fusion(T1, T2[1:])

############################################################
# Exercice 2.2
#
# Tri fusion
#

def fusion(T1, T2):
    if T1 == []:
        return T2
    elif T2 == []:
        return T1
    elif T1[0] < T2[0]:
        return [T1[0]] + fusion(T1[1:], T2)
    else:
        return [T2[0]] + fusion(T1, T2[1:])

def triFusion(T) :
    n = len(T)
    if n < 2:
        return T
    else:
        m = n//2
        return fusion(triFusion(T[:m]), triFusion(T[m:]))

############################################################
# Exercice 2.3
#
# Tri à bulles
#

def triBulles(T) :
    n = len(T)
    for i in range(n):
        for j in range(0, n-i-1):
            if T[j] > T [j+1]:
                T[j], T[j+1] = T[j+1], T[j]
    return T


############################################################
# Exercice 3.1
#
# Tri rapide avec mémoire auxiliaire et en place
#

def triRapide(T) :
    if len(T) <= 1 : 
        return T
    pivot = T[0]
    gauche = [val for val in T[1:] if val <= pivot]
    droite = [val for val in T[1:] if val > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)


def triRapideEnPlace(T):
    if len(T) <= 1 : 
        return T
    pivot = T[0]
    gauche = [val for val in T[1:] if val <= pivot]
    droite = [val for val in T[1:] if val > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

############################################################
# Exercice 3.2
#
# Tri rapide avec mémoire auxiliaire et en place avec pivot
# aléatoire
#

def triRapideAleatoire(T) :
    if len(T) <= 1 : 
        return T
    pivot = T[random.randint(1, len(T))-1]
    gauche = [val for val in T[1:] if val <= pivot]
    droite = [val for val in T[1:] if val > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

def triRapideEnPlaceAleatoire(T):
    if len(T) <= 1 : 
        return T
    pivot = T[random.randint(1, len(T))-1]
    gauche = [val for val in T[1:] if val <= pivot]
    droite = [val for val in T[1:] if val > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)


############################################################
# Exercice 4.1
#
# les tableaux de taille < 15 sont triés par insertion, le
# reste avec l'algo de tri rapide usuel.
#

def triRapideAmeliore(T) :
    # À COMPLETER
    return T

############################################################
# Exercice 4.2
#
# Tri rapide seulement pour les tableaux de taille >= 15 et
# ne fait rien pour les tableaux de taille < 15
#

def triRapideIncomplet(T) :
    # À COMPLETER
    return T

############################################################
# Exercice 4.3
#
# Trie par insertion le résultat de triRapideIncomplet(T).
#
def triSedgewick(T) :
    # À COMPLETER
    return T

############################################################
# Exercice 5.1
#
# Trie par insertion le sous-tableau
# T[debut::gap] de T
#

def triInsertionPartiel(T, gap, debut) :
    # À COMPLETER
    return None

############################################################
# Exercice 5.2
#
# Tri Shell

def triShell(T) :
    # À COMPLETER
    return T

##############################################################
#
# Mesure du temps
#

def mesure(algo, T) :
    debut = clock()
    algo(T)
    return clock() - debut

def mesureMoyenne(algo, tableaux) :
  return sum([ mesure(algo, t[:]) for t in tableaux ]) / len(tableaux)

couleurs = ['b', 'g', 'r', 'm', 'c', 'k', 'y', '#ff7f00', '.5', '#00ff7f', '#7f00ff', '#ff007f', '#7fff00', '#007fff' ]
marqueurs = ['o', '^', 's', '*', '+', 'd', 'x', '<', 'h', '>', '1', 'p', '2', 'H', '3', 'D', '4', 'v' ]

def courbes(algos, tableaux, styleLigne='-') :
  x = [ t[0] for t in tableaux ]
  for i, algo in enumerate(algos) :
    print('Mesures en cours pour %s...' % algo.__name__)
    y = [ mesureMoyenne(algo, t[1]) for t in tableaux ]
    plt.plot(x, y, color=couleurs[i%len(couleurs)], marker=marqueurs[i%len(marqueurs)], linestyle=styleLigne, label=algo.__name__)

def affiche(titre) :
  plt.xlabel('taille du tableau')
  plt.ylabel('temps d\'execution')
  plt.legend(loc='upper left')
  plt.title(titre)
  plt.show()

def compareAlgos (algos) :
  for tri in algos :
      if testeQueLaFonctionTrie(tri):
          print(tri.__name__ + ": OK")
      else:
          print(tri.__name__ + ": échoue")
  taille = 1000 # taille maximale des tableaux à trier
  pas = 100 # pas entre les tailles des tableaux à trier
  ech = 5 # taille de l'échantillon pris pour faire la moyenne
  print()
  print("Comparaison à l'aide de randomPerm")
  tableaux = [[i, [randomPerm(i) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de randomPerm")
  print()
  print("Comparaison à l'aide de randomTab")
  tableaux = [[i, [randomTab(i,0,1000000) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de randomTab")
  print()
  print("Comparaison à l'aide de derangeUnPeu (rev = False)")
  tableaux = [[i, [derangeUnPeu(i,20,False) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de derangeUnPeu (rev = False)")
  print()
  print("Comparaison à l'aide de derangeUnPeu (rev = True)")
  tableaux = [[i, [derangeUnPeu(i,20,True) for j in range(ech)]] for i in range(2, taille, pas)]
  courbes(algos, tableaux, styleLigne='-')
  affiche("Comparaison à l'aide de derangeUnPeu (rev = True)")

##############################################################
#
# Main
#

if __name__ == '__main__':
  trisInsertion = [ triInsertionEchange, triInsertionRotation, triInsertionRapide ]
  trisLents = [ triSelection, triBulles ]
  trisRapides = [ triFusion, triRapide, triRapideEnPlace, triRapideAleatoire, triRapideEnPlaceAleatoire, triRapideAmeliore, triSedgewick, triShell]

  #exercice1

  print("Exercice 1")
  algos = [triSelection]
  compareAlgos(algos)

  #exercice2

  print("Exercice 2")
  algos += trisInsertion + [triFusion, triBulles]
  compareAlgos(algos)

  #exercice3

  print("Exercice 3")
  algos = [triRapide, triRapideEnPlace, triRapideAleatoire, triRapideEnPlaceAleatoire]
  compareAlgos(algos)

  #exercice4

  #print("Exercice 4")
  #algos2 = [triRapideAmeliore, triSedgewick]
  #compareAlgos(algos2)
  #algos += algos2
  #compareAlgos(algos)

  #exercice5

  #print("Exercice 5")
  #algos = [triShell]
  #compareAlgos(algos)


  #compare tous les algos

  #print("Comparaisons de tous les algos")
  #algos = trisInsertion + trisLents + trisRapides
  #compareAlgos(algos)
 