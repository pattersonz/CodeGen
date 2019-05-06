.include "header.inc"
.include "snesINIT.asm"

	;====================
	;TileSet
	;====================
	.BANK 1
	.ORG 0
	.SECTION "TileData"
		.include "tiles.inc"
	.ENDS

;============================================================================
;LoadPalette - Macro that loads palette information into CGRAM
;----------------------------------------------------------------------------
; In: SRC_ADDR -- 24 bit address of source data,
;     START -- Color # to start on,
;     SIZE -- # of COLORS to copy
;----------------------------------------------------------------------------
; Out: None
;----------------------------------------------------------------------------
; Modifies: A,X
; Requires: mem/A = 8 bit, X/Y = 16 bit
;----------------------------------------------------------------------------
	.MACRO LoadPalette
		lda #\2
		sta $2121       ; Start at START color
		lda #:\1        ; Using : before the parameter gets its bank.
		ldx #\1         ; Not using : gets the offset address.
		ldy #(\3 * 2)   ; 2 bytes for every color
		jsr DMAPalette
	.ENDM

;============================================================================
; LoadBlockToVRAM -- Macro that simplifies calling LoadVRAM to copy data to VRAM
;----------------------------------------------------------------------------
; In: SRC_ADDR -- 24 bit address of source data
;     DEST -- VRAM address to write to (WORD address!!)
;     SIZE -- number of BYTEs to copy
;----------------------------------------------------------------------------

; Out: None
;----------------------------------------------------------------------------
; Modifies: A, X, Y
;----------------------------------------------------------------------------

;LoadBlockToVRAM SRC_ADDRESS, DEST, SIZE
;   requires:  mem/A = 8 bit, X/Y = 16 bit
.MACRO LoadBlockToVRAM
    lda #$80
    sta $2115       ; Set VRAM transfer mode to word-access, increment by 1

    ldx #\2         ; DEST
    stx $2116       ; $2116: Word address for accessing VRAM.
    lda #:\1        ; SRCBANK
    ldx #\1         ; SRCOFFSET
    ldy #\3         ; SIZE
    jsr LoadVRAM
.ENDM


.enum $0000
TOP		dw
AR 		dw
Joy1Raw 	dw
Joy2Raw		dw
Joy1Press	dw
Joy2Press	dw
Joy1Held	dw
Joy2Held	dw
SCREEN		dw
.ende
	.BANK 0 SLOT 0
	.ORG 0
	.SECTION "MainCode"
	
Start:
	
	InitSNES

	LoadPalette Pallette, 0, 4

	LoadBlockToVRAM DataSet, $0000, $0170

	lda #$80
	sta $2115
	ldx #$0400
	stx $2116

    ; Setup Video modes and other stuff, then turn on the screen
	jsr SetupVideo

	stz $4016
    
	 
   	  lda #$81
    	  sta $4200   ; Enable NMI and auto-joypad read	

	jmp main

;============================================================================
VBlank:
	phx
	phy
	pha

    lda $4210       ; Clear NMI flag
    jsr Joypad
    rep #$30

    	pla
	ply
	plx
    rti
;============================================================================

