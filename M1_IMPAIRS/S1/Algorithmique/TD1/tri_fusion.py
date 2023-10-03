def trifusion(t):
    if len(t) <= 1:
        return t
    else:
        m = len(t) // 2
        t1 = trifusion(t[:m])
        t2 = trifusion(t[m:])
        return fusion(t1, t2)

def fusion(t1, t2):
    t = []
    i = 0
    j = 0
    while i < len(t1) and j < len(t2):
        if t1[i] < t2[j]:
            t.append(t1[i])
            i += 1
        else:
            t.append(t2[j])
            j += 1
    t += t1[i:]
    t += t2[j:]
    return t

t = [5, 3, 8, 4, 1, 9, -2]
print(t)
print(trifusion(t))