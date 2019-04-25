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
method_getDistance:
pla
stx #6
sta 0, x
pla
stx #8
sta 0, x
lda $0000
adc #10
sta $0000
rts
method_main:
sta #2
stx #0
sta 0, x
sta #24
stx #22
sta 0, x
pla
stx #44
sta 0, x
lda $0000
adc #52
sta $0000
rts
.ENDS
