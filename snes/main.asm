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
sta $0000, x
lda TOP
clc
adc #$14
sta TOP
lda TOP
clc
adc #$02
sta TOP
ldx #$01
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$02
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$03
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$04
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$05
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$06
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$07
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$08
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$09
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0a
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0b
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0c
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0d
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0e
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0f
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$10
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$11
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$12
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$13
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$14
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$15
phx
pla
ldx $0000
sta $0000, x
lda TOP
clc
adc #$02
sta TOP
ldx #$0850
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
method_isValid:
ldx #$00
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare09
lda #$0000
jmp erapmoc09
compare09:
lda #$0001
erapmoc09:
stx $0000
pha
plx
cpx #$0000
beq isFalseB08
jmp doRight08
isFalseB08:
jmp compare08
doRight08:
ldx #$01
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare0a
lda #$0000
jmp erapmoc0a
compare0a:
lda #$0001
erapmoc0a:
stx $0000
pha
plx
cpx #$0000
beq compare08
ldx #$0001
jmp erapmoc08
compare08:
ldx #$0000
erapmoc08:
phx
plx
cpx #$0000
beq isFalseB07
jmp doRight07
isFalseB07:
jmp compare07
doRight07:
ldx #$02
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare0b
lda #$0000
jmp erapmoc0b
compare0b:
lda #$0001
erapmoc0b:
stx $0000
pha
plx
cpx #$0000
beq compare07
ldx #$0001
jmp erapmoc07
compare07:
ldx #$0000
erapmoc07:
phx
plx
cpx #$0000
bne isTrueB06
jmp doRight06
isTrueB06:
jmp compare06
doRight06:
ldx #$03
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare0e
lda #$0000
jmp erapmoc0e
compare0e:
lda #$0001
erapmoc0e:
stx $0000
pha
plx
cpx #$0000
beq isFalseB0d
jmp doRight0d
isFalseB0d:
jmp compare0d
doRight0d:
ldx #$04
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare0f
lda #$0000
jmp erapmoc0f
compare0f:
lda #$0001
erapmoc0f:
stx $0000
pha
plx
cpx #$0000
beq compare0d
ldx #$0001
jmp erapmoc0d
compare0d:
ldx #$0000
erapmoc0d:
phx
plx
cpx #$0000
beq isFalseB0c
jmp doRight0c
isFalseB0c:
jmp compare0c
doRight0c:
ldx #$05
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare10
lda #$0000
jmp erapmoc10
compare10:
lda #$0001
erapmoc10:
stx $0000
pha
plx
cpx #$0000
beq compare0c
ldx #$0001
jmp erapmoc0c
compare0c:
ldx #$0000
erapmoc0c:
phx
plx
cpx #$0000
bne compare06
ldx #$0000
jmp erapmoc06
compare06:
ldx #$0001

erapmoc06:
phx
plx
cpx #$0000
bne isTrueB05
jmp doRight05
isTrueB05:
jmp compare05
doRight05:
ldx #$06
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare13
lda #$0000
jmp erapmoc13
compare13:
lda #$0001
erapmoc13:
stx $0000
pha
plx
cpx #$0000
beq isFalseB12
jmp doRight12
isFalseB12:
jmp compare12
doRight12:
ldx #$07
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare14
lda #$0000
jmp erapmoc14
compare14:
lda #$0001
erapmoc14:
stx $0000
pha
plx
cpx #$0000
beq compare12
ldx #$0001
jmp erapmoc12
compare12:
ldx #$0000
erapmoc12:
phx
plx
cpx #$0000
beq isFalseB11
jmp doRight11
isFalseB11:
jmp compare11
doRight11:
ldx #$08
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare15
lda #$0000
jmp erapmoc15
compare15:
lda #$0001
erapmoc15:
stx $0000
pha
plx
cpx #$0000
beq compare11
ldx #$0001
jmp erapmoc11
compare11:
ldx #$0000
erapmoc11:
phx
plx
cpx #$0000
bne compare05
ldx #$0000
jmp erapmoc05
compare05:
ldx #$0001

