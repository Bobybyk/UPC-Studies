.ORIG x0000
main: 
	LD R0, A
	LD R1, B
	JSR mul0
	TRAP x25	; halt
A: .FILL 2
B: .FILL 3
mul0: 
	ADD R2, R2, R0	; R2 = 0
	AND R1, R1, R1
	BRz ret0
loop:	
	ADD R2, R0, R0	; R2 += R0
	ADD R1, R1, -1	; R1--
	BRnp loop
	RET
.END
