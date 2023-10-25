#!/usr/bin/env python3


def somme_impairs(array) :
# calcule la somme des entiers impairs de 1 à x
	ret = 0
	for x in array :
		if x%2 == 1 :
			ret += x
	return ret


def test_somme(n) :
	if n%2 == 0 :
		if (n/2)*(n/2) == n :
			return True
	elif n%2 == 1 :
		if (n+1)/2*(n+1)/2 == n :
			return True
	return False


# AJOUTER D'AUTRES TESTS
#  [valeur_x, resultat_attendu]
def testDataSomme() :
    """retourne un jeu de tests"""
    return [[0, 0], [3, 4], [24, 144], [-3, 0]]


# Création de listes d'entiers
def creaList() :
	return [[*range(10)], [*range(2, 11)], [*range(2, 11, 2)], [*range(10, 1, -2)]] 


# Affichage d'une liste
def printList(array) :
	for x in array :
		print(x)


#
# NE PAS MODIFIER
#
def testOp(op, data) :
	print('\n\n* Test function %s:' % op)
	score = 0
	ldata = len(data)
	for i, dt in enumerate(data) :
		print('** test %d/%d : ' % (i + 1, ldata), end='')
		x = dt[0]
		refr = dt[1]
		r = op
		if r == refr :
			score+=1
			print('ok')
		else :
			print('ECHEC')
			print('    entree  : %s' % x)
			print('    calcule : %s' % r)
			print('    attendu : %s' % refr)
	print('** Score %d/%d' % (score, ldata))


if __name__ == '__main__':
	array = creaList()
	print("Liste : ")
	printList(array[0])
	print("#############")
	print("somme impair : ", somme_impairs(array[0]))
	print("test somme :", test_somme(somme_impairs(array[0])))
	print("#############")
	print("testOp : ")
	testOp(somme_impairs(array[0]), testDataSomme())