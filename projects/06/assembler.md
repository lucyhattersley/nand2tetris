ASSEMBLER
=========

Outline
-------
Parses .asm files and translates ASM commands to machine code. Saves output to Hack file.
Notes: The assembler must perform two passes of the input. The first to build a symbolic table for L_COMMANDS. The second to parse the asm commands and insert the L_COMMAND references from the symbolic table. The advance method pops the commands from the list so input must be rebuilt or duplicated.

Input space
-----------
Input: .asm file
Output: hack

Example
-------
@R0              0000000000000000
D=M              1111110000010000
@R1              0000000000000001
D=D-M            1111010011010000 
@OUTPUT_FIRST    0000000000001010
D;JGT            1110001100000001
@R1i             0000000000000001
D=M              1111110000010000
@OUTPUT_D        0000000000001100
0;JMP            1110101010000111
(OUTPUT_FIRST)   
@R0              0000000000000000
D=M              1111110000010000
(OUTPUT_D)
@R2              0000000000000010
M=D              1110001100001000
(INFINITE_LOOP)
@INFINITE_LOOP   0000000000001110
0;JMP            1110101010000111

Dependencies 
------------
Parser module methods:
    setCommand
    getCommand
    hasMoreCommands
    Advance
    CommandType
    symbol
    dest
    comp
    jump

Code module methods:
    dest
    comp
    jump

Plan
==============

import parser, code, os, re

Set up the input stream
-----------------------
1. Get the filname from the command line (ARGV[1])
2. Split the filename into pre, ext parts (so we can save pre and the .hack extension)
3. Open the filname as input stream 
4. Clean the stream to remove blank lines and split into list  

Create the symbol table
-----------------------
5. Create symbol table with Pre defined lables and RAM reference addresses 

Parse input stream
------------------
6. Create duplicate input stream
   Set var address to 16

    1. While there are items in duplication stream
        pop next item
    1. If L Command 
        Add to the symbol table:
        SYMBOL = duplicate stream index number IE: if (LOOP) is on line 16  
        (LOOP) = 0000 0000 0000 1000
    2.  If Variable symbol (these are @ followed by an alphanumerical char)
            Map variable to memory location starting at RAM 16 (0x0010)
            Increase var address by 1
            Add item to duplicate input stream
    3.  Add all other items to duplicate input stream (A_commands and C_Commands)

    4.  Replace original input stream with duplicate input stream


Translate the stream to binary commands
---------------------------------------

9. while there are more commands
10. Get next command
11. if A_COMMAND (IE: @) command  
    If: Check if command is in symbol table
        return dict element
    Else: Parse binary of address
12. If C_COMMAND:
    Parse C_COMMAND
13. Write to file
14. Close file
    

    