import parser
import sys
import os
import re

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

    def parse_file(self):
        self.f = open(self.pre + 'hack', 'w')

        while parser.hasMoreCommands():
            parser.advance()
            hack_line = self.parse(parser.getCommand()) 
            self.f.write(hack_line)
        
        self.f.close()
        
assembler = Assembler(sys.argv[1])