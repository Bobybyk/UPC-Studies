# Implémentation de Matthieu

import time

def refresh(structure):
    if structure[0]:
        print("O")
        print("X")
        print("X")
    elif structure[1]:
        print("X")
        print("O")
        print("X")
    elif structure[2]:
        print("X")
        print("X")
        print("O")
    else:
        print("X")
        print("X")
        print("X")
    print("-----------------")

def changer_etat(position):
    structure = [False] * 3
    if position == 0:
        structure[0] = True
    elif position == 1:
        structure[1] = True
    elif position == 2:
        structure[2] = True
    return structure

def start():
    while True:
        changer_etat(2)
        refresh(changer_etat(0))
        time.sleep(5)
        changer_etat(1)
        refresh(changer_etat(1))
        time.sleep(1)
        changer_etat(0)
        refresh(changer_etat(2))
        time.sleep(5)

start()