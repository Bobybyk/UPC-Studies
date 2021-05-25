#!/usr/bin/env python3

#importe indirectement tp4, data, et tp4_ex* pour * < 3
from tp4_ex3 import *
from math import sqrt
from random import randint

#
# À COMPLÉTER !
#
def genererABRparInsPuisSup(perm) :
    ''' renvoie un ABR de taille n construit par insertions successives
    des éléments de la permutation perm (de taille n^2), puis suppression
    d'éléments aléatoires '''
  arbre = genererABRparInsertion(perm)
  toSupp = selectSuppr(len(perm))
  for elt in toSuppr :
    suppressionABR(arbre, toSuppr[i], False)

def selectSuppr(n) :
    ''' selects a len(perm)^2 - len(perm) elements to be deleted'''
  nbToSuppr = n - sqrt(n)
  l = []
  flag = [0] * lengthPerm
  for i in range(toSuppr) :
    r = randint(n)
    while flag[r] != 0:
      r = randint(n)
    flag[r] = 1
    l[i] = r

  return l

#
# À COMPLÉTER !
#
def genererABRparInsEtSup(permins, permsup) :
    ''' renvoie un couple (ABR, taille) construit par
    insertions/supressions successives entremêlées des éléments de
    permins et permsup respectivement '''
  arbre = nouvelABRVide()
  flagins = [0] * len(permins)
  flagsup = [0] * len(permsup)
  for i in range(len(permins)) :
    if i == 0 :
      a = selectRandom(flagsup)
      suppressionABR(arbre, permsup[a])
    if j == 0 :
      a = selectRandom(flagins)
      insertionABR(arbre, permins[a])
    else :
      b = randint(2)
      if b == 0 :
        a = selectRandom(flagins)
        insertionABR(arbre, permins[a])
      else :
        a = selectRandom(flagsup)
        suppressionABR(arbre, permsup[a])
  return [taille(arbre), arbre]

def selectRandom(flag) :
    ''' selects a random element to be deleted '''
  a = randint(len(flag))
  while flag[a] == 1:
    a = randint(len(flag))
  flag[a] = 1
  return a

def taille(arbre) :
  t = 0
  for elt in noeuds(arbre) :
    t++
  return t

#
# À COMPLÉTER !
#
def statsHauteursABRparInsPuisSup(n, m) :
    ''' renvoie le tableau des hauteurs de m arbres de taille n,
    construits par genererABRparInsPuisSup '''
  hauteurs = [0] * m
  for i in range(0, m) :
    perm = permutation(n)
    perm2 = permutation(n)
    taille, arbre = genererABRparInsertionPuisSuppr(perm, perm2)
    hauteurs[i] = hauteur(arbre)
  return hauteurs

#
# À COMPLÉTER !
#
def statsHauteursABRparInsEtSup(n, m) :
    ''' renvoie le tableau des (taille, hauteur)s de m arbres
    construits par genererABRparInsEtSup sur 2 permutations de taille 2n
    '''
    hauteurs = [0] * m
    for i in range(0, m) :
        perm = permutation(n)
        arbre = genererABRparInsertionEtSuppr(perm)
        hauteurs[i] = hauteur(arbre)
    return hauteurs

#
# NE PAS MODIFIER
#

def tracerInsPuisSup(limite, pas, m):
    lx, ly, ly_moy = [], [], []
    for i in range(1, limite, pas) :
        tmp = statsHauteursABRparInsPuisSup(i, m)
        lx.extend([i]*m)
        ly.extend(tmp)
        ly_moy.append(sum(tmp)/m)
    plt.plot([(math.log(i,2) if i>0 else 0) for i in range(limite)], color="blue")
    plt.plot(lx, ly, '.', color="orange")
    plt.plot(range(1,limite,pas), ly_moy, color="red")
    plt.ylabel('hauteur(n)')
    plt.xlabel('n = nombre noeuds')
    plt.title('Distribution des hauteurs d\'arbres aléatoires obtenus par insertions puis suppressions')
    plt.show()

def tracerInsEtSup(limite, pas, m):
    lx, ly = [], []
    plt.plot([(math.log(i,2) if i>0 else 0) for i in range(limite)], color="blue")
    for i in range(1, limite, pas) :
        tailles, hauteurs = list(zip(*statsHauteursABRparInsEtSup(i, m)))
        lx.extend(tailles)
        ly.extend(hauteurs)
    plt.plot(lx, ly, '.', color="green")
    plt.ylabel('hauteur(n)')
    plt.xlabel('n = nombre noeuds')
    plt.title('Distribution des hauteurs d\'arbres aléatoires obtenus par insertions et suppressions')
    plt.show()


if __name__ == '__main__':
  tracerInsPuisSup(100,5,5)
  tracerInsEtSup(1000,50,10)