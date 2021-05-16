20210210155657
#l2
#lc4
#c 
Back to course index : [[20210120112725]]

# LC4 : Amphi 2 (03.02.2021)

## Increment and decrement operators

- **pre**-increment/decrement : the operation is done first
- **post**-increment/decrement : the operation is done last

```c
int x = 7, y;
y = ++ x;
// x=8 et y=8

int x = 7, y;
y = x ++;
// x=8 et y=7
```

### Ternary operator

```c
x = (condition) ? val1 : val2
// equivalent to
if (condition) {
    x = val1;
} else {
    x = val2;
}
```

## Variable length arrays

Arrays can have a variable length.

```c
// ex of intialization
int k, n;
double tab[k*2 + n];

// ex in a method parameters
int somme(int length, int t[length]) { /* code */ }
```

## Structures

To aggregate elements with different types.

```c
struct personne {
    char sexe;
    int annee;
}; // you need to end the declaration with a ;

// declare and initialize a variable of type struct personne at the same time
struct personne robert = { .sex = 'm', .annee = 1956};

// declare and init separatly
struct personne p;
p.sexe = 'm';
p.annee = 1900;

// define an alias for the structure
typedef struct personne newName;

// define an alias at init
typedef struct point {
    int x;
    int y;
} newName ; 
```

You can put structures into a structure, no pb (just make sure to put all the `.` to access the fields).
You can't compare structures with `p1 == p2`, you have to manually compare each field with `p1.x == p2.x && p1.y == p2.y`.

Can be parameters of a method : but will be passed as value ! => a new structure will be initialized and the content of the variable you pass will be copied into it. If you modify the structure in the method, the 'original' one won't be modified.

A method can return a structure (same syntax as usual). 
In particular, a method can return a structure that contains an array (a method can't return an array by itself).

## enum

```c
enum color { BLUE, RED, GREEN }; // define the enum

enum color feu; // declare a variable of that enum. It can only contain the values defined for the enum.

typedef enum color newName; // use typedef in the same way to define aliases
```

The values in the enum are actually numbers, starting from 0 (here  BLUE=0, RED=1, GREEN=2). 
You can specify the values you want : `enum color { BLUE=2, RED=5 }`

## goto

```c
int somme_ligne( int nb_l, int nb_c, int tab[nb_l][nb_c] ){
    int s = 0;
    int i;
    for( i = 0; i < nb_l; i++ ){
        for( int j = 0 ; j < nb_c ; j++ ){
            if( tab[i][j] < 0 ) goto somewwhereInTheProg;
        }
    }

    somewwhereInTheProg:
    for( int j = 0 ; j < nb_c ; j++ ){
        s += tab[i][j];
    }
    return s;
}
```

**THE ONLY ACCEPTABLE USE OF GOTO IN THIS CLASS IS TO GET OUT OF NESTED LOOPS**