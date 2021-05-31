import parser
import sys

class Assembler:
    def __init__(self):
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
