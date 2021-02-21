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
    M=D-1

    @i // Index loop counter
    M=0

(LOOP) // draws all words white or black

    @pixel // increase pixel position
    M=M+1

    @i // increase index by 1
    M=M+1

    // Checking keyboard
    @24576 // Set keyboard register to @keyboard 
    D=M
    @keyboard // 
    M=D

    // Reset to start if full
    @i // Index 
    D=M

    @8192  // Words in a screen
    D=D-A

    @BEGIN
    D;JGT

    @keyboard // Get keyboard value
    D=M
    
    @ELSE // if keyboard value 0
    D;JEQ

    @pixel
    A=M  // Get pixel position
    M=-1 // Fill screen
    @LOOP
    0;JMP

(ELSE)
    @pixel
    A=M  // Get pixel position
    M=0 // Clear screen
    @LOOP
    0;JMP