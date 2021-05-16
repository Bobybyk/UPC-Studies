#!/usr/bin/env python3

# Laure Runser 21955060

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

def triSelection(T):
    for i in range(len(T)):
        indexMin = i
        for j in range(i+1, len(T)):
            if T[j] < T[indexMin]:
                indexMin = j
        T[i], T[indexMin] = T[indexMin], T[i]
    return T

############################################################
# Exercice 1.2
#
# randomPerm prend en paramètre un entier n et renvoie une
# permutation aléatoire de longueur n
#

def randomPerm(n):
    # init le tableau
    T = [0] * n
    for i in range(n):
        T[i] = i
    # permuter les elements
    for i in range(n-1):
        j = random.randint(i+1, n-1)
        T[i], T[j] = T[j], T[i]
    return T

############################################################
# Exercice 1.3
#
# testeQueLaFonctionTrie prend en paramètre une fonction de
# tri f et l’applique sur des permutations aléatoires de
# taille i variant de 2 à 100 et vérifie que le résultat est
# un tableau trié
#

def testeQueLaFonctionTrie(f):
    for i in range(2, 100):
        T = randomPerm(i)
        R = T.copy()
        T = triSelection(T)
        if triFails(T):
            print("Can't sort : ", R)
            print("Result was : ", T)
            return False
    return True

# Verifie que le tableau T est bien trie par ordre croissant
def triFails(T):
    for i in range(len(T)-1):
        if T[i] > T[i+1]:
            return True
    return False

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
    for i in range(k):
        # choisir deux indices distincts au hasard
        a = random.randint(0, n-1)
        b = a
        while b==a:
            b = random.randint(0, n-1)
        # faire la permutation
        T[a], T[b] = T[b], T[a]
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
        for j in range(i, 0, -1):
            if T[j-1] > T[j]:
                T[j], T[j-1] = T[j-1], T[i]
            else:
                break
    return T

def triInsertionRotation(T):
    for i in range(1, len(T)):
        pos = i
        for j in range(i, 0, -1):
            if T[j-1] > T[i]:
                pos = j-1
            else:
                break
        T[i], T[pos] = T[pos],T[i]
    return T

def triInsertionRapide(T):
    for i in range (1, len(T)):
        pos = findPos(T, i-1, T[i])
        T[i], T[pos] = T[pos], T[i]
    return T

# find the position where to insert x in T
def findPos(T, end, x):
    start = 0
    while start != end:
        m = (end - start)//2 + start
        if T[m] < x:
            start = m+1
        else:
            end = m
    return start

############################################################
# Exercice 2.2
#
# Tri fusion
#

def fusion(T1, T2):
    R = [0] * (len(T1)+ len(T2))
    i = 0
    j = 0
    for k in range(len(T1)+len(T2)):
        if i == len(T1):
            R[k] = T2[j]
            j += 1
        elif j == len(T2):
            R[k] = T1[i]
            i += 1
        else:
            if T1[i] < T2[j]:
                R[k] = T1[i]
                i += 1
            else:
                R[k] = T2[j]
                j += 1
    return R

def triFusion(T):
    if len(T) == 1: return T
    m = len(T)//2
    T1 = triFusion(T[m:])
    T2 = triFusion(T[:m])
    return fusion(T1, T2)

############################################################
# Exercice 2.3
#
# Tri à bulles
#

def triBulles(T) :
    for i in range(len(T)-1, 1, -1):
        for j in range(0, i-1):
            if T[j+1] < T[j]:
                T[j+1], T[j] = T[j], T[j+1]
    return T


############################################################
# Exercice 3.1
#
# Tri rapide avec mémoire auxiliaire et en place
#

def triRapide(T) :
    if len(T) < 2 : return T
    pivot = T[0]
    gauche = [elt for elt in T if elt < pivot]
    droite = [elt for elt in T if elt > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

def triRapideEnPlace(T):
    return T

############################################################
# Exercice 3.2
#
# Tri rapide avec mémoire auxiliaire et en place avec pivot
# aléatoire
#

def triRapideAleatoire(T) :
    if len(T) < 2 : return T
    pivot = T[random.randint(0, len(T)-1)]
    gauche = [elt for elt in T if elt < pivot]
    droite = [elt for elt in T if elt > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

def triRapideEnPlaceAleatoire(T):
    return T

############################################################
# Exercice 4.1
#
# les tableaux de taille < 15 sont triés par insertion, le
# reste avec l'algo de tri rapide usuel.
#

def triRapideAmeliore(T) :
    if len(T) < 15 : return triInsertionRotation(T)
    pivot = T[0]
    gauche = [elt for elt in T if elt < pivot]
    droite = [elt for elt in T if elt > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

############################################################
# Exercice 4.2
#
# Tri rapide seulement pour les tableaux de taille >= 15 et
# ne fait rien pour les tableaux de taille < 15
#

def triRapideIncomplet(T) :
    if len(T) < 15 : return T
    pivot = T[0]
    gauche = [elt for elt in T if elt < pivot]
    droite = [elt for elt in T if elt > pivot]
    return triRapide(gauche) + [pivot] + triRapide(droite)

############################################################
# Exercice 4.3
#
# Trie par insertion le résultat de triRapideIncomplet(T).
#
def triSedgewick(T) :
    R = triRapideIncomplet(T)
    return triInsertionRotation(R)

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
  #print("Exercice 1")
  #algos = [triSelection]
  #compareAlgos(algos)

  #exercice2

  # print("Exercice 2")
  #algos = trisInsertion + [triFusion, triBulles]
  #compareAlgos(algos)

  #exercice3

  #print("Exercice 3")
  #algos = [triRapide, triRapideEnPlace, triRapideAleatoire, triRapideEnPlaceAleatoire]
  #compareAlgos(algos)

  #exercice4

  #print("Exercice 4")
  algos2 = [triRapideAmeliore, triSedgewick]
  compareAlgos(algos2)
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

