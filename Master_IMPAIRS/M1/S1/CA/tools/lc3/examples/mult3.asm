        .ORIG x3000

        ;; Point d'entrée du programme
        LD R0, a
        LD R1, b
        JSR mul
exit:   TRAP x25                ; exit

        ;; Constantes
a:      .FILL 4
b:      .FILL 3

        ;; ** mul **
        ;; Hypothèse : R0 et R1 contiennent les arguments A et B
        ;; Garantie : R2 contient le produit A * B
        ;;            R4-R7 non modifiés
        ;; *********
mul:    LD R3, cst16            ; R3 == k
        AND R2, R2, 0           ; R2 == x_k
loop:   ADD R2, R2, R2          ; R2 *= 2
        AND R1, R1, R1
        BRzp loop1              ; test du bit de poids fort de R1
        ADD R2, R2, R0          ; R2 += R0
loop1:  ADD R1, R1, R1          ; décalage de 1 bit de R1
        ADD R3, R3, -1          ; R2--
        BRnp loop               ; test R2 != 0
ret:    RET

cst16:  .FILL 16

        .END
