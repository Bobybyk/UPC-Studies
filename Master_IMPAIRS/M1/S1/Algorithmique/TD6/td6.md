# TD6

## Exercice 1

### 1.1

| - | - | a | n | a | n | a | s |
|---|---|---|---|---|---|---|---|
| - | 0 | 1 | 2 | 3 | 4 | 5 | 6 |
| b | 1 | 1 | 2 | 3 | 4 | 5 | 6 |
| a | 2 | 1 | 2 | 2 | 3 | 4 | 5 |
| n | 3 | 2 | 1 | 2 | 2 | 3 | 4 |
| a | 4 | 3 | 2 | 1 | 2 | 2 | 3 |
| n | 5 | 4 | 3 | 2 | 1 | 2 | 2 |
| e | 6 | 5 | 4 | 3 | 2 | 2 | 3 |

### 1.2 

```
pour j allant de 0 à |A| :
	T[0, j] = j
pour i allant de 0 à |B| :
	T[i, 0] = i
pour i allant de 1 à |A|
	pour j allant de 1 à |B|
		si (A[i] == B[j]) {
			T[i, j] = T[i-1, j-1]
		}
		sinon
			T[i, j] = 1 + min(T[i-1, j-1], T[i-1, j], T[i, j-1])
return T[|A|, |B|]
```

### 1.3

Complexité : O(|A|.|B|)

### 1.4

On peut gagner en espace O(min(|A|, |B|))

---

## Exercice 2

### 2.1

"**La programmation dynamique est une des bases algorithmiques**"

glouton :
```
M = 17 :
	La programmation | B0 = 1
	dynamique est une| B1 = 0
	des bases        | B2 = 8
	algorithmiques   | B3 = 3
```
1³ + 0³ + 8³ + 3³ = 540

optimale :
```
```

### 2.2

```
penalite(i, j) = (M - Epsilone(j, k=i, lk - (j-i)) )³
```

### 2.3

coût(i) = coût pour le texte jusqu'au i-ième mot.
**cas de base :** coût(0) = 0
coût(i) = min[coût(k-1) + penalite(k, i)] : 1 <= k <= i

### 2.4

1. calculer penalite[i, j] pour tout i <= j
2. calculer coût[i] : 0 <= i <= n
```
cout[0] = 0

pour i allant de 1 à n

	m = +inf

	pour k allant de 1 à i
		ca = coût[k-1] + penalite[k, i]
		si  ca < m
			alors m = ca
		coût[i] = m

return coût[n]
```

Complexité : O(n.M/2) : n première boucle et M/2 deuxième boucle

---

## Exercice 3

### 3.1

```
J(n, m) : booléen
	si (m >= n):
		alors return true
	sinon si il existe un i <= k <= m tq J(n-k, 2k) == false
		alors return true
	sinon
		return false
```