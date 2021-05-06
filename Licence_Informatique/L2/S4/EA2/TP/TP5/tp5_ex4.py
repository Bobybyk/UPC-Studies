#!/usr/bin/env python3

import tp5_ex2_ex3

def mot_to_int(mot) :
    #A COMPLETER
    return

def creer_dico(taille=0) :
    #A COMPLETER
    return

def ajouter_mot(dico, mot) :
    #A COMPLETER
    return

def retirer_mot(dico, mot) :
    #A COMPLETER
    return

def dans_dico(dico, mot):
    #A COMPLETER
    return

##############################################################
#
# crée un générateur des mots contenus dans le roman de Marcel Proust
#
def proust() :
    with open("proust.txt") as f :
        for ligne in f :
            for mot in ligne.split() :
                tmp = mot.strip('-,.?!;:"«»()^').lower()
                if tmp != '' : yield tmp


##############################################################
#
# Main
#

if __name__ == '__main__':
    
    #complétez avec vos tests...
