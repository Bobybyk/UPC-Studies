        ;; On suit la convention d'appel suivante :
        ;;   - les arguments sont passés sur la pile, empilés de droite à gauche
        ;;   - R7 est caller-save, R1-R5 sont callee-save

        .ORIG x3000

        LEA R6, stbot

        ;; Note : on considère un peu arbitrairement que la fonction principale
        ;; n'a pas à sauvegarder les registres.

main:   AND R0, R0, 0
        ADD R0, R0, 3
        ADD R6, R6, -3
        STR R0, R6, 0           ; arg0 <- 3
        STR R0, R6, 2           ; arg2 <- 3
        ADD R0, R0, -2
        STR R0, R6, 1           ; arg1 <- 1
        JSR hanoi              ; hanoi(3, 1, 3)
        ADD R6, R6, 3           ; pop arg1, arg2, arg3

        AND R0, R0, 0
        TRAP x25                ; exit(0)

        ;; Fonction mvt(src, dst)
mvt:    ADD R6, R6, -3
        STR R0, R6, 0
        STR R1, R6, 1
        STR R7, R6, 2           ; prologue

        LD R1, char0
        LDR R0, R6, 3
        ADD R0, R0, R1          ; R0 <- arg0 + '0'
        TRAP x21
        LEA R0, arrow
        TRAP x22
        LDR R0, R6, 4           ; R0 <- arg1 + '0'
        ADD R0, R0, R1
        TRAP x21
        LD R0, charNL           ; R0 <- '\n'
        TRAP x21

mvt0:   LDR R7, R6, 2           ; épilogue
        LDR R1, R6, 1
        LDR R0, R6, 0
        ADD R6, R6, 3
        RET

        ;; Fonction hanoi(nb_disques, src, dst)
hanoi:  ADD R6, R6, -4
        STR R7, R6, 0
        STR R0, R6, 1
        STR R1, R6, 2
        STR R2, R6, 3           ; prologue

        LDR R0, R6, 4
        AND R0, R0, R0
        BRnz hanoi0             ; if (nb_disques <= 0) goto hanoi0

        ADD R0, R0, -1          ; R0 <- nb_disques - 1

        LDR R1, R6, 5           ; R1 <- src
        LDR R2, R6, 6           ; R2 <- tgt
        ADD R3, R1, R2
        NOT R3, R3
        ADD R3, R3, 7           ; itm = R3 <- 6 - src - dst

        ADD R6, R6, -3
        STR R0, R6, 0
        STR R1, R6, 1
        STR R3, R6, 2
        JSR hanoi              ; hanoi(nb_disques - 1, src, itm)

        STR R1, R6, 0
        STR R2, R6, 1
        JSR mvt                ; mvt(src, tgt)

        STR R0, R6, 0
        STR R3, R6, 1
        STR R2, R6, 2
        JSR hanoi              ; hanoi(nb_disques - 1, itm, tgt)
        ADD R6, R6, 3

hanoi0: LDR R2, R6, 3           ; prologue
        LDR R1, R6, 2
        LDR R0, R6, 1
        LDR R7, R6, 0
        ADD R6, R6, 4
        RET

char0:  .FILL 48                ; code de '0'
charNL: .FILL 10                ; code de '\n'
arrow:  .STRINGZ " --> "

sttop:  .BLKW 100
stbot:

        .END