Joypad:	
    lda $4212           ; auto-read joypad status
    and #$01            ; 
    bne Joypad          ; read is done when 0

    rep #$30            ; A/X/Y - 16 bit
	
    ; Player 1
    ldx Joy1Raw         ; load log of last frame's RAW read of $4218
                        ; the log will be 0 the first time read of course..
    lda $4218           ; Read current frame's RAW joypad data
    sta Joy1Raw         ; save it for next frame.. (last frame log is still in X)
    txa                 ; transfer last frame input from X -> A (it's still in X)
    eor Joy1Raw         ; Xor last frame input with current frame input
                        ; shows the changes in input
                        ; buttons just pressed or just released become set.
                        ; Held or unactive buttons are 0
    and Joy1Raw         ; AND changes to current frame's input.
                        ; this ends up leaving you with the only the buttons that
                        ; are pressed.. It's MAGIC!
    sta Joy1Press       ; Store just pressed buttons
    txa                 ; Transfer last frame input from X -> A again
    and Joy1Raw	        ; Find buttons that are still pressed (held)
    sta Joy1Held        ; by storing only buttons that are pressed both frames
    
    ; Player 2          ; Repeat :)
    ldx Joy2Raw
    lda $421A           ; Read Joypad2 Regs
    sta Joy2Raw
    txa
    eor Joy2Raw         ; Find  just triggered buttons
    and Joy2Raw
    sta Joy2Press
    txa
    and Joy2Raw         ; Find buttons that are still pressed (held)
    sta Joy2Held
    
    ; Joypads standard (ie not a mouse or superscope..) and connected?
    sep #$20
    ldx #$0000      ; we'll clear recorded input if pad is invalid
    
    lda $4016       ; Pad 1 - now we read this (after we stored a 0 to it earlier)
    bne _check2     ; $4016 returns 0 if not connected, 1 if connected
    stx Joy1Raw     ; otherwise clear all recorded input.. it's not valid..
    stx Joy1Press
    stx Joy1Held

_check2:    
    lda $4017           ; Pad 2
    bne _done           ; 0=not connected,
    stx Joy2Raw
    stx Joy2Press
    stx Joy2Held

_done:
    RTS

;============================================================================
; SetupVideo -- Sets up the video mode and tile-related registers
;----------------------------------------------------------------------------
; In: None
;----------------------------------------------------------------------------
; Out: None
;----------------------------------------------------------------------------
SetupVideo:
    php

    lda #$00
    sta $2105           ; Set Video mode 0, 8x8 tiles, 4 color BG1/BG2/BG3/BG4

    lda #$04            ; Set BG1's Tile Map offset to $0400 (Word address)
    sta $2107           ; And the Tile Map size to 32x32

    stz $210B           ; Set BG1's Character VRAM offset to $0000 (word address)

    lda #$01            ; Enable BG1
    sta $212C

    lda #$FF
    sta $210E
    sta $210E

    lda #$0F
    sta $2100           ; Turn on screen, full Brightness

    plp
    rts
;============================================================================

;============================================================================
; LoadVRAM -- Load data into VRAM
;----------------------------------------------------------------------------
; In: A:X  -- points to the data
;     Y     -- Number of bytes to copy (0 to 65535)  (assumes 16-bit index)
;----------------------------------------------------------------------------
; Out: None
;----------------------------------------------------------------------------
; Modifies: none
;----------------------------------------------------------------------------
; Notes:  Assumes VRAM address has been previously set!!
;----------------------------------------------------------------------------
LoadVRAM:
    php         ; Preserve Registers

    stx $4302   ; Store Data offset into DMA source offset
    sta $4304   ; Store data Bank into DMA source bank
    sty $4305   ; Store size of data block

    lda #$01
    sta $4300   ; Set DMA mode (word, normal increment)
    lda #$18    ; Set the destination register (VRAM write register)
    sta $4301
    lda #$01    ; Initiate DMA transfer (channel 1)
    sta $420B

    plp         ; restore registers
    rts         ; return
;============================================================================

;============================================================================
; DMAPalette -- Load entire palette using DMA
;----------------------------------------------------------------------------
; In: A:X  -- points to the data
;      Y   -- Size of data
;----------------------------------------------------------------------------
; Out: None
;----------------------------------------------------------------------------
; Modifies: none
;----------------------------------------------------------------------------
DMAPalette:
    php         ; Preserve Registers

    stx $4302   ; Store data offset into DMA source offset
    sta $4304   ; Store data bank into DMA source bank
    sty $4305   ; Store size of data block

    stz $4300  ; Set DMA Mode (byte, normal increment)
    lda #$22    ; Set destination register ($2122 - CGRAM Write)
    sta $4301
    lda #$01    ; Initiate DMA transfer
    sta $420B

    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; Multiply -- Multiply two numbers together. their numbers are assumed to
