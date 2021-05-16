#!/usr/bin/env python3

import sys
import random

m = 4093

if len(sys.argv) == 1 :
  alpha = float(input("taux de remplissage : "))
else : alpha = float(sys.argv[1])

H = [0] * m
H1 = [0] * m
H2 = [0] * m

#phi = (sqrt(5) - 1) / 2

def h(x) : 
  return x % m
  #return (int)(m  * ((phi*x)%1))

def h2(x) :
  return 1 + (x % (m-1))


random.seed()
for i in range(int(m * alpha)) :
  x = random.randint(1, m * m)
  H[h(x)] += 1
  
  i = h(x)
  while H1[i] != 0 : i = (i+1) % m
  H1[i] = 1
  
  i = h(x)
  j = h2(x)
  while H2[i] != 0 : i = (i+j) % m
  H2[i] = 1

def clusters(T, filtre=1) :
  res = []
  tmp = 0
  for elt in T :
    if elt == 0 :
      if tmp > filtre : res, tmp = res + [tmp], 0
    else : tmp += 1
  return sorted(res, reverse=True)

print('chainage :')
res = ""
for elt in H : 
  if elt == 0 : res += '.'
  else : res+= str(elt)
print(res)

print('sondage lineaire :')
res = ""
for elt in H1 : 
  if elt == 0 : res += '.'
  else : res+= 'x'
print(res)

print('double hachage :')
res = ""
for elt in H2 : 
  if elt == 0 : res += '.'
  else : res+= 'x'
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
print('    clusterisation avec sondage lineaire :', clusters(H1)[:10])
print('    clusterisation avec double hachage :', clusters(H2)[:10])

 
 
