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
6. Go through list to find L Commands
7. Add each L Command to the symbol table:
    1. SYMBOL = line number IE: if (LOOP) is on line 16  
    (LOOP) = 0000 0000 0000 1000
    2. Remove SYMBOL from input stream array
8. Close file

Translate the stream to binary commands
---------------------------------------

9. while there are more commands
10. Get next command
11. if A_COMMAND (IE: @) command  
    If: Check if command is in symbol table
    Else: Parse binary of address
12. If C_COMMAND:
    Parse C_COMMAND
13. Write to file
14. Close file
    

    