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