def print_graph(G, S):
    n = len(G)
    print('   ', end='')
    for u in range(n):
        print(S[u], end=' ')
    print()
    for u in range(n):
        print(S[u], end=': ')
        for v in range(n):
            print(G[u][v], end=' ')
        print()
    print("----------")

"""
G : réseau sous forme matricielle
f : flot du réseau G sous forme matricielle

Retourne le graphe résiduel Gf du réseau G
Nb : Prendre en compte les arrêtes retour pour une arrête consommée
"""
def residual(G, f):
    n = len(G)
    R = [[0 for _ in range(n)] for _ in range(n)]
    for u in range(n):
        for v in range(n):
            R[u][v] = G[u][v] - f[u][v]
            R[u][v] = R[u][v] + f[v][u]
    return R

"""
G : réseau sous forme matricielle
s : source
t : puits
f : flot du réseau G sous forme matricielle

Retourne un chemin augmentant dans le graphe résiduel Gf et augmente le flot f dans ce chemin
"""
def augment(G, s, t, f):
    n = len(G)
    R = residual(G, f)
    path = []
    visited = [False] * n
    visited[s] = True
    path.append(s)
    while len(path) > 0:
        u = path[-1]
        if u == t:
            return path
        for v in range(n):
            if not visited[v] and R[u][v] > 0:
                visited[v] = True
                path.append(v)
                break
        else:
            path.pop()
    return None

def ford_fulkerson(G, s, t):
    n = len(G)
    f = [[0 for _ in range(n)] for _ in range(n)]
    while True:
        path = augment(G, s, t, f)
        if path is None:
            break
        for i in range(len(path) - 1):
            u = path[i]
            v = path[i + 1]
            f[u][v] += 1
    return f

# réseau G (un graphe) sous forme matricielle
G = [[0, 5, 8, 0],  #s
     [0, 0, 1, 6],  #a
     [0, 0, 0, 3],  #b
     [0, 0, 0, 0]]  #t

S = ['s', 'a', 'b', 't']

# flot f du réseau G sous forme matricielle
f = [[0, 1, 0, 0],  #s
     [0, 0, 1, 0],  #a
     [0, 0, 0, 1],  #b
     [0, 0, 0, 0]]  #t

print("graph G :")
print_graph(G, S)
print("residual Gf :")
print_graph(residual(G, f), S)
print("augmenting path :")
print(augment(G, 0, 3, f))
print("ford fulkerson :")
print_graph(ford_fulkerson(G, 0, 3), S)
