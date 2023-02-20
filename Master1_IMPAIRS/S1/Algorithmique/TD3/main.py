# Comp = k*n
def naif(t, k):
    for i in range(k + 1):
        im = indice_min(t)
        if i < k:
            t[im] = float("inf")
    return t[im]


def indice_min(t):
    indice = 0
    for i in range(0, len(t)):
        if t[indice] > t[i]:
            indice = i
    return indice


##################################

def trie(t, k):
    t.sort()
    return t[k - 1]


##################################

import heapq


def tas(t, k):
    heapq.heapify(t)
    for i in range(0, k):
        v = heapq.heappop(t)
    return v


##################################

def quick_select(t, k, bg, bd):
    ip = indice_pivot(t, bg, bd)
    rgpivot = ip - bg + 1
    if k < rgpivot:
        return quick_select(t, k, bg, ip - 1)
    elif k > rgpivot:
        return quick_select(t, k - rgpivot, ip + 1, bd)
    else:
        return t[ip]


def indice_pivot(t, bg, bd):
    valeurpivot = t[bd]
    index = bg
    for i in range(bg, bd):
        if t[i] < valeurpivot:
            t[index], t[i] = t[i], t[index]
            index += 1
    t[bd], t[index] = t[index], t[bd]
    return index


if __name__ == '__main__':
    arr = [0, 2, 3, 5, 7, 8, 9, 10, 11, 18, 56]
    print(naif(arr, 5))