erapmoc05:
phx
plx
cpx #$0000
bne isTrueB04
jmp doRight04
isTrueB04:
jmp compare04
doRight04:
ldx #$00
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare18
lda #$0000
jmp erapmoc18
compare18:
lda #$0001
erapmoc18:
stx $0000
pha
plx
cpx #$0000
beq isFalseB17
jmp doRight17
isFalseB17:
jmp compare17
doRight17:
ldx #$03
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare19
lda #$0000
jmp erapmoc19
compare19:
lda #$0001
erapmoc19:
stx $0000
pha
plx
cpx #$0000
beq compare17
ldx #$0001
jmp erapmoc17
compare17:
ldx #$0000
erapmoc17:
phx
plx
cpx #$0000
beq isFalseB16
jmp doRight16
isFalseB16:
jmp compare16
doRight16:
ldx #$06
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare1a
lda #$0000
jmp erapmoc1a
compare1a:
lda #$0001
erapmoc1a:
stx $0000
pha
plx
cpx #$0000
beq compare16
ldx #$0001
jmp erapmoc16
compare16:
ldx #$0000
erapmoc16:
phx
plx
cpx #$0000
bne compare04
ldx #$0000
jmp erapmoc04
compare04:
ldx #$0001

erapmoc04:
phx
plx
cpx #$0000
bne isTrueB03
jmp doRight03
isTrueB03:
jmp compare03
doRight03:
ldx #$01
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare1d
lda #$0000
jmp erapmoc1d
compare1d:
lda #$0001
erapmoc1d:
stx $0000
pha
plx
cpx #$0000
beq isFalseB1c
jmp doRight1c
isFalseB1c:
jmp compare1c
doRight1c:
ldx #$04
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare1e
lda #$0000
jmp erapmoc1e
compare1e:
lda #$0001
erapmoc1e:
stx $0000
pha
plx
cpx #$0000
beq compare1c
ldx #$0001
jmp erapmoc1c
compare1c:
ldx #$0000
erapmoc1c:
phx
plx
cpx #$0000
beq isFalseB1b
jmp doRight1b
isFalseB1b:
jmp compare1b
doRight1b:
ldx #$07
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare1f
lda #$0000
jmp erapmoc1f
compare1f:
lda #$0001
erapmoc1f:
stx $0000
pha
plx
cpx #$0000
beq compare1b
ldx #$0001
jmp erapmoc1b
compare1b:
ldx #$0000
erapmoc1b:
phx
plx
cpx #$0000
bne compare03
ldx #$0000
jmp erapmoc03
compare03:
ldx #$0001

erapmoc03:
phx
plx
cpx #$0000
bne isTrueB02
jmp doRight02
isTrueB02:
jmp compare02
doRight02:
ldx #$02
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare22
lda #$0000
jmp erapmoc22
compare22:
lda #$0001
erapmoc22:
stx $0000
pha
plx
cpx #$0000
beq isFalseB21
jmp doRight21
isFalseB21:
jmp compare21
doRight21:
ldx #$05
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare23
lda #$0000
jmp erapmoc23
compare23:
lda #$0001
erapmoc23:
stx $0000
pha
plx
cpx #$0000
beq compare21
ldx #$0001
jmp erapmoc21
compare21:
ldx #$0000
erapmoc21:
phx
plx
cpx #$0000
beq isFalseB20
jmp doRight20
isFalseB20:
jmp compare20
doRight20:
ldx #$08
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare24
lda #$0000
jmp erapmoc24
compare24:
lda #$0001
erapmoc24:
stx $0000
pha
plx
cpx #$0000
beq compare20
ldx #$0001
jmp erapmoc20
compare20:
ldx #$0000
erapmoc20:
phx
plx
cpx #$0000
bne compare02
ldx #$0000
jmp erapmoc02
compare02:
ldx #$0001

erapmoc02:
phx
plx
cpx #$0000
bne isTrueB01
jmp doRight01
isTrueB01:
jmp compare01
doRight01:
ldx #$00
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare27
lda #$0000
jmp erapmoc27
compare27:
lda #$0001
erapmoc27:
stx $0000
pha
plx
cpx #$0000
beq isFalseB26
jmp doRight26
isFalseB26:
jmp compare26
doRight26:
ldx #$04
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare28
lda #$0000
jmp erapmoc28
compare28:
lda #$0001
erapmoc28:
stx $0000
pha
plx
cpx #$0000
beq compare26
ldx #$0001
jmp erapmoc26
compare26:
ldx #$0000
erapmoc26:
phx
plx
cpx #$0000
beq isFalseB25
jmp doRight25
isFalseB25:
jmp compare25
doRight25:
ldx #$08
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare29
lda #$0000
jmp erapmoc29
compare29:
lda #$0001
erapmoc29:
stx $0000
pha
plx
cpx #$0000
beq compare25
ldx #$0001
jmp erapmoc25
compare25:
ldx #$0000
erapmoc25:
phx
plx
cpx #$0000
bne compare01
ldx #$0000
jmp erapmoc01
compare01:
ldx #$0001

