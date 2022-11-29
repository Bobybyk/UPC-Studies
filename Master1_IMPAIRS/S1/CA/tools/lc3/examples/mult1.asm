        .ORIG x3000

        ;; Point d'entrée du programme
        LD R0, a
        LD R1, b
        JSR mul
exit:   TRAP x25                ; exit

        ;; Constantes
a:      .FILL 2
b:      .FILL 3

        ;; ** mul **
        ;; Hypothèse : R0 et R1 contiennent les arguments, positifs
        ;; Garantie : R2 contient le produit de R0 initial et de R1
        ;;            R2-R7 non modifiés
        ;; *********
mul:    AND R2, R2, 0
        AND R1, R1, R1
        BRz ret                 ; R1 == 0 ?
loop:   ADD R2, R2, R0          ; do { R2 += R0;
        ADD R1, R1, -1          ;      R1--;
        BRp loop                ;    } while (R1 > 0);
ret:    RET

        .END
