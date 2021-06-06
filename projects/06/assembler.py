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
        self.symbol_table = {} # create dict for symbols

    def parse(self, line):
        # input: expects ASM command
        # returns a 16 digit Hack command

        # TODO Handle L_CCOMMAND here
        if self.parser.commandType(line) == 'L_COMMAND':
           return "{0:016b}".format(int(line[1:])) # convert digit to 16-bit binary number

        elif self.parser.commandType(line) == 'A_COMMAND':
            # handle R commands
            if line[:2] == '@R':
               return "{0:016b}".format(int(line[2:])) # convert digit to 16-bit binary number
            elif line[0] =='@': # A_COMMAND is symbol reference
                return self.symbol_table[line] # TODO: this is out of scope
            else: # command is regular A variable
                return "{0:016b}".format(int(line[1:])) # convert digit to 16-bit binary number
           
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

    def parse_file(self):
        self.f = open(self.pre + '.hack', 'w')

        self.symbol_parser = deepcopy(self.parser) # copy the parser

        # first pass to create symbol table
        symbol_val = 16 # symbol values start at RAM address 16 (0x0010)
        while self.symbol_parser.hasMoreCommands():
            self.symbol_parser.advance()
            hack_line = self.parse(self.symbol_parser.getCommand()) 
            if self.symbol_parser.commandType(self.symbol_parser.getCommand()) == 'L_COMMAND':
                # convert sybol to binary
                symbol_table[self.symbol_parser.getCommand] = "{0:016b}".format(int(symbol_val))
                symbol_val+=1 # write next symbol to next mem address

        # second pass to write hack file
        while self.parser.hasMoreCommands():
            self.parser.advance()
            if self.parser.commandType(self.parser.getCommand()) == 'L_COMMAND':
                self.f.write(symbol_table[self.parser.getCommand()])
            # Add support for L_Command reference here
            else:
                hack_line = self.parse(self.parser.getCommand()) 
                self.f.write(hack_line + '\n')
        
        self.f.close()

assembler = Assembler(sys.argv[1])