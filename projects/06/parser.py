import sys # file support
import os # for filename extension
import re # regex support

class Parser:
    def __init__(self):
        self.command = ''

        # open file
        # needs moving to command line
        argv = 'add/Add.asm'
        pre, ext = os.path.splitext(argv)
        f = open(pre + ext, 'r')

        # instantiate lists
        self.input = f.read()

        # clean code 
        comment_free = re.sub('(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)|[^\S\r\n]', '', self.input) # removes comments
        lines = comment_free.splitlines() # splits into list
        self.input = [x for x in lines if x] # removes empty items / blank lines from list

        # close file
        f.close()

    def hasMoreCommands(self):
        return(not not self.input)

    def advance(self):
        if self.hasMoreCommands():
            self.command = self.input.pop(0)

    def commandType(self, command):
        if re.match("@", self.command): # line is A instruction
            return('A_COMMAND')
        elif re.search(r"=|;", self.command): # line is C instruction (contract states that all code is correct and lines are either A or C)
            return('C_COMMAND')
        elif re.match("\(", self.command): # line is L (LOOP) instruction
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