.include "setup.inc"
main:
rep #$30
lda #$0000
tcd
lda #$0804
sta $0000
ldx $0000
txa
inc a
inc a
sta 0, x
lda $0000
clc
adc #$16
sta $0000
ldx #$02
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$03
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$04
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$05
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$06
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$07
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$08
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$09
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0a
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0b
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0c
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0d
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0e
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$0f
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$10
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$11
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$12
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$13
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$14
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx #$15
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
jsr method_main
infinite:
jmp infinite
method_func1:
lda $0002
ldx $0000
sta 0,x
txa
sta $0002
inc a
inc a
sta $0000
ldx #$0a
phx
ldx #$00
phx
ldx $0002
lda $02, x
pha
pla
plx
ply
pha
lda $0000
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty $0000
clc
adc $0000
clc
adc #$0004
tax
ply
pla
sta 0,x
sty $0000
method_func1_end:
ldy $0002
lda $0000, y
sta $0002
sty $0000
rts
method_main:
lda $0002
ldx $0000
sta 0,x
txa
sta $0002
inc a
inc a
sta $0000
ldx $0000
txa
inc a
inc a
sta 0, x
lda $0000
clc
adc #$16
sta $0000
ldx #$01
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldy $0828
phy
ldx #$00
phx
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx $0002
lda $02, x
sta $0000
pla
asl a
clc
adc $0000
tax
pla
sta $0000, x
plx
stx $0000
ldy $081c
phy
ldx #$00
phx
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx #$0000
lda $0804, x
sta $0000
pla
asl a
clc
adc $0000
tax
pla
sta $0000, x
plx
stx $0000
while_0:
ldx $0002
lda $18, x
pha
ldx #$0a
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare0
lda #$0001
jmp erapmoc0
compare0:
lda #$0000
erapmoc0:
stx $0000
pha
pla
cmp #$0000
beq elihwb_0
jmp whileS_0
elihwb_0:
jmp elihw_0
whileS_0:
ldx $0000
phx
ldx $0002
lda $18, x
pha
ldx #$01
phx
ply
plx
lda $0000
pha
sty $0000
txa
sec
sbc $0000
plx
stx $0000
pha
;getVal
ply
lda $0000
pha
phy
ldx $0002
lda $02, x
sta $0000
pla
asl a
clc
adc $0000
tax
lda $0000, x
plx
stx $0000
pha
ldx #$01
phx
plx
ply
lda $0000
pha
stx $0000
tya
clc
adc $0000
plx
stx $0000
pha
ldx $0002
lda $18, x
pha
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx $0002
lda $02, x
sta $0000
pla
asl a
clc
adc $0000
tax
pla
sta $0000, x
plx
stx $0000
ldx $0002
lda $18, x
pha
ldx #$01
phx
ply
plx
lda $0000
pha
sty $0000
txa
sec
sbc $0000
plx
stx $0000
pha
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0804, x
sta $0000
pla
asl a
clc
adc $0000
tax
lda $0000, x
plx
stx $0000
pha
ldx #$02
phx
plx
ply
lda $0000
pha
stx $0000
tya
clc
adc $0000
plx
stx $0000
pha
ldx $0002
lda $18, x
pha
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx #$0000
lda $0804, x
sta $0000
pla
asl a
clc
adc $0000
tax
pla
sta $0000, x
plx
stx $0000
ldx $0002
lda $18, x
pha
plx
inx
phx
pla
ldy $0002
sta $18, y
plx
stx $0000
jmp while_0
elihw_0:
ldx #$00
phx
pla
ldy $0002
sta $18, y
while_1:
ldx $0002
lda $18, x
pha
ldx #$0a
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare1
lda #$0001
jmp erapmoc1
compare1:
lda #$0000
erapmoc1:
stx $0000
pha
pla
cmp #$0000
beq elihwb_1
jmp whileS_1
elihwb_1:
jmp elihw_1
whileS_1:
ldx $0000
phx
ldx $0002
lda $18, x
pha
ldx #$00
phx
ldx $0002
lda $18, x
pha
ldy $0828
phy
plx
ply
lda $0000
pha
stx $0000
tya
clc
adc $0000
plx
stx $0000
pha
pla
plx
ply
pha
lda $0000
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty $0000
clc
adc $0000
clc
adc #$0004
tax
ply
pla
sta 0,x
sty $0000
ldx $0002
lda $18, x
pha
ldx #$01
phx
ldx $0002
lda $18, x
pha
;getVal
ply
lda $0000
pha
phy
ldx $0002
lda $02, x
sta $0000
pla
asl a
clc
adc $0000
tax
lda $0000, x
plx
stx $0000
pha
pla
plx
ply
pha
lda $0000
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty $0000
clc
adc $0000
clc
adc #$0004
tax
ply
pla
sta 0,x
sty $0000
ldx $0002
lda $18, x
pha
ldx #$02
phx
ldx $0002
lda $18, x
pha
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0804, x
sta $0000
pla
asl a
clc
adc $0000
tax
lda $0000, x
plx
stx $0000
pha
pla
plx
ply
pha
lda $0000
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty $0000
clc
adc $0000
clc
adc #$0004
tax
ply
pla
sta 0,x
sty $0000
ldx $0002
lda $18, x
pha
plx
inx
phx
pla
ldy $0002
sta $18, y
plx
stx $0000
jmp while_1
elihw_1:
lda $0002
ldx $0000
sta $0000,x
stx $0002
txa
clc
adc #$0002
sta $0000
ldy $0826
phy
ldx $0002
pla
sta $02, x
inc $0000
inc $0000
lda $0002
ldx $0000
sta $0000,x
stx $0002
jsr method_func1
ldx #$0b
phx
ldx #$00
phx
ldy $0840
phy
pla
plx
ply
pha
lda $0000
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty $0000
clc
adc $0000
clc
adc #$0004
tax
ply
pla
sta 0,x
sty $0000
jsr SetTiles
method_main_end:
ldy $0002
lda $0000, y
sta $0002
sty $0000
rts
.ENDS
