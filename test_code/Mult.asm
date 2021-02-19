// Multiplies R0 and R1
// This code adds two numbers
    @sum  // A -> R16
    M=0   // Set R16 to 0
(LOOP)    // line 3 link for loop
    // Get first value
    @R0   // A -> R0
    D=M   // D->M[A]

    // Add first value to second value
    @R1   // A -> R1
    D=D+M // D -> D+A

    // Store combined value
    @sum  // A -> R16
    M=D   // M[A] -> R16

    @LOOP // Points to loop
    0;JMP // Jumps to loop

(END)     // Infinite loop
    @LOOP  // Points to END location
    0;JMP // Jumps to END location


