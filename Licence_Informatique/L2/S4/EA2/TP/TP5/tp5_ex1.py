#!/usr/bin/env python3

from time import process_time
from random import randint

def mesure_temps(f, *param, rep=1, cle=True):
    tps = 0
    for i in range(rep):
      if cle:
        x = randint(1,1000000)
        deb = process_time()
        f(x, *param)
        tps += (process_time() - deb)
      else:
        deb = process_time()
        f(*param)
        tps += (process_time() - deb)
    return tps/rep
    
def cherche(x, I) :
    for key, val in I[hash(x)][::-1] :
        if key == x : 
            return val
    return None


def nb_elts_diff_liste(L):
    #A COMPLETER
    return

def nb_elts_diff_ens(E):
    #A COMPLETER
    return

def comparaison_rech(L, E):
    print()
    print("##############################################################")
    print()
    print("temps moyen d'acces a une liste de longeur", len(L),":", mesure_temps(cherche,L,rep=10))
    print("temps moyen d'acces a un ensemble de longueur", len(L),":", mesure_temps(cherche,E,rep=10))

  
def comparaison_nb_elts(L, E):
    print()
    print("##############################################################")
    print()
    print("temps moyen pour compter les elements distincts dans une liste de longeur", len(L),":", mesure_temps(nb_elts_diff_liste, L, rep=10, cle=False))
    print("temps moyen pour compter les elements distincts dans un ensemble de longueur", len(L),":", mesure_temps(nb_elts_diff_ens, E, rep=10, cle=False))
    print()
    print("##############################################################")
    print()

if __name__ == '__main__':
    L=[randint(1,1000000) for i in range(1000000)]
    E=set(L)
    comparaison_rech(L, E)
    comparaison_nb_elts(L, E)