; be small enough to not flip into negatie. numbers are changed to positive
; to be multiplied. The final product is then changed if needed.
;----------------------------------------------------------------------------
; In: X   -- First number
;     Y   -- Second number
;----------------------------------------------------------------------------
; Out: X --product of X * Y
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
Multiply:
    php         ; Preserve Registers
    pha
    sei		;since registers above $2 are used make sure they aren't edited
    rep #$30
    		;store top, ar, 
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    
    ;0004 will be whether or not the product is negative
    lda #$0000
    sta $0004
    txa
    bmi multiply_set1
    jmp multiply_skip
multiply_set1:
    lda #$0001
    sta $0004
    txa
    eor #$FFFF ;2s compliment negation
    inc a
    tax
multiply_skip:
    tya
    bmi multiply_set12
    jmp multiply_skip2
multiply_set12:
    lda #$0001
    eor $0004	;so this will flip the bit. double negative is positive result
    sta $0004
    tya
    eor #$FFFF ;2s compliment negation
    inc a
    tay
multiply_skip2:

		;store product in $0002
   lda #$0000
   sta $0002
		;goal here is to rotate the second value, every time it registers a 1, add the first value to the sum
		;every time you rotate the second value right, rotate the second value left stop when the second value is 0
multiply_startLoop:
    tya
    cmp #$0000
    beq multiply_finish
    lsr a ;shift last bit into carry, if set add x to $2
    bcc multiply_noAdd
    tay
    txa
    clc
    adc $0002
    sta $0002
    jmp multiply_primeNext
multiply_noAdd:
    tay
multiply_primeNext:
    txa
    asl a
    tax
    jmp multiply_startLoop
multiply_finish:
    lda $0004
    beq multiply_valPositive ;if the value pulled is 0, product remains, else negate
    lda $0002
    eor #$ffff
    inc a
    tax
    jmp multiply_end
multiply_valPositive:
    ldx $0002
multiply_end:
    pla
    sta $0004
    pla
    sta AR
    pla
    sta TOP

    cli
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; Divide -- divide one number by another. Numbers are changed to positive
; to be divided. The final quotient is then changed if needed.
;----------------------------------------------------------------------------
; In: X   -- First number
;     Y   -- Second number
;----------------------------------------------------------------------------
; Out: X --Quotient of X / Y
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
Divide:
    php         ; Preserve Registers
    pha
    sei		;since registers above $2 are used make sure they aren't edited
    rep #$30
    		;store top, ar, 
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    lda $0006
    pha
    
    ;0004 will be whether or not the product is negative
    lda #$0000
    sta $0004
    txa
    bmi Divide_set1
    jmp Divide_skip
Divide_set1:
    lda #$0001
    sta $0004
    txa
    eor #$FFFF ;2s compliment negation
    inc a
    tax
Divide_skip:
    tya
    bmi Divide_set12
    jmp Divide_skip2
Divide_set12:
    lda #$0001
    eor $0004	;so this will flip the bit. double negative is positive result
    sta $0004
    tya
    eor #$FFFF ;2s compliment negation
    inc a
    tay
Divide_skip2:
   ;store quotient in $0002
   lda #$0000
   sta $0002
   tya
   cmp #$0000
   bne Divide_start
   ldx #$FFFF
   jmp Divide_end
Divide_start:
	;get number of left shifts until y > x, store in $0000
	stz $0000
	stx $0002
Divide_findMaxMul:
	tya
	cmp $0002
	bmi Divide_continue
	jmp Divide_findQuo
Divide_continue: ;if here, then Y is still less than X
	lda $0000
	beq Divide_wasFirst
	asl a
	sta $0000
	tya
	asl a
	tay
	jmp Divide_findMaxMul
Divide_wasFirst:
	lda #$0001
	sta $0000
	jmp Divide_findMaxMul
Divide_findQuo:
lda #$0000
sta $0002
lda $0000
lsr a
sta $0000
tya
lsr a
tay
Divide_startLoop: ;subtract y from x add
 	lda $0000
	beq Divide_finish;value has been right shifted past original spot
	sty $0006
	txa
	cmp $0006
	bmi Divide_primeNext
