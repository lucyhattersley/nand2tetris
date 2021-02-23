// This file is part of www.nand2tetris.org
// and the book "The Elements of Computing Systems"
// by Nisan and Schocken, MIT Press.
// File name: projects/04/Fill.asm

// Runs an infinite loop that listens to the keyboard input.
// When a key is pressed (any key), the program blackens the screen,
// i.e. writes "black" in every pixel;
// the screen should remain fully black as long as the key is pressed. 
// When no key is pressed, the program clears the screen, i.e. writes
// "white" in every pixel;
// the screen should remain fully clear as long as no key is pressed.

// Put your code here.
(BEGIN)
    @SCREEN // screen start
    D=A

    @pixel //  position on row. Starts at 16384
    M=D // 

(LOOP) // draws all words white or black

    @pixel  
    D=M
    @24575  // End of register
    D=D-A
    @BEGIN // Jump to start if screen full
    D;JGE

    @pixel // increase pixel position
    M=M+1

    @24576 // Keyboard register
    D=M
    @FILL
    D;JGT // Jump at end
    @CLEAR
    D;JEQ

    @BEGIN // Failsafe
    0;JMP

(FILL)
    @pixel
    A=M
    M=-1 // Fill screen
    @LOOP
    0;JMP

(CLEAR)
    @pixel
    A=M
    M=0 // Clear screen
    @LOOP
    0;JMP