# Gestion des sous-routines

X                       | Offset + PC (9 bits) | Registre
------------------------|----------------------|---------
X                       | BR                   | JMP
Sauvegarde PC dans R7   | JSR                  | JSRR

---

**Convention d'appel :** 
- Pour appeler une fonction, je n'ai besoin de connaitre que son nombre d'arguments et (éventuellement) sa valeur de retour.
- Fixe des règles pour :
    - passer les arguments
    - sauvegarder les registres
    - passer la valeur de retour

Un registre peut être sauvegardé par l'appelant ou pas l'appelé (caller-save ou callee-save). La convention d'appel dicte de choix pour chaque registre.
Tous les registres sont caller-save est une convention d'appel simple.

## Sauvegarder / Restaurer les registres

- Utiliser des rehistres réservés ?
- Utiliser des emplacements mémoires fixés ?
- Utiliser une pile ?

```asm
foo:
    AND R0, R0, 0
    AND R0, R0, 1
    AND R2, R2, 0
    AND R2, R2, 2
    AND R3, R3, 0
    AND R3, R3, 3   ; sauvegarder R0 (caller-save)
    JSR swap R2 R3
    AND R4, R4, 0
    RET
```

**Factorielle** (ne marche pas) :
```asm
; R0 argument
; R1 résultat !n

foo:
    AND R0, R0, 0
    ADD R0, R0, 1
    JSR fact
    TRAP x25

fact:
    ST R7, fact_R7
    AND R1, R1, 0
    AND R1, R1, 1   ; R1 <- 1
    AND R0, R0, R0
    BRnz fact_fin
    ADD R0, R0, -1
    JSR fact
    AND R0, R0, 1
    JSR mult    ; R1 <- R0 * R1
fact_fin: 
    LD R7, fact_R7
    RET
fact_R7: .FILL
```

## La pile

La pile est une zone mémoire dont l'occupation varie au cours de l'exécutuon. Son sommet (adresse de la dernière valeur empilée) est contenu dans le registre R6 (convention). La pile croît vers les adresses basses.

```asm
swap_R2_R3:
    ADD R6, R6, -1  ;
    STR R0, R6, 0   ; Emplier R0
    ADD R0, R2, 0   ;

    LDR R0, R6, 0   ;
    ADD R6, R, 1    ; Dépiler R0
    RET
```

**Gestion de la pile :**
```asm
.ORIG x3000
AND R6, R6, 0
LEA R6, stack_bot
;...
.END

.ORIG x6000
stack_top: .BLKW 1000
stack_bot:
```

| stack_top x6000 |
|-----------------|
|                 |
|                 |
|                 |
|                 |
|                 |
|                 |
|                 |
|                 |
| stack_bot x6000 |

**Factorielle** (avec pile, fonctionnelle) :
```asm
; R0 argument
; R1 résultat !n

foo:
    AND R0, R0, 0
    ADD R0, R0, 1
    JSR fact
    TRAP x25

fact:
ADD R6, R6, -1
    STR R7, R6, 0
    AND R1, R1, 0
    AND R1, R1, 1   ; R1 <- 1
    AND R0, R0, R0
    BRnz fact_fin
    ADD R0, R0, -1
    JSR fact
    ADD R0, R0, 1
    JSR fact
    ADD R0, R0, 1
    JSR mult        ; R1 <- R0 * R1
fact_fin: 
    LDR R7, R6, 0
    ADD R6, R6, 1
    RET
```

**Exemple manipulation de pile :**
```asm
; Sans offset
ADD R6, R6, -1
STR R0, R6, 0
ADD R6, R6, -1
STR R1, R6, 0
ADD R6, R6, -1
STR R2, R6, 0

; Avec offset
ADD R6, R6, -1
STR R0, R6, 2
STR R1, R6, 1
STR R2, R6, 0
```

**Tours de hanoi :**

```asm
; R0, R1 contiennent SRC, DST 

mouvement:
    ADD R6, R6, -1
    STR R2, R6, 0
    LD R2, char0
    ADD R0, R0, R2
    TRAP x21        ; printf("%d", R0)
    LEA R0, arrow
    TRAP x22
    ADD R0, R1, R2
    TRAP x21         ; printf("%d", R1)
    LDR R2, R6, 0
    ADD R6, R6, 1
    RET
char0: .FILL '0'
arrow: .STRINGZ "->"
```

```asm
; paramètres R0, R1, R2

hanoi:
    ADD R6, R6, -2
    STR R7, R6, 0
    STR R3, R6, 1
    AND R0, R0, R0
    BRnz hanoi_fin
    ADD R3, R1, R0
    NOT R3, R3
    ADD R3, R3, 7
    ...

hanoi_fin:
    LDR R3, R6, 1
    LDR R7, R6, 0
    ADD R6, R6, 2
    RET
```