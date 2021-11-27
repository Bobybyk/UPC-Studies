20210210161830

#l2
#lc4
#c 
Back to course index : [[20210120112725]]

# LC4 : Amphi 3 (10.02.2021)

## Pointers

To get the memory address of a variable x : `&x`.
To signifie a variable is holding a pointer, put `*` in front of its name.

```c
short a = 2;
short *b = &a;

short **c; // pointer of pointer
c = &b;

// declare several pointers at the same time  !! beware of syntax
int *x, *y; // 2 pointers
int *l, m; // l is a pointer, m a regular int
```

In an expression, `*a` is the value that is put at the memory address.
```c
int *a;
int d = 8;
a = &d; // a points to d

*a = 10; // put 10 at the memory address that a points to => d = 12
*a = *a + *a * *a; // put 10 + 10*10 = 110 at that memory address

prinft("%d", d); // prints 130
```

## Pointers and arrays

```c
int tab[] = {2,3,4};

int *pt = &tab[0];
int *pq = tab; // tab is already a pointer that points to tab[0]

pq += 2; // pp now points to tab[2]
pq -= 1; // pq points to tab[1]
```
**!! if you do this kind of stuff on pointers that do not point to arrays, you will have problems !!**

## NULL

Value defined in `stdio.h`

Used to say that a pointer is pointing to nothing. 
If a pointer is `NULL` and you try to change the value at that memory address, programm will terminate with an error.