erapmoc01:
phx
plx
cpx #$0000
bne isTrueB00
jmp doRight00
isTrueB00:
jmp compare00
doRight00:
ldx #$02
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare2c
lda #$0000
jmp erapmoc2c
compare2c:
lda #$0001
erapmoc2c:
stx $0000
pha
plx
cpx #$0000
beq isFalseB2b
jmp doRight2b
isFalseB2b:
jmp compare2b
doRight2b:
ldx #$04
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare2d
lda #$0000
jmp erapmoc2d
compare2d:
lda #$0001
erapmoc2d:
stx $0000
pha
plx
cpx #$0000
beq compare2b
ldx #$0001
jmp erapmoc2b
compare2b:
ldx #$0000
erapmoc2b:
phx
plx
cpx #$0000
beq isFalseB2a
jmp doRight2a
isFalseB2a:
jmp compare2a
doRight2a:
ldx #$06
phx
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx $0002
lda $00, x
pha
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare2e
lda #$0000
jmp erapmoc2e
compare2e:
lda #$0001
erapmoc2e:
stx $0000
pha
plx
cpx #$0000
beq compare2a
ldx #$0001
jmp erapmoc2a
compare2a:
ldx #$0000
erapmoc2a:
phx
plx
cpx #$0000
bne compare00
ldx #$0000
jmp erapmoc00
compare00:
ldx #$0001

erapmoc00:
phx
pla
ldy AR
sta $0000, y
jmp method_isValid_end
method_isValid_end:
rts
method_getWinner:
ldy $0824
phy
plx
inx
phx
plx
stx $0824
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
lda TOP
clc
adc #$00
sta AR
lda TOP
clc
adc #$02
sta TOP
jsr method_isValid
ldy AR
lda $0000, y
tax
tya
sec
sbc #$00
sta TOP
ply
sty AR
phx
pla
cmp #$0000
beq elseb_0
jmp ifS_0
elseb_0:
jmp else_0
ifS_0:
if_0:
ldy $0832
phy
pla
ldy AR
sta $0000, y
jmp method_getWinner_end
jmp fi_0
else_0:
ldx AR
phx
ldx #$02
phx
ldx $0000
pla
sta $00, x
lda TOP
clc
adc #$00
sta AR
lda TOP
clc
adc #$02
sta TOP
jsr method_isValid
ldy AR
lda $0000, y
tax
tya
sec
sbc #$00
sta TOP
ply
sty AR
phx
pla
cmp #$0000
beq elseb_1
jmp ifS_1
elseb_1:
jmp else_1
ifS_1:
if_1:
ldy $082e
phy
pla
ldy AR
sta $0000, y
jmp method_getWinner_end
jmp fi_1
else_1:
ldy $0824
phy
ldx #$09
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare2f
lda #$0000
jmp erapmoc2f
compare2f:
lda #$0001
erapmoc2f:
stx $0000
pha
pla
cmp #$0000
beq elseb_2
jmp ifS_2
elseb_2:
jmp else_2
ifS_2:
if_2:
ldy $0828
phy
pla
ldy AR
sta $0000, y
jmp method_getWinner_end
jmp fi_2
else_2:
fi_2:
fi_1:
fi_0:
ldx #$00
phx
pla
ldy AR
sta $0000, y
jmp method_getWinner_end
method_getWinner_end:
rts
method_possibleSpot:
ldx $0002
lda $00, x
pha
;getVal
ply
lda $0000
pha
phy
ldx #$0000
lda $0810, x
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
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare30
lda #$0000
jmp erapmoc30
compare30:
lda #$0001
erapmoc30:
stx $0000
pha
pla
ldy AR
sta $0000, y
jmp method_possibleSpot_end
method_possibleSpot_end:
rts
method_drawAt:
ldx #$00
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
ldx #$02
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
ldx $0002
lda $00, x
pha
ldx #$05
phx
pla
ply
ldx $0000
sty $0000
cmp $0000
bpl compare31
lda #$0001
jmp erapmoc31
compare31:
lda #$0000
erapmoc31:
stx $0000
pha
pla
cmp #$0000
beq elseb_3
jmp ifS_3
elseb_3:
jmp else_3
ifS_3:
if_3:
ldx #$04
phx
pla
ldy $0002
sta $04, y
jmp fi_3
else_3:
ldx $0002
lda $00, x
pha
ldx #$02
phx
pla
ply
ldx $0000
sty $0000
cmp $0000
bpl compare32
lda #$0001
jmp erapmoc32
compare32:
lda #$0000
erapmoc32:
stx $0000
pha
pla
cmp #$0000
beq elseb_4
jmp ifS_4
elseb_4:
jmp else_4
ifS_4:
if_4:
ldx #$02
phx
pla
ldy $0002
sta $04, y
jmp fi_4
else_4:
fi_4:
fi_3:
ldx $0002
lda $00, x
pha
ldx #$08
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare35
lda #$0000
jmp erapmoc35
compare35:
lda #$0001
erapmoc35:
stx $0000
pha
plx
cpx #$0000
bne isTrueB34
jmp doRight34
isTrueB34:
jmp compare34
doRight34:
ldx $0002
lda $00, x
pha
ldx #$05
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare36
lda #$0000
jmp erapmoc36
compare36:
lda #$0001
erapmoc36:
stx $0000
pha
plx
cpx #$0000
bne compare34
ldx #$0000
jmp erapmoc34
compare34:
ldx #$0001

