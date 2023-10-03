def lev(u, v):
    n = len(v) + 1
    m = len(u) + 1
    T = [[10000] * len(v) * len(u)]
    for i in range(len(u)):
        T[0][j] = j
    for j in range(len(v)):
        T[j][0] = j
    for i in range(1, len(u)):
        for j in range(1, len(v)):
            if (u[i] == v[j]):
                T[i][j] = T[i][j] = T[i-1][j-1]
            else: