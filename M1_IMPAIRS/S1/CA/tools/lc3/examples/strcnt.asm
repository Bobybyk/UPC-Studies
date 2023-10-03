        .ORIG x3000

        ;; Point d'entrée du programme
        LEA R0, str
        LD R1, char
        JSR strcnt
exit:   TRAP x25                ; exit

        ;; Constantes
str:    .STRINGZ "Hello world!"
char:   .FILL 108               ; 'l'

        ;; ** strcnt **
        ;; Hypothèse : R0 contient l'adresse de la chaîne
        ;;             R1 contient le caractère dont à dénombrer
        ;; Garantie : R2 contient le nombre d'occurrences du caractère
        ;;            R4-R6 sont préservés
        ;; ************
strcnt: NOT R1, R1
        ADD R1, R1, 1           ; R1 <- -R1
        AND R2, R2, 0
        LDR R3, R0, 0
        BRz ret                 ; chaîne vide ?
loop:   ADD R3, R3, R1
        BRnp next               ; R3 == R1 ?
        ADD R2, R2, 1
next:   ADD R0, R0, 1
        LDR R3, R0, 0
        BRnp loop               ; boucle tant que chaîne non-vide
ret:    RET

        .END
