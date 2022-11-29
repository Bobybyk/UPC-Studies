;; Convention d'appel:
;;	- arguments passés sur la pile, avec le premier au sommet, 
;;	- le suivant et ainsi de suite
;; 	- R7 est sauvegardé par l'appelant
;;	- R0-R5 sont sauvegardés par l'appelé

.ORIG x3000
LEA R6, stbot

;; mvt(src, tgt)
mvt:	
	;; prologue
	ADD R6, R6, -3
	STR R0, R6, 0
	STR R1, R6, 1
	STR R7, R6, 2

	;; code de mouvement
	LD R1, char0
	LDR R0, R6, 3
	ADD R0, R0, R1		; R0 <- src + '0'
	TRAP x21		; putc(src + '0')
	LEA R0, arrow
	TRAP x22		; printf(" --> ");
	LDR R0, R6, 4
	ADD R0, R0, R1		; R0 <- tgt + '0'
	TRAP x21		; putc(tgt + '0')
	LD R0, charNL
	TRAP x21		; putc('\n')

	;; épilogue
	LDR R0, R6, 0
	LDR R1, R6, 1
	LDR R7, R6, 2
	RET
char0: .FILL 48		; 48 = '0'
charNL: .FILL 10
arrow: .STRINGZ " ---> "

;; hanoi(nb_disques, src, tqt)
hanoi:
	;; prologue
	ADD R6, R6, -5
	STR R0, R6, 0
	STR R1, R6, 1
	STR R2, R6, 2
	STR R3, R6, 3
	STR R7, R6, 4
	
	LDR R0, R6, 5		; R0 <- nb_disques
	BRnz hanoi0		; if (nb_disques <= 0) goto hanoi0

	ADD R0, R0, -1		; nb_disques <- nb_disques - 1
	LDR R1, R6, 6		; R1 <- src
	LDR R2, R6, 7		; R2 <- tgt
	ADD R3, R1, R2
	NOT R3, R3
	ADD R3, R3, 7		; itm = R3 <- 6 - src - tgt

	ADD R6, R6, -3
	STR R3, R6, 2
	STR R1, R6, 1
	STR R0, R6, 0
	JSR hanoi 		; hanoi(nb_disques - 1, src, itm)

	ADD R6, R6, -2
	STR R2, R6, 1
	STR R1, R6, 0
	JSR mvt			; mvt(src, tgt)

	STR R2, R6, 2
	STR R3, R6, 1
	STR R0, R6, 0
	JSR hanoi		; hanoi(nb_disques -1, itm, tgt)
	ADD R6, R6, 3 
	 
hanoi0:
	
	ADD R6, R6, 5
	RET
;; On suppose que main n'a pas besoin de préserver les registres
main:
	AND R0, R0, 0
	ADD R0, R0, 3
	STR R0, R6, 2		; R[2] <- 1
	ADD R0, R6, 0		; R[0] <- 		
	STR R0, R6, 		; R6[1] <- 3
	JSR hanoi		; hanoi(3, 1, 3)
	ADD R6, R6, 2
sttop:	.BLKW 100
stbot:	.END	
