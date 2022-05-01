// Set SP to 256 (for setup)
@256
D=A
@SP
M=D

// push constant 17
@17
D=A
@SP
A=M
M=D
@SP
M=M+1

// push constant 17
@18
D=A
@SP
A=M
M=D
@SP
M=M+1

// EQ Implementation
@SP
AM=M-1
D=M
// D = M(--SP)
A=A-1
// A = SP - 2
D=M-D
M=-1 // sets to -1 first

@EQ_labelcount
D;JEQ // jumps if equal (remains -1)
@SP
A=M-1
M=0 // otherwise sets to 0
(EQ_labelcount)