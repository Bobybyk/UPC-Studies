.ORIG x3000

;; R0 = adresse du tableau
;; R1 = taille du tableau
;; R2 = somme

LEA R0		; adresse de tab (pas valeur) : R0 <- tab
AND R2, R2, 0	; R2 <- 0
LD R1, len	; R1 <- MEM[len]
BRnz end

loop:
	LDR R3, R0, 0
	ADD R2, R2, R3
	STR R2, R0, 0
	ADD R0, R0, 1
	ADD R1, R1, -1
	BRp loop
	TRAP x25

len: .FILL 10

	TRAP x25

tab: 
	.FILL 5 
	.FILL 2 
	.FILL 10 
	.FILL 3 
	.FILL 6 
	.FILL 120 
	.FILL 120 
	.FILL 10 
	.FILL 0 
	.FILL 1
.END
