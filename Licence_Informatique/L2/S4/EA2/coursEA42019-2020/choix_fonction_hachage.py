#! /usr/bin/env python3

import sys
import random
from math import sqrt

#m = 4093

m = 1024

if len(sys.argv) == 1 :
  alpha = float(input("taux de remplissage : "))
else : alpha = float(sys.argv[1])

H = [0] * m
H1 = [0] * m
#H2 = [0] * m

phi = (sqrt(5) - 1) / 2

def h(x) : 
  return x % m

def h1(x) :
  return (int)(m * ((phi*x)%1))

#def h2(x) :
#  return 1 + (x % (m-1))


random.seed()
deb = random.randint(1, m * m)
for x in range(deb, int(deb + m * alpha)) :
  H[h(x)] += 1
  H1[h1(x)] += 1

def affiche(table) :
  res = ""
  for elt in table : 
    if elt == 0 : res += '.'
    else : res+= str(elt)
  print(res)
  print()

print("modulo simple :")
affiche(H)
print("modulo apres multiplication :")
affiche(H1)

def stats(table) :
  S = set(table)
  MAX = max(S)
  #decompte = dict((elt, H.count(elt)) for elt in S)
  return MAX

print ('statistiques (alpha = ', alpha, ') :')
print ('    collision max :\t', stats(H), '\t', stats(H1))

