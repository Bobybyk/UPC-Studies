# Complexité 

**On note** : `f(n) ∈ O(g(n))` ou `f(n) ∈ Θ(g(n))`

- **n** représente la taille de l'entrée (le nombre de valeurs)
- **O** : la complexité dans le pire cas, O(n) majore
- **Θ** : la complexité moyenne, Θ(n) encadre
- **Ω** : la complexité minimum, Ω(n) minore (meilleur cas)

**f(n) ∈ O(g(n))** : `Majore`

**g(n) ∈ Ω(f(n))** : `Minore`

**f(n) ∈ Θ(g(n))** : `Encadre`

![graph](graph.png)


**Complexités croissantes :**

- log10(n)
- log2(log2(n))
- log2(n)
- sqrt(n)
- n
- n.log(n)
- n.sqrt(n)
- n²
- n³
- 2ⁿ
- 3ⁿ
- n!
- nⁿ

**Il est vrai de noter que :**
- n ∈ O(n²)
- n ∈ O(n)
- n ∈ Θ(n)
- n² ∈ O(2ⁿ)
- 3 · log2(n) ∈ Θ(log2(n))
- sqrt(n) + n ∈ Θ(n)

**Il n'est pas vrai de dire que :**
- Si **f (n) ∈ O(g(n))**, alors **g(n) ∈ O(f(n))**, car si f majore g, l'inverse n'est pas vrai
- Si **f (n) ∈ Ω(g(n))**, alors **g(n) ∈ Ω(f(n))**, car si f majore g, l'inverse n'est pas vrai
- n² ∈ O(n)
- n² ∈ Θ(n)
- n ∈ Θ(n²)
- n² ∈ Θ(2ⁿ)