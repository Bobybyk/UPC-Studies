f20210127164056
#l2
#lc4
#c 
Back to course index : [[20210120112725]]

# LC4 : Amphi 1 (27.01.2021)

## Compile with gcc
```bash
gcc -Wall hello.c -o hello

./hello # to execute the program
```

- `gcc` is the compiler
- `-Wall` to see all the warnings : if there is a warning, the program is most likely incorrect, even if it compiles
- `hello.c` is the name of the source code file
- `-o hello` to give the name of the executable binary that will be compiled


## Hello World!

#### Basic structure
```c
#include <stdio.h>  // MUST be included in all C programs

/* Main method, necessary
     * You can change void into another type to pass parameters to the proram */
int main(void) 
{
    printf("Hello World");
    return 0;  //always must return 0 (or 1 on error)
}
```

#### Standard lib
- header files with **.h** extension
- can be included at the top of your program
- MUST include `stdio.h` in all programs

## Variables

### Types
 
Each type can be signed or unsigned
- short
- int
- long
- long long
- double

### Declaration and affectation
_Convention : declare all your variables at the beginning of the method_

```c
int a; // declaration
a = 4; // affectation

int b = 0; // both at the same time
b = 12; // change the value in the variable

a = b = 23; // assign the same value to both variables at the same time
```

```c
signed char = 20;
signed char = 'A'; // assign a value with the ASCII table
```
Don't use `char` : always go for `signed char` or `unsigned char`
Don't use `float` either.

### Arrays

#### Declaration and initialisation

```c
double tab[] = {-4.8, 6.1, 57.0, 23.99}; // both at the same time

int nbElements = sizeof(tab)/sizeof(tab[0])
```

Note : `sizeof()` give the number of bytes (depends on the type of the variable)

#### Arrays as a method parameter

When you pass an array as a parameter of a method, you have to pass the number of elements of the array too (if you want to use the value). Otherwise, you can't calculate it with `sizeof()`.

### Casts
```c
float a = 12.5;
int b = (int)a;
```

### Operations

- same operators as usual
- `a *= b` => `a = a * b` (same for all operators)

If you mix integers and real numbers, everything is converted to a real number and the result will be a real number.

**!! DO NOT MIX SIGNED AND UNSIGNED NUMBERS in operations : the rules of calculus are weird !! If you have to, CAST the unsigned variables into signed ones.**
**Only use UNISIGNED numbers for bitwise operations**

### Booleans

- booleans are seen as numbers : **0** is FALSE and **1** is TRUE.


## Conditional statements

#### if else
```c 
if ( condition ){
    // instructions
} else if (condition2 ){
    // instructions
} else{
    // instructions
}
```

#### while and do while

##### while
```c
while ( condition ){
    // instructions
}
```

Note : if the inside of the `while` block is empty, put a `;` in it : 
```c
while ( condition ){
    ; /* empty loop */
}
```
Note : `while (1)` is an infinite loop.

##### do while

```c
do {
    // instructions
} while ( condition );
```

#### for

```c
for (initialization ; condition ; incrementation){
    // instructions
}
```

Note : `,` (comma) to separate several expression inside a block.
```c
for ( i=0, j=99; i<j, j>4; i++, j--)
```
Note : each of the block ca be empty. This gives an infinite loop.
```c
for ( ; ; )
```

#### break and continue

- `break` : get out of the loop and jump to the next instruction after.
- `continue` :  forces the next iteration of the loop to take place, skipping any code in between. Starts again by checking the termination condition (only does another round if the termination condition is true).

#### switch
```c
switch( expression ){
    case exp-const1 : instructions ; break;
    case exp-const2 : instructions; break;
    default : instructions; break;
}
```

Don't forget the `break` statements after each case !
The last `break` in the `default` clause is not useful but VERY recommended to avoid mistakes.

## Constants

**DO NOT USE MAGIC NUMBERS**
Declare general constants at the beginning of the file, after the `#include` statements.
```c
#define NAME VALUE
```

## Methods

#### Definition

You can **declare** a method at the top of your file : just the name and the type of the parameters. The names of the parameters are optional. If you choose to put names, you don't have to reuse the same names when defining the method later.
You can then **define** it later in the file.

_You don't have to declare a method to define it_ **BUT YOU HAVE TO DEFINE A METHOD BEFORE YOU CALL IT**

Example :
```c 
#include <stdio.h>

double somme(int nb, double tab[]); // DECLARATION

int main(void){
    double tab[] = {-4.8, 6.1, 57.0, 23.99, -11.32, 4.5};
    int n = sizeof(tab)/sizeof(tab[i]);
    double s = somme(n, tab);
    printf("somme = %f\n",s);
}

double somme(int nb_elem, double t[]){ //DEFINITION
    double s=0;
    for(int i=0 ; i < nb_elem; i++)
        s += t[i];
    return s;
}
```


## Format a string

Flags : 
- %d : int
- %f : float
- %c : char
- %s : string

To only have a certain number of char printed : %.N and then the letter of the flag,
where N is the number of chars to print.

Format a string :
```c
int a = 4;
int b = 56;
printf("My value is : %d. And my second value is : %d", a, b);

float c = 23.456;
printf("My value is : %.3d", c); // will print 23.4
```
