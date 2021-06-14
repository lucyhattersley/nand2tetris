import parser
import sys
import os
import re
from copy import deepcopy

class Assembler:
    def __init__(self, argv=sys.argv[1]):
        # split into filename and extenstion 
        self.pre, self.ext = os.path.splitext(argv)
        self.f = open(self.pre + self.ext, 'r') 

        # create input stream
        self.input = self.f.read()

        # clean code: remove blank lines and split into list 
        comment_free = re.sub('(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)|[^\S\r\n]', '', self.input) # removes comments
        lines = comment_free.splitlines() # splits into list
        self.input = [x for x in lines if x] # removes empty items / blank lines from list

        # close file
        self.f.close()

        # instantiate objects
        self.parser = parser.Parser(self.input)
        self.code = parser.Code()

        # TODO move to symbolTable module
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
         # Parse input stream
         # We loop through input stream and add L_COMMANDS and binary index to Symbol Table
        varAddr = 16
        for command in self.input:
            if self.parser.commandType(command) == 'L_COMMAND':
                loc = "{0:016b}".format(self.input.index(command)) # convert index position to binary location
                self.symbol_table[command] = loc
                self.input.remove(command) # Symbol Table location now points to next command in ASM (loop point)
            elif self.parser.commandType(command) == 'A_COMMMAND' and command[1].isalpha(): # Command is variable reference
                loc = "{0:016b}".format(varAddr)
                self.symbol_table[command] = loc
                varAddr += 1

    def parse(self, line):
        """
        Parses an ASM command and outputs a corrosponding HACK file
        Input: ASM command (str)
        Returns: 16 digit binary (Hack) command (str)
        Note: code is not error checking. Assumes all ASM commands are correct 
        """

        if self.parser.commandType(line) == 'A_COMMAND':
            if line in self.symbol_table: # first we check against sybol table
                return self.symbol_table[line]
        elif self.parser.commandType(line) == 'C_COMMAND':
            ins = '111'

            self.parser.setCommand(line)
            mnem = self.parser.comp()
            comp = self.code.comp(mnem)

            mnem = self.parser.dest()
            dest = self.code.dest(mnem)

            mnem = self.parser.comp()
            try:
                jump = self.code.jump(mnem)
            except:
                jump = '000'

            return ins + comp + dest + jump

    # def parse_file(self):
    #     self.f = open(self.pre + '.hack', 'w')

    #     self.symbol_parser = deepcopy(self.parser) # copy the parser

    #     # first pass to create symbol table
    #     symbol_val = 16 # symbol values start at RAM address 16 (0x0010)
    #     while self.symbol_parser.hasMoreCommands():
    #         self.symbol_parser.advance()
    #         hack_line = self.parse(self.symbol_parser.getCommand()) 
    #         if self.symbol_parser.commandType(hack_line) == 'L_COMMAND' and hack_line[0] == '@':
    #             break
    #         if self.symbol_parser.commandType(hack_line) == 'L_COMMAND':
    #             # convert sybol to binary
    #             self.symbol_table[hack_line] = "{0:016b}".format(int(symbol_val))
    #             symbol_val+=1 # write next symbol to next mem address

    #     # second pass to write hack file
    #     while self.parser.hasMoreCommands():
    #         self.parser.advance()
    #         if self.parser.commandType(self.parser.getCommand()) == 'L_COMMAND':
    #             self.f.write(self.symbol_table[self.parser.getCommand()])
    #         # Add support for L_Command reference here
    #         else:
    #             hack_line = self.parse(self.parser.getCommand()) 
    #             self.f.write(hack_line + '\n')
        
    #     self.f.close()
    

assembler = Assembler(sys.argv[1])
# assembler.parse_file()