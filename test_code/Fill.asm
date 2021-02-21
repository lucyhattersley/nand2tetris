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
    @SCREEN // screen start
    D=A
    @pixel //  position on row. Starts at 16384
    M=D

    @i // Index loop 1 to 32 (number of words in row)
    M=1

(LOOP) // draws all words

    @i // Index 
    D=M

    @8192  // Words in a screen
    D=D-A

    @END
    D;JGT
    
    @pixel
    A=M  // Get pixel position
    M=-1

    @pixel // increase pixel position
    M=M+1

    @i // increase index by 1
    M=M+1

    @LOOP
    0;JMP


(END)
    @END // infinite loop
    0;JMP
