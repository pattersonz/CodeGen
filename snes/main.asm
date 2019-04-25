.incude"setup.inc"
main:
rep #$30
ldx #$0004
stx $0002
lda #$0000
tcd
pla
stx #2052
sta 0, x
pla
stx #2054
sta 0, x
lda #2056
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
.ENDS
