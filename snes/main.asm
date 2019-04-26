.include "setup.inc"
main:
rep #$30
ldx #$0004
stx $0002
lda #$0000
tcd
pla
ldx #$0804
sta 0, x
pla
ldx #$0806
sta 0, x
lda #$0808
sta $0000
tcd
jsr method_main
infinite:
jmp infinite
method_sqrt:
lda $0000
adc #6
sta $0000
rts
method_getDistance:
pla
ldx #$06
sta 0, x
pla
ldx #$08
sta 0, x
lda $0000
adc #10
sta $0000
rts
method_main:
tdc
adc #$02
ldx #$00
sta 0, x
tdc
adc #$18
ldx #$16
sta 0, x
pla
ldx #$2c
sta 0, x
lda $0000
adc #52
sta $0000
rts
.ENDS
