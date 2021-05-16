#!/usr/bin/env python3

import sys
import random

m = 4093

if len(sys.argv) == 1 :
  alpha = float(input("taux de remplissage : "))
else : alpha = float(sys.argv[1])

H = [0] * m

#phi = (sqrt(5) - 1) / 2

def h(x) : 
  return x % m
  #return (int)(m  * ((phi*x)%1))

random.seed()
for i in range(int(m * alpha)) :
  x = random.randint(1, m ** 4)
  H[h(x)] += 1
  
#print('chainage :')
res = ""
for elt in H : 
  if elt == 0 : res += '.'
  else : res+= str(elt)
print(res)

print()

S = set(H)
MAX = max(S)
decompte = dict((elt, H.count(elt)) for elt in S)

print('statistiques (alpha = ', alpha, ') :')
print('    collision max :', MAX)
print('    nombre de collisions : ', decompte.get(2, 0), "(simples),",
        decompte.get(3, 0), "(doubles),", end=' ') 
for i in range(4, MAX) : print(decompte.get(i, 0), ',', end=' ')
if MAX > 3 : print(decompte.get(MAX))
else : print()
 
