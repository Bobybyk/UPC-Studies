def triRec(T, bg, bd):
    for i in range(bg+1, bd):
         if (T[i] < T[i-1]):
            T[i-1], T[i] = T[i], T[i-1]
            print("tri")
            print("bg : ", bg)
            print("bd : ", bd)
            print(T)
            print("----------")
            return triRec(T, bg, bd)
    print(T)
    print("----------")
    return T
        
def tri (T, bg, bd):
    if (T[bd-1] < T[bg]):
         T[bg], T[bd-1] = T[bd-1], T[bg]
         print("swap")
         print(T)
         print("----------")
    if (len(T) > 2):
        T = triRec (T, bg, ((bd/3)*2))
        T = triRec (T, bd/3, bd)
        T = triRec (T, bg, ((bd/3)*2))
    print(T)
    return T

T = [1, 9, 4, 10, 3, 20]
print(T)
print("----------")
tri(T, 0, len(T))