Divide_addToQuo:	;add the current multiplier counter to the quo
	lda $0000	;subtract current y from x
	clc
	adc $0002
	sta $0002
	txa
	sec
	sbc $0006
	tax
Divide_primeNext:	;right shift both $0 and y
	lda $0000
	lsr a
	sta $0000
	lda $0006
	lsr a
	tay
	jmp Divide_startLoop
Divide_finish:
    lda $0004
    beq Divide_valPositive ;if the value pulled is 0, product remains, else negate
    lda $0002
    eor #$FFFF
    inc a
    tax
    jmp Divide_end
Divide_valPositive:
    ldx $0002
Divide_end:
    pla
    sta $0006
    pla
    sta $0004
    pla
    sta AR
    pla
    sta TOP

    cli
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; IntToFloat -- this value transforms an integer to floating pointer format
; since bools and chars are largely just ints, this works for those as well
;----------------------------------------------------------------------------
; In: X   -- Value to convert
;----------------------------------------------------------------------------
; Out: X -- Converted value
;----------------------------------------------------------------------------
; Modifies: X
;----------------------------------------------------------------------------
IntToFloat:
    php         ; Preserve Registers
    pha
    phy
    lda TOP
    pha
    lda AR
    pha

    ;$0 will be final value if int is negative, change to positive then set neg bit
    txa
    bmi I2F_wasNeg
    beq I2F_end
    ldy #$0000
    jmp I2F_signEnd
I2F_wasNeg:
	ldy #$8000
	eor #$FFFF
	tax ;copy new value to x for perserve
	inc a
I2F_signEnd:
	sty $0000
;find the number of shifts until 0, add this to exp, exp will be in y
      ldy #$0010
I2F_expStart:
	lsr a
	beq I2F_expEnd
	iny
	jmp I2F_expStart
I2F_expEnd:
	tya
	;10 bit mantissa, so shift y 10 times and add to $0 for total value
	.rept 10
	   asl a   
	.endr
	clc
	adc $0000
	sta $0000
	;initial value being 1 is implied. So make it so that first 1 is at bit 10(15,14,...1,0)
	;then mask bits 0-9
	txa
	ldy #$0400 ;2^10
	sty $0002
	cmp $0002
	beq I2F_end;in this case mantissa should be blank
	bmi I2F_increase
I2F_decrease:
	cmp #$0800
	bmi I2F_mantissaEnd
	lsr a
	jmp I2F_decrease
I2F_increase:
	asl a
	cmp #$0400
	bcs I2F_mantissaEnd
	beq I2F_mantissaEnd
	jmp I2F_increase
I2F_mantissaEnd:
	and #$03FF
	clc
	adc $0000
	tax
I2F_end:
    pla
    sta AR
    pla
    sta TOP
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================


;============================================================================
; FloatToInt -- this value transforms a float to integer format
; since bools and chars are largely just ints, this works for those as well
;----------------------------------------------------------------------------
; In: X   -- Value to convert
;----------------------------------------------------------------------------
; Out: X -- Converted value
;----------------------------------------------------------------------------
; Modifies: X
;----------------------------------------------------------------------------
FloatToInt:
    php         ; Preserve Registers
    pha
    phy
    sei
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    
    txa
    beq F2I_end
    ;get exp
    .rept 10
    lsr a
    .endr
    and #$001F	;mask out sign bit
    cmp #$0010
    bmi F2I_end0
    jmp F2I_transfer
F2I_end0:
    ldx #$0000
    jmp F2I_end
F2I_transfer:
    sta $0002 ;exp is now in $0002
    txa
    and #$03FF
    clc
    adc #$0400 ;now mantissa value
    sta $0000  ;mantissa is now in $0000
    ldy #$0000
    sty $0004
    ;while exp >= 16 grab val at #$0400, shift it to 1. shift current y and add that bit
