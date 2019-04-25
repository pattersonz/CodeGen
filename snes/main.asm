.include "setup.inc"
main:	
	ldx #$0002
	stx $2118
	phx
	inx
	stx $2118
	phx
	inx
	stx $2118
	phx
	inx
	stx $2118
	phx
	inx
	phx
	stx $2118
	ldx #$000a
	stx $0000
	ldx #$0007
	stx $0002
	stx $0004
	stx $0006
	stx $0008
	stx $000a
	stx $000c
	stx $000e
	stx $0010
	stx $0012
	stx $0014
	stx $0016
	inx
	stx $2118
	phd
	
	rep #$20
	lda #$0004
	tcd
	ldy #$0000
	lda $00, y
	sta $2118
	ldx #10
	stx $2118
	ldy #$02
	lda $00, y
	pld
	ldx #$07
	stx $2118
	tax
	stx $2118

forever:
	jmp forever
	.ENDS
