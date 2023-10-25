# Spécifications feu rouge

## Un seul feu

**les états et leurs positions :**
- rouge fixe : arrêt circulation obligatoire, haut
- jaune fixe : arrêt circulation imminent, milieu
- vert fixe : circulation autorisé, bas
- jaune intermitent : circulation autorisé en respectant les priorités, milieu
- éteint : pas d'informations visuelles, toutes positions

**les temps d'activité par états :**
- rouge : 30 secondes
- jaune : 3 secondes
- vert : 30 secondes
- jaune intermitent : 0.5s jaune/éteint
- éteint : tant que éteint

**les transitions d'états :**
- rouge <-> jaune
- vert <-> jaune

**Remarques :**
- lorsqu'un état est activé, il remplace l'état précédent

**le lexique :*
- fixe : reste dans l'état de sa couleur durant son temps d'ativité
- intermitent : alterne entre la couleur de son état et éteint