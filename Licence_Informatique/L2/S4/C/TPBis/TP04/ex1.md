# Exercice 1

## 1)
### a) 
```
int t[] = {1, 2, 3}, *pt;
pt = t;
```
**OK, compile comme il faut. Le nom du tableau est un pointeur vers son premier element**

### b)
```
int t[3] = {1, 2, 3}, *pt;
pt = &t[0];
```
**OK**

### c)
```
int t[] = {1, 2, 3}, *pt;
t = pt;
```
**NON ne compile pas : t est de type tableau, pas de type pointeur**

## 2)
### a)
```
int t[3] = {1, 2, 3}, *pt;
pt = t + 1;
```
**OK : pt contient l'adresse de la case t[1]**

### b)
```
int t[3] = {1, 2, 3}, *pt;
pt = &t[1];
```
**OK : pt contient aussi l'adresse de la case t[1]**

## 3)
### a)
```
int t[3], *pt;
pt = malloc (5 * sizeof (int));
pt = t;
```
**NON : fuite de memoire => on n'a pas libere l'adresse renvoyee par malloc.
 Par contre, il est tout a fait possible de mettre un tableau de taille 3 meme si on a reserve plus de memoire. Pas de pb de compilation !**

### b)
```
int t[5] = malloc (5 * sizeof (int));
```
**NON : on ne peut pas mettre dans un tableau une valeur renvoyee par malloc**

## 4)
### a)
```
int *pt;
pt = malloc (5 * sizeof (int));
*pt = 10;
*(pt + 1) = 20;
*(pt + 12) = 30;
```
**NON : on a alloue un tableau de taille 5, on ne peut pas mettre des trucs dans la case 12.
Il n'y aura pas de pb de compilation, mais peut etre des pb a l'execution**

### b)
```
int *pt;
pt = malloc (5 * sizeof (int));
pt[0] = 10;
pt[1] = 20;
pt[12] = 30;
```
**OK : on peut utiliser la meme syntaxe avec les pointeurs qu'avec les tableau.
Mais on aura tjrs le meme pb qu'au dessus**
