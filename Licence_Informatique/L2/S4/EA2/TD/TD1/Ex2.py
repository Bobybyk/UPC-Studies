def multiplication_russe(m, n):
    res = 0
    while n != 0 :
        if n%2 == 1 : res += m
        m *= 2
        n //= 2
    return res

def exponentiationRapide(m, n) :
    res = 1
    while n != 0 :
        if n%2 == 1 : res *= m
        n //= 2
        m *= m
    return res

print("exp : ", exponentiationRapide(2, 4))

print("prod : ", multiplication_russe(13, 14))
