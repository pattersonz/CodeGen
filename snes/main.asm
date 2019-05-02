.include "setup.inc"
main:
rep #$30
lda #$0000
tcd
lda #$0810
sta TOP
ldx $0000
txa
inc a
inc a
sta 0, x
lda TOP
clc
adc #$16
sta TOP
ldx #$02
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$03
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$04
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$05
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$06
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$07
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$08
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$09
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0a
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0b
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0c
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0d
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0e
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0f
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$10
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$11
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$12
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$13
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$14
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$15
phx
pla
ldx $0000
sta 0, x
lda TOP
clc
adc #$02
sta TOP
ldx #$084e
stx AR
jsr method_main
infinite:
jmp infinite
method_draw:
ldx $0002
lda $00, x
pha
ldx $0002
lda $02, x
pha
ldx $0002
lda $04, x
pha
pla
plx
ply
pha
lda TOP
pha
tya
.rept 6
asl a
.endr
tay
txa
asl a
sty TOP
clc
adc TOP
clc
adc #$0010
tax
ply
pla
sta 0,x
sty TOP
method_draw_end:
rts
method_main:
ldx #$01
phx
pla
ldx $0000
sta 0, x
lda $0000
clc
adc #$02
sta $0000
ldx $0002
phx
ldx #$00
phx
ldx $0000
pla
sta $00, x
ldx #$00
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx $0000
stx $0002
lda $0000
clc
adc #$06
jsr method_draw
lda $0002
sta $0000
ply
sty $0002
while_0:
ldx #$01
phx
pla
cmp #$0000
beq elihwb_0
jmp whileS_0
elihwb_0:
jmp elihw_0
whileS_0:
ldx $0000
phx
wai
lda Joy1Held
and #$0080
php
sep #$20
pla
lsr a
and #$01
eor #$01
tax
rep #$20
phx
pla
cmp #$0000
beq elseb_0
jmp ifS_0
elseb_0:
jmp else_0
ifS_0:
if_0:
ldx $0000
phx
ldx $0002
phx
ldx $0002
lda $00, x
pha
ldx $0000
pla
sta $00, x
ldx $0002
lda $00, x
pha
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0002
lda $00, x
pha
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
ldx $0000
pla
sta $04, x
ldx $0000
stx $0002
lda $0000
clc
adc #$06
jsr method_draw
lda $0002
sta $0000
ply
sty $0002
ldx $0002
lda $00, x
pha
plx
inx
phx
pla
ldy $0002
sta $00, y
plx
stx $0000
jmp fi_0
else_0:
fi_0:
jsr SetTiles
wai
plx
stx $0000
jmp while_0
elihw_0:
method_main_end:
rts
.ENDS
