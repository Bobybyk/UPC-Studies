#!/usr/bin/env python3

#
# A REMPLIR
#
def somme_impairs(x) :
# calcule la somme des entiers impairs de 1 à x
    res = 0;
    for i in range(1, x+1, 2) :
        res += i
    return res

def test_somme(n) :
# teste que la somme des entiers impairs de 1 à x =
#    (x/2)*(x/2) si x est pair
#    (x+1)/2*(x+1)/2 sinon
# pour tout 1 <= x <= n
    for i in range(0, n) :
        if (i % 2 == 0) :
            if (not somme_impairs(i) == (i/2)* (i/2)) :
                return False
        else :
            if (not somme_impairs(i) == (i+1)/2 * (i+1)/2) :
                return False
    return True


# AJOUTER D'AUTRES TESTS
#  [valeur_x, resultat_attendu]
def testDataSomme() :
    """retourne un jeu de tests"""
    return [[0, 0], [3, 4], [24, 144], [-3, 0]]


#
# NE PAS MODIFIER
#
def testOp(op, data) :
        print('\n\n* Test function %s:' % op.__name__)
        score = 0
        ldata = len(data)
        for i, dt in enumerate(data) :
                print('** test %d/%d : ' % (i + 1, ldata), end='')
                x = dt[0]
                refr = dt[1]
                r = op(x)
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
        testOp(somme_impairs, testDataSomme())
        print("test de la fonction de test : ")
        print(test_somme(20))

