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
        self.symbol_table = parser.SymbolTable()

        #  Parse input stream
        # We loop through input stream and add L_COMMANDS and binary index to Symbol Table
        varAddr = 16

        # Iter through list and remove items: see https://stackoverflow.com/questions/1207406/how-to-remove-items-from-a-list-while-iterating
        # Todo: Work through logic here
        def determine(command):
            if self.parser.commandType(command) == 'L_COMMAND':
                address = "{0:016b}".format(self.input.index(command)) # convert index position to binary location
                self.symbol_table.addEntry(command[1:-1], address) # note, first and last char '(' and ')' sliced from command
                return False

            elif self.parser.commandType(command) == 'A_COMMMAND' and command[1].isalpha(): # Command is variable reference
                loc = "{0:016b}".format(varAddr)
                self.symbolTable.addEntry(command, address)
                varAddr += 1
            
            return True
        
        self.input[:] = [command for command in self.input if not determine(command)]

    def parse(self, line):
        """
        Parses an ASM command and outputs a corrosponding HACK file
        Input: ASM command (str)
        Returns: 16 digit binary (Hack) command (str)
        Note: code is not error checking. Assumes all ASM commands are correct 
        """

        if self.parser.commandType(line) == 'A_COMMAND':
            if self.symbol_table.contains(line[1:]):
                return self.symbol_table.getAddress(line[1:])
            else:
                return "{0:016b}".format(int(line[1:]))
                
        elif self.parser.commandType(line) == 'C_COMMAND':
            ins = '111'

            self.parser.setCommand(line)
            mnem = self.parser.comp()
            comp = self.code.comp(mnem)

            mnem = self.parser.dest()
            dest = self.code.dest(mnem)

            mnem = self.parser.jump()
            try:
                jump = self.code.jump(mnem)
            except:
                jump = '000'

            return ins + comp + dest + jump

    def parse_file(self):
        self.f = open(self.pre + '.hack', 'w') # create output file

        while self.parser.hasMoreCommands():
            self.parser.advance() # pops first item to parsers current command
            command = self.parser.getCommand() # lets get a copy
            hack_line = self.parse(command) # parse it to hack command
            self.f.write(hack_line + '\n') # write to file
        self.f.close() # Close output file
    
assembler = Assembler(sys.argv[1])
assembler.parse_file()
