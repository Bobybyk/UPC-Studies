.ORIG x0000

A: .FILL 2
B: .FILL3

main: 
	LD R0, A
	LD R1, B
	JSR mult
	TRAP x25	; halt

mult:
	AND R2, R2, 0	; R2 = 0
	LD R3, cst16

loop:
	BRz ret
	ADD R2, R2, R2	; R2 *= 2
	ADD R1, R1, R1
	BRzp loop2
	ADD R2, R2, R0	; R2 += R0 si R1_k = 1

loop2:
	ADD R1, R1, R1	; R1 *=2
	ADD R3, R3, -1
	BR loop

ret: RET
cst16: .FILL 16

.END