F2I_loop:
	lda $0002
	cmp #$0010
	bmi F2I_loopEnd	; if exp is now < 16
	lda $0000
	and #$0400	
	.rept 10		;bit is now at spot 0
	     lsr a
	.endr
	tay
	lda $0004
	asl a
	sta $0004
	tya
	clc
	adc $0004
	sta $0004 ;value is taken, shifted then the new bit is appended
	lda $0002
	dec a
	sta $0002
	lda $0000
	asl a
	sta $0000 ;next bit is now in place
	jmp F2I_loop
F2I_loopEnd:
	;negate int value if sign bit
	txa
	and #$8000
	cmp #$0000
	beq F2I_posVal
	;negative
	lda $0004
	and #$FFFF
	inc a
	tax
	jmp F2I_end
F2I_posVal:
	ldx $0004
F2I_end:
    pla
    sta $0004
    pla
    sta AR
    pla

    sta TOP
    
    cli
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; FloatGtr -- compares if one float is greater than another
;----------------------------------------------------------------------------
; In: X  -- Value to compare
;   : Y  -- Value to compare to
;----------------------------------------------------------------------------
; Out: X -- True or False
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
FloatGtr:
    php         ; Preserve Registers
    pha
    phy
    lda TOP
    pha
    
    sty $0000
    txa
    cmp $0000
    beq FloatGtr_isFalse
    ;compare sign bits, if both +, continue, if mismatch, answer, if both -, swap x and y
    tya
    and #$8000
    sta $0000
    txa
    and #$8000
    cmp $0000
    beq FloatGtr_compare	;values are not equal
    cmp #$8000			;if negative
    beq FloatGtr_isFalse
    jmp FloatGtr_isTrue
FloatGtr_compare:
	cmp #$8000
	beq FloatGtr_swap
	jmp FloatGtr_comp
FloatGtr_swap:	;when the values are negative, the smallest magnitude(what we are comparing)
		;is the largest of the two. So switch them
	sty $0000
	txa
	tay
	ldx $0000
FloatGtr_comp:
	;with exception of sign bit, simply the largest value is the largest
	txa
	and #$7FFF
	tax
	tya
	and #$7FFF
	sta $0000
	txa
	cmp $0000
	bmi FloatGtr_isFalse
FloatGtr_isTrue:
	ldx #$0001
	jmp FloatGtr_end
FloatGtr_isFalse:
	ldx #$0000
FloatGtr_end:
    pla
    sta TOP
    
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; FloatAdd -- Adds one float to another
;----------------------------------------------------------------------------
; In: X  -- Value to add to
;   : Y  -- Value to add
;----------------------------------------------------------------------------
; Out: X -- sum of X and Y
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
FloatAdd:
    php         ; Preserve Registers
    pha
    phy
    sei
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    lda $0006
    pha
    
    ;get mantissa of x and y $4 $6
    txa
    and #$03FF ;mask mantissa
    clc
    adc #$0400 ;add the implied bit
    sta $0004
    tya
    and #$03FF
    clc
    adc #$0400
    sta $0006
    ;get exponent of x and y $0 $2
    txa
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0000
    tya
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0002
    cmp $0000
    beq FloatAdd_sameExp
    bmi FloatAdd_yIsLess
FloatAdd_xIsLess:
	lda $0000
	cmp $0002
	beq FloatAdd_sameExp
	inc $0000
	lda $0004
	lsr a
	sta $0004
	jmp FloatAdd_xIsLess
FloatAdd_yIsLess:
	lda $0002
	cmp $0000
	beq FloatAdd_sameExp
	inc $0002
	lda $0006
	lsr a
	sta $0006
	jmp FloatAdd_yIsLess
FloatAdd_sameExp:
	txa
	and #$8000
	beq FloatAdd_checkY
	lda $0004
	eor #$FFFF
	inc a
	sta $0004
FloatAdd_checkY:
	tya
	and #$8000
	beq FloatAdd_transMant
	lda $0006
	eor #$FFFF
	inc a
	sta $0006
FloatAdd_transMant:
	lda $0006
	clc
	adc $0004
	;since exp are the same $0 is now answer
	ldx #$0000
	stx $0000
	sta $0004 ;save sum in $4, which isnt needed now that sum has been found
	cmp #$0000
	bmi FloatAdd_setSignNeg
	beq FloatAdd_valIsZero
	jmp FloatAdd_signSet
