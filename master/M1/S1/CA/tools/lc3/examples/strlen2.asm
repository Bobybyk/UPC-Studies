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
strlen: AND R1, R1, 0
loop:   LDR R2, R0, 0
        BRz ret
        ADD R0, R0, 1
        ADD R1, R1, 1
        BR loop
ret:    RET

        .END