erapmoc34:
phx
plx
cpx #$0000
bne isTrueB33
jmp doRight33
isTrueB33:
jmp compare33
doRight33:
ldx $0002
lda $00, x
pha
ldx #$02
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare37
lda #$0000
jmp erapmoc37
compare37:
lda #$0001
erapmoc37:
stx $0000
pha
plx
cpx #$0000
bne compare33
ldx #$0000
jmp erapmoc33
compare33:
ldx #$0001

erapmoc33:
phx
pla
cmp #$0000
beq elseb_5
jmp ifS_5
elseb_5:
jmp else_5
ifS_5:
if_5:
ldx #$06
phx
pla
ldy $0002
sta $06, y
jmp fi_5
else_5:
ldx $0002
lda $00, x
pha
ldx #$07
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare3a
lda #$0000
jmp erapmoc3a
compare3a:
lda #$0001
erapmoc3a:
stx $0000
pha
plx
cpx #$0000
bne isTrueB39
jmp doRight39
isTrueB39:
jmp compare39
doRight39:
ldx $0002
lda $00, x
pha
ldx #$04
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare3b
lda #$0000
jmp erapmoc3b
compare3b:
lda #$0001
erapmoc3b:
stx $0000
pha
plx
cpx #$0000
bne compare39
ldx #$0000
jmp erapmoc39
compare39:
ldx #$0001

erapmoc39:
phx
plx
cpx #$0000
bne isTrueB38
jmp doRight38
isTrueB38:
jmp compare38
doRight38:
ldx $0002
lda $00, x
pha
ldx #$01
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare3c
lda #$0000
jmp erapmoc3c
compare3c:
lda #$0001
erapmoc3c:
stx $0000
pha
plx
cpx #$0000
bne compare38
ldx #$0000
jmp erapmoc38
compare38:
ldx #$0001

