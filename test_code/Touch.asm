// Put your code here

@i // 1 if key pressed; 0 if no key
M=0

(LOOP)
    @24576 // Keyboard register
    D=M
    @keyboard // 
    M=D

    @keyboard
    D=M
    @ELSE
    D;JGT
    @i
    M=0
    @LOOP
    0;JMP

(ELSE) 
    @i
    M=1

@LOOP // infinite loop
0;JMP