FloatAdd_setSignNeg:
	lda #$8000
	sta $0000
	;flip mantissa
	lda $0004
	eor #$FFFF
	inc a
	sta $0004
	jmp FloatAdd_signSet
FloatAdd_valIsZero:
	ldx #$0000
	jmp FloatAdd_end
FloatAdd_signSet:
	ldy #$0400 ;2^10
	sty $0006
	cmp $0006
	beq FloatAdd_end;in this case mantissa should be blank
	bmi FloatAdd_increase
FloatAdd_decrease:
	cmp #$0800
	bmi FloatAdd_mantissaEnd
	lsr a
	ldx $0002
	inx
	stx $0002
	jmp FloatAdd_decrease
FloatAdd_increase:
	asl a
	ldx $0002
	dex
	stx $0002
	cmp #$0400
	bcs FloatAdd_mantissaEnd
	beq FloatAdd_mantissaEnd
	jmp FloatAdd_increase
FloatAdd_mantissaEnd:	
	and #$03FF ;mask mantissa
	clc
	adc $0000  ;make sure to include the negative sign
	sta $0000
	;include exp
	lda $0002
	.rept 10
	asl a		;shift to right position
	.endr
	clc
	adc $0000
	tax		
FloatAdd_end:

    pla
    sta $0006
    pla
    sta $0004
    pla
    sta AR
    pla
    sta TOP

    cli
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; FloatMult -- Multiplies one float by another
;----------------------------------------------------------------------------
; In: X  -- Value to multiply 
;   : Y  -- Value to multiply with
;----------------------------------------------------------------------------
; Out: X -- product of X and Y
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
FloatMult:
    php         ; Preserve Registers
    pha
    phy
    sei
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    lda $0006
    pha
        
    ;get mantissa of x and y $4 $6
    txa
    and #$03FF ;mask mantissa
    clc
    adc #$0400 ;add the implied bit
    sta $0004
    tya
    and #$03FF
    clc
    adc #$0400
    sta $0006
    ;get exponent of x and y $0 $2
    txa
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0000
    tya
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0002
    ;exp $0, $2 mant $4, $6
	txa
	and #$8000
	beq FloatMult_checkY
	lda $0004
	eor #$FFFF
	inc a
	sta $0004
FloatMult_checkY:
	tya
	and #$8000
	beq FloatMult_transMant
	lda $0006
	eor #$FFFF
	inc a
	sta $0006
FloatMult_transMant:
	phx
	phy
	;reduce bits in considered mantissas to 7 to prevent overflow
	lda $0004
	.rept 4
	lsr a
	.endr
	tax
	lda $0006
	.rept 4
	lsr a
	.endr
	tay
	jsr Multiply ; multiply values together
	txa
	ply
	plx
	cmp #$0000
	bmi FloatMult_makePos
	jmp FloatMult_shift
FloatMult_makePos:
	eor #$FFFF
	inc a
FloatMult_shift:
	ldy #$0400 ;2^10
	sty $0006
	cmp $0006
	beq FloatMult_end;in this case mantissa should be blank
	bmi FloatMult_increase
FloatMult_decrease:
	cmp #$0800
	bmi FloatMult_mantissaEnd
	lsr a
	inc $0002
	jmp FloatMult_decrease
FloatMult_increase:
	asl a
	dec $0002
	cmp #$0400
	bcs FloatMult_mantissaEnd
	beq FloatMult_mantissaEnd
	jmp FloatMult_increase
FloatMult_mantissaEnd:	
	and #$03FF ;mask mantissa
	sta $0004
	tya
	and #$8000
	sta $0006
	txa
	and #$8000
	eor $0006
	clc
	adc $0004  ;make sure to include the negative sign
	sta $0004
	;include exp
	lda $0002
	clc
	adc $0000
	sec
	sbc #$0012
	and #$001F
	.rept 10
	asl a		;shift to right position
	.endr
	clc
	adc $0004
	tax
