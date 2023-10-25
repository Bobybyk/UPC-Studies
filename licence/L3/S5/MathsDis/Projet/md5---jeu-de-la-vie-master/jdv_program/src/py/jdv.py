from tkinter import *

def evolve(T):
    T2 = [0] * len(T)
    for i in range(len(T)):
        if i == 0:
            if T[i] == 0 and (T[i+1] == 1 or T[len(T) -1 ] == 1):
                T2[i] = 1
            elif T[i] == 1 and (T[i+1] == 1 or T[len(T) - 1] == 1):
                T2[i] = 0
        elif i == len(T) - 1:
            if T[i] == 0 and (T[i-1] == 1 or T[0] == 1):
                T2[i] = 1
            elif T[i] == 1 and (T[i-1] == 1 or T[0] == 1):
                T2[i] = 0
        else:
            if T[i] == 0 and (T[i+1] == 1 or T[i-1] == 1):
                T2[i] = 1
            elif T[i] == 1 and (T[i+1] == 1 or T[i-1] == 1):
                T2[i] = 0
    return T2

def printT(T):
    for i in range(len(T)):
        if T[i] == 0:
            print("[ ]",end='')
        if T[i] == 1:
            print("[X]",end='')
    print('')

T = [0,0,0,0,0,1,0,1,0]
printT(T)
for i in range(15):
    T = evolve(T)
    printT(T)
