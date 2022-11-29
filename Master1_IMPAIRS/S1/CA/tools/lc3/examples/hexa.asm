        .ORIG x3000

        ;; Point d'entrée du programme
        LD R0, a
        JSR hex
exit:   TRAP x25                ; exit

        ;; Constantes
a:      .FILL 42

        ;; ** hexa **
        ;; Hypothèse : R0 contient un entier à afficher en hexadécimal
        ;; Garantie : R4-R7 non modifiés
        ;; ************
hex:    ADD R2, R2, 4           ; R2 <- 4 quartets
        ADD R1, R0, 0           ; R1 <- R0
loop:   LD R3, cst4             ; for (i = 4; i > 0; i++) {
loops:  AND R1, R1, R1
        BRn loop1
        ADD R1, R1, R1
        BR loop2
loop1:  ADD R1, R1, R1
        ADD R1, R1, 1
loop2:  ADD R3, R3, -1          ; rotation de 1 bit vers gauche (faible -> fort)
        BRp loops               ; }
        AND R0, R1, xF
        ADD R0, R0, -10         ; R0 = (R1 & 0xF) - 10
        BRn aff2
        LD R3, code0
        BR aff3
aff2:   LD R3, codeA
aff3:   ADD R0, R0, R3
        TRAP x21
        ADD R2, R2, -1
        BRp loop
        RET

cst4:   .FILL 4
code0:  .FILL x40               ; code ASCII '0'
codeA:  .FILL x51               ; code ASCII 'A'

        .END
