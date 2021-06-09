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

Set up the input from the .asm file
-----------------------------------
Get the filname from the command line (ARGV[1])
Split the filename into pre, ext parts (so we can save pre and the .hack extension)
Open the filname as input stream 
Clean the stream to remove blank lines and split into list
Go through list to find L Commands
Add each L Command to symbol table with a variable number (starting at 16 0X0010000)
Close file

Go through file line by line

while there are more commands
Get next command
If L command (loop)
    return location of L command
if A command
    If R command return 

