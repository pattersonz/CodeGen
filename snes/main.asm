.include "setup.inc"
main:	
	ldx #$0002
	stx $2118
	phx
	ldx #$0003
	phx
	phx
	stx $2118
	rep #$20
	tsc
	adc #$03
	tcs
	sep #$20
	plx
	stx $2118
forever:
	jmp forever
	.ENDS
