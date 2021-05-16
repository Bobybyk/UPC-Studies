# Exercice 1 du TP06 de C

## 1
```c
void f1 (const char *s) {
    for(int i = 0; s[i] != ’\0’; i++)
    printf ("%c", s[i]);
}
```
OK

## 2
```c
void f1 (const char *s) {
    for(; *s; s++)
    printf("%c", *s);
}
```
NON

## 3

```c
char *g1 (const char *s) {
    char *t;
    strcpy (t, s);
    return t;
}
```
NON : on n'a pas fait malloc, l'espace memoire alloue au pointeur va etre ecrase


## 4
```c
char *g2 (const char *s) {
    char *t = malloc (strlen (s));
    strcpy (t, s);
    return t;
}
```
NON : strlen(s) renvoie la longueur SANS le \0 a la fin

## 5

```c
char *g3 (const char *s) {
    char *t = malloc (strlen (s) + 1);
    strcpy (t, s);
    return t;
}
```
OUI
