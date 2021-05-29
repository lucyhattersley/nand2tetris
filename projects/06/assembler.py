import parser
import sys

class Assembler:
    def __init__(self):
        self.parser = parser.Parser()
        
    def parse(self, line):
        # input: expects ASM command
        # returns a 16 digit Hack command
        self.parser.setCommand(line)
        return 

assembler = Assembler()