def nim(n, m):
    if (m >= n):
        return True
    else:
        for i in range(1, m+1):
            if (not(nim(n-i, 2*i)) or nim(n-1, m-1)):
                print("i : " + str(i))
                return True
    return False

def nim_bis(n, m):
    if(n <= m):
        return True
    if (m == 1):
        return not(nim_bis(n-1, 2))
    return nim_bis(n, m-1) or not(nim_bis(n-m, 2*m))
