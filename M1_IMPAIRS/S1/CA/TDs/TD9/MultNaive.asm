; @param R0 x
; @param R1 n
; @return R2 x * n
        .ORIG x0000
        AND R0,R0,0
        ADD R0,R0,xF    ; x = 15
        AND R1,R1,0
        ADD R1,R1,x4    ; n = 4
        AND R2,R2,0     ; r = 0
        AND R1,R1,R1    ; Mise a jour des indicateurs
                        ; nzp pour le test
        BRz fini
loop:   ADD R2,R2,R0    ; r += x
        ADD R1,R1,-1    ; n--
        BRnp loop       ; Boucle si n != 0
fini:   NOP
        .END

