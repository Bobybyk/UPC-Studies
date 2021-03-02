#1

**OK**
```c
int t[] = {1, 2, 3}, *pt; 
pt = t;
```

**OK**
```c
int t[3] = {1, 2, 3}, *pt;
pt = &t[0];
```

```c
int t[] = {1, 2, 3}, *pt;
t = pt;
```


#2

```c
int t[3] = {1, 2, 3}, *pt;
pt = t + 1;
```

```c
int t[3] = {1, 2, 3}, *pt;
pt = &t[1];
```

#3

**OK**
```c
1int t[3], *pt;
pt = malloc (5 * sizeof (int));
pt = t;
```

**NOP**
```c
int t[5] = malloc (5 * sizeof (int));
```


#4

**OK**
```c
int *pt;
pt = malloc (5 * sizeof (int));
*pt = 10;
*(pt + 1) = 20;
*(pt + 12) = 30;
```

```c
int *pt;
pt = malloc (5 * sizeof (int));
pt[0] = 10;
pt[1] = 20;
pt[12] = 30;
```