erapmoc38:
phx
pla
cmp #$0000
beq elseb_6
jmp ifS_6
elseb_6:
jmp else_6
ifS_6:
if_6:
ldx #$04
phx
pla
ldy $0002
sta $06, y
jmp fi_6
else_6:
fi_6:
fi_5:
ldx AR
phx
ldx $0002
lda $04, x
pha
ldx $0000
pla
sta $00, x
ldx $0002
lda $06, x
pha
ldx $0000
pla
sta $02, x
ldx $0002
lda $02, x
pha
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
method_drawAt_end:
rts
method_main:
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
ldx #$00
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
lda $0000
clc
adc #$02
sta $0000
lda $0000
clc
adc #$02
sta $0000
lda $0000
clc
adc #$02
sta $0000
while_1:
ldx $0002
lda $00, x
pha
ldx #$20
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare3d
lda #$0001
jmp erapmoc3d
compare3d:
lda #$0000
erapmoc3d:
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
ldx #$00
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
while_2:
ldx $0002
lda $08, x
pha
ldx #$20
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare3e
lda #$0001
jmp erapmoc3e
compare3e:
lda #$0000
erapmoc3e:
stx $0000
pha
pla
cmp #$0000
beq elihwb_2
jmp whileS_2
elihwb_2:
jmp elihw_2
whileS_2:
ldx $0000
phx
ldx AR
phx
ldx $0002
lda $00, x
pha
ldx $0000
pla
sta $00, x
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $02, x
ldy $0826
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx $0002
lda $08, x
pha
plx
inx
phx
pla
ldy $0002
sta $08, y
plx
stx $0000
jmp while_2
elihw_2:
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
jmp while_1
elihw_1:
ldx AR
phx
ldx #$00
phx
ldx $0000
pla
sta $00, x
ldx #$03
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$00
phx
ldx $0000
pla
sta $00, x
ldx #$05
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$02
phx
ldx $0000
pla
sta $00, x
ldx #$03
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$02
phx
ldx $0000
pla
sta $00, x
ldx #$05
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$04
phx
ldx $0000
pla
sta $00, x
ldx #$03
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$04
phx
ldx $0000
pla
sta $00, x
ldx #$05
phx
ldx $0000
pla
sta $02, x
ldy $0834
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
ldx #$02
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
ldx #$04
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
ldx #$06
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$03
phx
ldx $0000
pla
sta $00, x
ldx #$02
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$03
phx
ldx $0000
pla
sta $00, x
ldx #$04
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$03
phx
ldx $0000
pla
sta $00, x
ldx #$06
phx
ldx $0000
pla
sta $02, x
ldy $084c
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
ldx #$03
phx
ldx $0000
pla
sta $02, x
ldy $084a
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$01
phx
ldx $0000
pla
sta $00, x
ldx #$05
phx
ldx $0000
pla
sta $02, x
ldy $084a
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$03
phx
ldx $0000
pla
sta $00, x
ldx #$03
phx
ldx $0000
pla
sta $02, x
ldy $084a
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx #$03
phx
ldx $0000
pla
sta $00, x
ldx #$05
phx
ldx $0000
pla
sta $02, x
ldy $084a
phy
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
jsr SetTiles
wai
ldx #$00
phx
pla
ldy $0002
sta $00, y
while_3:
ldx $0002
lda $00, x
pha
ldx #$09
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare3f
lda #$0001
jmp erapmoc3f
compare3f:
lda #$0000
erapmoc3f:
stx $0000
pha
pla
cmp #$0000
beq elihwb_3
jmp whileS_3
elihwb_3:
jmp elihw_3
whileS_3:
ldx $0000
phx
ldx #$00
phx
ldx $0002
lda $00, x
pha
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx #$0000
lda $0810, x
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
jmp while_3
elihw_3:
ldx #$00
phx
pla
ldy $0002
sta $02, y
ldx #$00
phx
plx
stx $0824
ldx #$01
phx
pla
ldy $0002
sta $04, y
while_4:
ldx $0002
lda $02, x
pha
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare40
lda #$0000
jmp erapmoc40
compare40:
lda #$0001
erapmoc40:
stx $0000
pha
pla
cmp #$0000
beq elihwb_4
jmp whileS_4
elihwb_4:
jmp elihw_4
whileS_4:
ldx $0000
phx
ldx #$00
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
lda $0000
clc
adc #$02
sta $0000
ldx #$00
phx
pla
ldy $0002
sta $06, y
while_5:
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
lda TOP
clc
adc #$00
sta AR
lda TOP
clc
adc #$02
sta TOP
jsr method_possibleSpot
ldy AR
lda $0000, y
tax
tya
sec
sbc #$00
sta TOP
ply
sty AR
phx
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare41
lda #$0000
jmp erapmoc41
compare41:
lda #$0001
erapmoc41:
stx $0000
pha
pla
cmp #$0000
beq elihwb_5
jmp whileS_5
elihwb_5:
jmp elihw_5
whileS_5:
ldx $0000
phx
ldx $0002
lda $08, x
pha
ldx #$08
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare42
lda #$0000
jmp erapmoc42
compare42:
lda #$0001
erapmoc42:
stx $0000
pha
pla
cmp #$0000
beq elseb_7
jmp ifS_7
elseb_7:
jmp else_7
ifS_7:
if_7:
ldx #$00
phx
pla
ldy $0002
sta $08, y
jmp fi_7
else_7:
ldx $0002
lda $08, x
pha
plx
inx
phx
pla
ldy $0002
sta $08, y
fi_7:
plx
stx $0000
jmp while_5
elihw_5:
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
ldy $084e
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
jsr SetTiles
wai
while_6:
ldx $0002
lda $06, x
pha
pla
cmp #$0000
php
sep #$20
pla
and #$02
lsr a
tax
rep #$20
phx
pla
cmp #$0000
beq elihwb_6
jmp whileS_6
elihwb_6:
jmp elihw_6
whileS_6:
ldx $0000
phx
wai
lda Joy1Held
and #$0200
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
beq elseb_8
jmp ifS_8
elseb_8:
jmp else_8
ifS_8:
if_8:
ldx $0000
phx
ldx $0002
lda $08, x
pha
pla
ldy $0002
sta $0a, y
ldx $0002
lda $08, x
pha
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare43
lda #$0000
jmp erapmoc43
compare43:
lda #$0001
erapmoc43:
stx $0000
pha
pla
cmp #$0000
beq elseb_9
jmp ifS_9
elseb_9:
jmp else_9
ifS_9:
if_9:
ldx #$08
phx
pla
ldy $0002
sta $08, y
jmp fi_9
else_9:
ldx $0002
lda $08, x
pha
plx
dex
phx
pla
ldy $0002
sta $08, y
fi_9:
while_7:
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
lda TOP
clc
adc #$00
sta AR
lda TOP
clc
adc #$02
sta TOP
jsr method_possibleSpot
ldy AR
lda $0000, y
tax
tya
sec
sbc #$00
sta TOP
ply
sty AR
phx
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare44
lda #$0000
jmp erapmoc44
compare44:
lda #$0001
erapmoc44:
stx $0000
pha
pla
cmp #$0000
beq elihwb_7
jmp whileS_7
elihwb_7:
jmp elihw_7
whileS_7:
ldx $0002
lda $08, x
pha
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare45
lda #$0000
jmp erapmoc45
compare45:
lda #$0001
erapmoc45:
stx $0000
pha
pla
cmp #$0000
beq elseb_10
jmp ifS_10
elseb_10:
jmp else_10
ifS_10:
if_10:
ldx #$08
phx
pla
ldy $0002
sta $08, y
jmp fi_10
else_10:
ldx $0002
lda $08, x
pha
plx
dex
phx
pla
ldy $0002
sta $08, y
fi_10:
jmp while_7
elihw_7:
ldx AR
phx
ldx $0002
lda $0a, x
pha
ldx $0000
pla
sta $00, x
ldy $0826
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
ldy $084e
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
jsr SetTiles
wai
plx
stx $0000
jmp fi_8
else_8:
wai
lda Joy1Held
and #$0100
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
beq elseb_11
jmp ifS_11
elseb_11:
jmp else_11
ifS_11:
if_11:
ldx $0000
phx
ldx $0002
lda $08, x
pha
pla
ldy $0002
sta $0a, y
ldx $0002
lda $08, x
pha
ldx #$08
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare46
lda #$0000
jmp erapmoc46
compare46:
lda #$0001
erapmoc46:
stx $0000
pha
pla
cmp #$0000
beq elseb_12
jmp ifS_12
elseb_12:
jmp else_12
ifS_12:
if_12:
ldx #$00
phx
pla
ldy $0002
sta $08, y
jmp fi_12
else_12:
ldx $0002
lda $08, x
pha
plx
inx
phx
pla
ldy $0002
sta $08, y
fi_12:
while_8:
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
lda TOP
clc
adc #$00
sta AR
lda TOP
clc
adc #$02
sta TOP
jsr method_possibleSpot
ldy AR
lda $0000, y
tax
tya
sec
sbc #$00
sta TOP
ply
sty AR
phx
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare47
lda #$0000
jmp erapmoc47
compare47:
lda #$0001
erapmoc47:
stx $0000
pha
pla
cmp #$0000
beq elihwb_8
jmp whileS_8
elihwb_8:
jmp elihw_8
whileS_8:
ldx $0002
lda $08, x
pha
ldx #$08
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare48
lda #$0000
jmp erapmoc48
compare48:
lda #$0001
erapmoc48:
stx $0000
pha
pla
cmp #$0000
beq elseb_13
jmp ifS_13
elseb_13:
jmp else_13
ifS_13:
if_13:
ldx #$00
phx
pla
ldy $0002
sta $08, y
jmp fi_13
else_13:
ldx $0002
lda $08, x
pha
plx
inx
phx
pla
ldy $0002
sta $08, y
fi_13:
jmp while_8
elihw_8:
ldx AR
phx
ldx $0002
lda $0a, x
pha
ldx $0000
pla
sta $00, x
ldy $0826
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
ldy $084e
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
jsr SetTiles
wai
plx
stx $0000
jmp fi_11
else_11:
wai
lda Joy1Press
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
beq elseb_14
jmp ifS_14
elseb_14:
jmp else_14
ifS_14:
if_14:
ldx #$01
phx
pla
ldy $0002
sta $06, y
jmp fi_14
else_14:
fi_14:
fi_11:
fi_8:
plx
stx $0000
jmp while_6
elihw_6:
ldx $0002
lda $04, x
pha
ldx $0002
lda $08, x
pha
;asn val
plx
ply
lda $0000
pha
phy
phx
ldx #$0000
lda $0810, x
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
lda $04, x
pha
ldx #$01
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare49
lda #$0000
jmp erapmoc49
compare49:
lda #$0001
erapmoc49:
stx $0000
pha
pla
cmp #$0000
beq elseb_15
jmp ifS_15
elseb_15:
jmp else_15
ifS_15:
if_15:
ldx $0000
phx
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
ldy $0832
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
ldx #$02
phx
pla
ldy $0002
sta $04, y
plx
stx $0000
jmp fi_15
else_15:
ldx $0000
phx
ldx AR
phx
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $00, x
ldy $082e
phy
ldx $0000
pla
sta $02, x
ldx TOP
stx AR
lda TOP
clc
adc #$04
sta TOP
jsr method_drawAt
lda AR
sta TOP
ply
sty AR
ldx #$01
phx
pla
ldy $0002
sta $04, y
plx
stx $0000
fi_15:
ldx $0002
phx
ldx $0000
stx $0002
jsr method_getWinner
ldy AR
lda $0000, y
tax
tya
sta TOP
ply
sty AR
phx
pla
ldy $0002
sta $02, y
plx
stx $0000
jmp while_4
elihw_4:
ldx #$00
phx
pla
ldy $0002
sta $00, y
while_9:
ldx $0002
lda $00, x
pha
ldx #$20
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare4a
lda #$0001
jmp erapmoc4a
compare4a:
lda #$0000
erapmoc4a:
stx $0000
pha
pla
cmp #$0000
beq elihwb_9
jmp whileS_9
elihwb_9:
jmp elihw_9
whileS_9:
ldx $0000
phx
ldx #$00
phx
pla
ldx $0000
sta $0000, x
lda $0000
clc
adc #$02
sta $0000
while_10:
ldx $0002
lda $08, x
pha
ldx #$20
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
bpl compare4b
lda #$0001
jmp erapmoc4b
compare4b:
lda #$0000
erapmoc4b:
stx $0000
pha
pla
cmp #$0000
beq elihwb_10
jmp whileS_10
elihwb_10:
jmp elihw_10
whileS_10:
ldx $0000
phx
ldx AR
phx
ldx $0002
lda $00, x
pha
ldx $0000
pla
sta $00, x
ldx $0002
lda $08, x
pha
ldx $0000
pla
sta $02, x
ldx $0002
lda $02, x
pha
ldx $0000
pla
sta $04, x
ldx TOP
stx AR
lda TOP
clc
adc #$06
sta TOP
jsr method_draw
lda AR
sta TOP
ply
sty AR
ldx $0002
lda $08, x
pha
plx
inx
phx
pla
ldy $0002
sta $08, y
plx
stx $0000
jmp while_10
elihw_10:
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
jmp while_9
elihw_9:
jsr SetTiles
wai
ldx #$00
phx
pla
ldy $0002
sta $06, y
while_11:
ldx $0002
lda $06, x
pha
ldx #$00
phx
ply
pla
ldx $0000
sty $0000
cmp $0000
beq compare4c
lda #$0000
jmp erapmoc4c
compare4c:
lda #$0001
erapmoc4c:
stx $0000
pha
pla
cmp #$0000
beq elihwb_11
jmp whileS_11
elihwb_11:
jmp elihw_11
whileS_11:
ldx $0000
phx
wai
lda Joy1Press
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
beq elseb_16
jmp ifS_16
elseb_16:
jmp else_16
ifS_16:
if_16:
ldx #$01
phx
pla
ldy $0002
sta $06, y
jmp fi_16
else_16:
fi_16:
plx
stx $0000
jmp while_11
elihw_11:
plx
stx $0000
jmp while_0
elihw_0:
method_main_end:
rts
.ENDS
