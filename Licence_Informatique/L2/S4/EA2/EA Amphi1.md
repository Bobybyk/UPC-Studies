20210127132937
#l2
#ea4
#algo
Back to index : [[20210120111811]]

# EA4 : Amphi 1

## Introduction
Slides : `cours_1.pdf`

Quelques définitions pourries, considérations éthymologiques

Des exemples en python pour faire des opérations sur les nombres (addition, soustraction, puissance)

## Micro-cours de Python
Slides : `cours_python.pdf`

Langage interprété : soit dans l'interpréteur intéractif, soit dans des fichiers

**Dans un fichier : première ligne = référence à l'interpréteur** `#!/usr/bin/python3`

Attention : `python` renvoie vers la version 2

- pas de déclaration de variables
- typage dynamique (le type d'une variable est déterminé à l'éxécution, et peut changer)
- typage fort (on ne peut pas mélanger les types n'importe comment)
- pas de signe en fin de ligne (pas de ; comme en java)

### Types de base

- `int` : entiers de longueur non bornée
- `float` : flottants sur 64 bits
- `bool`
- `str`
- `list` : listes, pas nécessairement homogènes (peuvent avoir des types différents dedans)
- `tuple` : k-uplets
- `set` : ensembles
- `dict` : dictionnaires (ou tables d'association)

#### `int`

- p puissance k : `p **= k`

#### `str`

- concaténation  : `str1 + str2`
- accès aux charactères comme avec un tableau : `str[3]` (on peut les afficher directement par ex)
- mais on ne peut PAS modifier un charactère au milieu du tableau avec : `str[3] = 'd'` ne fonctionne pas
- définir une chaîne sur plusieurs lignes :
```python
str = """coucou

salut"""
```
- longueur de la chaîne : `len(str)`
- enlever les blancs de part et d'autre : `str.strip()`
- plein d'autres méthodes

! il n'y a pas de type `char` pour les charactères, il faut passer par des chaînes de longueur 1

#### `list`

- valeurs entre `[]`
- peut contenir des éléments de types différents
- copie de t1 dans t2 : `t2 = t1[:]`
- manipulables comme des tableaux ET comme des listes chaînées : 
```python
l = [1, 4, 7, "coucou"]

l[2] # accéder à la variable en deuxième position (ici 7)

l.append(8) # ajouter un élément à la fin
l.count(4) # compter le nombre d'occurences
l.insert(2, "salut") # insérer "salut" en position 2
```

#### `tuple`

- valeurs entre `()`
- comme les listes mais non mutables :
```python
t = (1, 3, 7, 'coucou', 4)

t[2] # pour lire la valeur 7

t[2] = 5 # impossible car non mutable

t += (1,2,3) # création d'un nouvel objet
```

### Conditionnelles

- définies uniquement par l'indentation
```python
if test1 :
    # instructions
elif test2 :
    # instructions
elif test3 :
    # instructions
else :
    # instructions
```

```python
while test :
    # instructions
```

```python
for element in sequence-iterable : # listes, tuples, str, ...
    # instructions
```
Par exemple on peut itérer sur une plage d'entiers : `range(1,4)` (entre 1 compris et 4 exclus)

### Méthodes

```python
def ma_méthode (liste_paramètres) :
    """écrire la doc de la méthode ici"""
    # instructions
```

- on ne déclare pas le type des paramètres, juste leur nom
- on ne déclare pas de type de retour
- pour retourner une valeur : `return var_name`

#### Quelques méthodes particulières

`help()` 
    - sans argument, lance l'aide intéractive
    - avec argument, affiche l'aide spécifique de l'élément

`dir()`
    - sans argument, liste les noms des objets et méthodes définis
    - avec un argument, liste les attributs de l'objet/la méthode
                       