FloatMult_end:
    pla
    sta $0006
    pla
    sta $0004
    pla
    sta AR
    pla
    sta TOP

    cli
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================

;============================================================================
; FloatDi -- Divides one float by another
;----------------------------------------------------------------------------
; In: X  -- Value to divide 
;   : Y  -- Value to divide by
;----------------------------------------------------------------------------
; Out: X -- quotient of X over Y
;----------------------------------------------------------------------------
; Modifies: X, Y
;----------------------------------------------------------------------------
FloatDiv:
    php         ; Preserve Registers
    pha
    phy
    sei
    lda TOP
    pha
    lda AR
    pha
    lda $0004
    pha
    lda $0006
    pha
        
    ;get mantissa of x and y $4 $6
    txa
    and #$03FF ;mask mantissa
    clc
    adc #$0400 ;add the implied bit
    sta $0004
    tya
    and #$03FF
    clc
    adc #$0400
    sta $0006
    ;get exponent of x and y $0 $2
    txa
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0000
    tya
    and #$7C00
    .rept 10
    lsr a
    .endr
    sta $0002
    ;exp $0, $2 mant $4, $6
	txa
	and #$8000
	beq FloatDiv_checkY
	lda $0004
	eor #$FFFF
	inc a
	sta $0004
FloatDiv_checkY:
	tya
	and #$8000
	beq FloatDiv_transMant
	lda $0006
	eor #$FFFF
	inc a
	sta $0006
FloatDiv_transMant:
	phx
	phy
	;reduce manipulate shifts for more accuracy
	lda $0004
	.rept 3
	asl a
	.endr
	tax
	lda $0006
	.rept 3
	lsr a
	.endr
	tay
	jsr Divide ; multiply values together
	txa
	ply
	plx
	cmp #$0000
	bmi FloatDiv_makePos
	jmp FloatDiv_shift
FloatDiv_makePos:
	eor #$FFFF
	inc a
FloatDiv_shift:
	ldy #$0400 ;2^10
	sty $0006
	cmp $0006
	beq FloatDiv_end;in this case mantissa should be blank
	bmi FloatDiv_increase
FloatDiv_decrease:
	cmp #$0800
	bmi FloatDiv_mantissaEnd
	lsr a
	inc $0000
	jmp FloatDiv_decrease
FloatDiv_increase:
	asl a
	dec $0000
	cmp #$0400
	bcs FloatDiv_mantissaEnd
	beq FloatDiv_mantissaEnd
	jmp FloatDiv_increase
FloatDiv_mantissaEnd:	
	and #$03FF ;mask mantissa
	sta $0004
	tya
	and #$8000
	sta $0006
	txa
	and #$8000
	eor $0006
	clc
	adc $0004  ;make sure to include the negative sign
	sta $0004
	;include exp
	lda $0002
	sec
	sbc #$0014
	sta $0002
	lda $0000
	sec
	sbc $0002
	and #$001F
	.rept 10
	asl a		;shift to right position
	.endr
	clc
	adc $0004
	tax
FloatDiv_end:
    pla
    sta $0006
    pla
    sta $0004
    pla
    sta AR
    pla
    sta TOP

    cli
    ply
    pla
    plp
    rts         ; return from subroutine

;============================================================================


;============================================================================
; SetTiles -- Sets all the tile values in the designated mem space to
; the screen
;----------------------------------------------------------------------------
; In: None
;----------------------------------------------------------------------------
; Out: None
;----------------------------------------------------------------------------
; Modifies: none
;----------------------------------------------------------------------------
SetTiles:
	php
	sei
	phx
	phy
	phd

	stz $2115       ; Setup VRAM
    	ldx #$0400
    	stx $2116       ; Set VRAM address

	rep #$20
	ldx #$0000
	.rept 32	;loop through all 32 rows...
	      .rept 32 	;and all 32 cols....
	      	    lda $10, x
		    sta $2118
		    inx
		    inx
	      .endr
	.endr
	pld
	ply
	plx
	cli
	plp
	rts