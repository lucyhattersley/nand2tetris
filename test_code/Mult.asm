// Multiplies R0 and R1
// This code adds two numbers
    @R1   // A -> R0
    D=M   // D -> M[A]

    @sum  // A -> R16
    M=D   // Set R16 to value at D

(LOOP)    // line 3 link for loop
    // Get first value
    @R0   // A -> R0
    D=M   // D->M[A]

    // Add first value to sum (should be)
    @sum   // A -> R16
    D=D+M // D -> D+A

    // Store combined value
    @sum  // A -> R16
    M=D   // M[A] -> R16

    @LOOP // Points to loop
    0;JMP // Jumps to loop

(END)     // Infinite loop
    @LOOP  // Points to END location
    0;JMP // Jumps to END location


