/*
First test should translate 

From this:
push constant 7
push constant 8
add

To this:
@7
D=A
@8
D=D+A
@0
M=D
*/

// import java.util.*;
// import java.io.*;

public class VMEmulator {
    public static void main(String[] args) {
        System.out.println("VMEmulator running");
    }
}
