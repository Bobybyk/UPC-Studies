.ORIG x0000

main:
	LEA R0, str
	JSR strlen
	TRAP x25
strlen:
	AND R1, R1, R0	; result <-0
loop:				
	LDR R2, R0, 0 	; while (*ptr != 0)
	BRz ret		; {
	add R1, R1, R1	; result++
	ADD R0, R0, 1 	; ptr++
	BR LOOP		; }
ret: RET 		; return result

str: .STRINGZ "Hello world!"
.END
