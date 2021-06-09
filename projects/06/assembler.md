ASSEMBLER
=========

Outline
Parses .asm files and translates ASM commands to machine code. Saves output to Hack file.
Input: .asm file
Output: hack

Notes: 
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
    