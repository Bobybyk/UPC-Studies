import math

a = int(input("First number: "))
b = int(input("Second number: "))
r = int(input("base: "))
astr = str(a)
bstr = str(b)
al = len(astr)
bl = len(bstr)
n = bl if al < bl else al
k = n/2
a1 = int(slice(0, len(astr)//2))
a0 = int(slice(len(astr)//2, len(astr)))
b1 = int(slice(0, len(bstr)//2))
b0 = int(slice(len(bstr)//2, len(bstr)))
p = a1*b1*pow(r, 2*k) + (a0*b1 + a1*b0)*pow(r, k) + a0*b0