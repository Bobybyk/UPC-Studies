        .ORIG x3000

        ;; Point d'entrée du programme
        LEA R0, str
        JSR strlen
exit:   TRAP x25                ; exit

        ;; Constantes
str:    .STRINGZ "Hello world!"

        ;; ** strlen **
        ;; Hypothèse : R0 contient l'adresse de la chaîne
        ;; Garantie : R1 contient la longueur de la chaîne
        ;;            R3-R6 sont préservés
        ;; ************
strlen: NOT R1, R0
        ADD R1, R1, 1           ; R1 <- - R0
        LDR R2, R0, 0
        BRz ret                 ; chaîne vide ?
loop:   ADD R0, R0, 1           ; R0++
        LDR R2, R0, 0
        BRnp loop               ; boucle tant que chaîne non-vide
ret:    ADD R1, R0, R1          ; R1 <- R0 + R1 = strlen(str)
        RET

        .END
