import sys # file support
import os # for filename extension
import re # regex support

class Parser:
    def __init__(self, input):
        self.input = input

    #Setter and getter for test
    def setCommand(self, command):
        self.command = command

    def getCommand(self):
        return self.command

    # Routine's outlined in "The elements of computing systems"
    def hasMoreCommands(self):
        return(not not self.input)

    def advance(self):
        if self.hasMoreCommands():
            self.command = self.input.pop(0)

    def commandType(self, command):
        if re.match("@", command): # line is A instruction
            return('A_COMMAND')
        elif re.search(r"=|;", command): # line is C instruction (contract states that all code is correct and lines are either A or C)
            return('C_COMMAND')
        elif re.match("\(", command): # line is L (LOOP) instruction
            return('L_COMMAND')

    def symbol(self):
        if self.commandType(self.command) == 'A_COMMAND' or self.commandType(self.command) == 'L_COMMAND':
            # needs to return symbol or decimal of command get rid of @() (regex?)
            return re.sub('@|\(|\)', '', self.command)
        else: 
            return '' # failsafe. C_COMMAND has no symbol

    def dest(self):
        if self.commandType(self.command) == 'C_COMMAND' and '=' in self.command:
                return self.command.split('=')[0]
        else: # failsafe
            return ''

    def comp(self):
        if self.commandType(self.command) == 'C_COMMAND':
            if '=' in self.command and ';' in self.command:
                words = re.split('=|;', self.command)
                return str(words[1])
            elif '=' in self.command:
                words = re.split('=', self.command)
                return str(words[1])
            elif ';' in self.command:
                words = re.split(';', self.command)
                return str(words[0])
        else: # failsafe
            return ''

    def jump(self):
        if self.commandType(self.command) == 'C_COMMAND' and ';' in self.command:
                words = re.split(';', self.command)
                return str(words[1])
        else: # failsafe 
            return ''

class Code:
    def __init__(self):
        self.c_table = {
                "0"  : "0101010",
                "1"  : "0111111",
                "-1" : "0111010", 
                "D"  : "0001100", 
                "A"  : "0110000", 
                "M"  : "1110000", 
                "!D" : "0001101", 
                "!A" : "0110001", 
                "!M" : "1110001", 
                "-D" : "0001111", 
                "-A" : "0110011", 
                "-M" : "1110011", 
                "D+1": "0011111", 
                "A+1": "0110111", 
                "M+1": "1110111", 
                "D-1": "0001110", 
                "A-1": "0110010", 
                "M-1": "1110010", 
                "D+A": "0000010", 
                "D+M": "1000010", 
                "D-A": "0010011", 
                "D-M": "1010011", 
                "A-D": "0000111", 
                "M-D": "1000111", 
                "D&A": "0000000", 
                "D&M": "1000000", 
                "D|A": "0010101",
                "D|M": "1010101" 
        }

        # dest table
        self.d_table = {
            "null" : "000",
            "M"    : "001",
            "D"    : "010",
            "MD"   : "011",
            "A"    : "100",
            "AM"   : "101",
            "AD"   : "110",
            "AMD"  : "111"
        }

        self.j_table = {
            "null" : "000",
            "JGT"  : "001",
            "JEQ"  : "010",
            "JGE"  : "011",
            "JLT"  : "100",
            "JNE"  : "101",
            "JLE"  : "110",
            "JMP"  : "111"
        }

    def dest(self, mnemonic):
        return self.d_table[mnemonic]
    
    def comp(self, mnemonic):
        return self.c_table[mnemonic] 
    
    def jump(self, mnemonic):
        return self.j_table[mnemonic]

class SymbolTable:
    def __init__(self):
        #Create symbol table with predefined symbols
        self.symbol_table = {
        "SP":"0000000000000000",
        "LCL":"0000000000000001",
        "ARG":"0000000000000002",
        "THIS":"0000000000000003",
        "THAT":"0000000000000004",
        "R0":"0000000000000000",
        "R1":"0000000000000001",
        "R2":"0000000000000010",
        "R3":"0000000000000011",
        "R4":"0000000000000100",
        "R5":"0000000000000101",
        "R6":"0000000000000110",
        "R7":"0000000000000111",
        "R8":"0000000000001000",
        "R9":"0000000000001001",
        "R10":"0000000000001010",
        "R11":"0000000000001011",
        "R12":"0000000000001100",
        "R13":"0000000000001101",
        "R14":"0000000000001110",
        "R15":"0000000000001111"
        }
        
    def addEntry(symbol, address):
        self.symbol_table[symbol] = address

    def contains(symbol):
        return symbol in self.symbol_table

    def getAddress(symbol):
        return self.symbol_table[symbol]