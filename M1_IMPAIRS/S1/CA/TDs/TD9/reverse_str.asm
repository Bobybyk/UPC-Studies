.ORIG x3000

str: .STRINGZ "Hello World"

;; @param R0 l'adresse de la chaine
;; @return R1 la longueur de la chaîne
;; R5, R6 est écrasé

strlen:
	AND R6, R6, 0
	AND R6, R6, R0	; R6 <- R0
	AND R1, R1, 0	; R1 <- 0
	LDR R5, R6, 0	; R5 <- MEM[R6]
	BRz ret0
loop0:
	ADD R1, R1, 1	; R1++
	ADD R6, R6, 1	; R6++
	LD R5, R6, 0
	BRnp loop0	; goto loop0 si MEM[R6] != 0
ret0: RET

swap:
	LDR R5, R0, 0
	LDR R6, R1, 0
	STR R5, R1, 0
	STR R6, R0, 0
	RET
.END
