import parser
import sys
import os
import re

class Assembler:
    def __init__(self):
        # open file
        argv = sys.argv[1]
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

        self.parser = parser.Parser()
        self.code = parser.Code()

    def parse(self, line):
        # input: expects ASM command
        # returns a 16 digit Hack command
        if self.parser.commandType(line) == 'A_COMMAND':
           return "{0:016b}".format(int(line[1:])) # convert digit to 16-bit binary number
           
        if self.parser.commandType(line) == 'C_COMMAND':
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

assembler = Assembler()
