// Multiplies R0 and R1
// This code adds two numbers
(LOOP)    // line 3 link for loop
    @R0   // A -> R0
    D=M   // D->M[A]

    @R1   // A -> R1
    D=D+M // D -> D+A

    @R2  // A -> M[A]
    M=D 

