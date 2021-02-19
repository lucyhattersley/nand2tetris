// Multiplies R0 and R1
// This code adds two numbers
(LOOP)    // line 3 link for loop
    // check R1 > 0. If R1 <=0 GOTO END  
    @R1   // A-R1
    D=M   // D -> M[A]

    @END  // Point to END
    D;JLE // Jump to end if D<=0

    // Get first value
    @R0   // A -> R0
    D=M   // D->M[A]

    // Add first value to sum (should be)
    @sum  // A -> R16
    D=D+M // D -> D+A

    // Store value
    @sum  // A -> R16
    M=D   // M[A] -> R16

    // Reduce R1 by 1
    @R1   // A -> R1
    D=A-1 // Stores val in D

    @R1   // A -> R1
    M=D   // Stores val in R1

    @LOOP // Points to loop
    0;JMP // Jumps to loop

(END)     // Infinite loop
    @END  // Points to END location
    0;JMP // Jumps to END location


