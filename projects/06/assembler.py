import parser
import sys
import os
import re

class Assembler:
    def __init__(self, argv=sys.argv[1]):
        # split into filename and extenstion 
        self.pre, ext = os.path.splitext(argv)
        f = open(self.pre + ext, 'r') 

        # create input stream
        self.input = f.read()

        # clean code: remove blank lines and split into list 
        comment_free = re.sub('(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)|[^\S\r\n]', '', self.input) # removes comments
        lines = comment_free.splitlines() # splits into list
        self.input = [x for x in lines if x] # removes empty items / blank lines from list

        # close file
        f.close()

        # instantiate objects
        self.parser = parser.Parser(self.input)
        self.code = parser.Code()
        self.symbol_table = parser.SymbolTable()

        #  First-pass through input stream
        # We loop through input stream and add L_COMMANDS and binary index to Symbol Table
        input_dupe = [] 
        varAddr = 16 # Variables are assigned from memory address 16

        #TODO: Replace command with self.command

        # First pass through input file. Remove L_COMMANDS and add Variables to symbol_table
        while self.parser.hasMoreCommands():
            self.parser.advance()
            command = self.parser.getCommand()

            if self.parser.commandType(command) == 'L_COMMAND':
                address = "{0:016b}".format(len(input_dupe)) # convert index position to binary location
                self.symbol_table.addEntry(command[1:-1], address) # note, first and last char '(' and ')' sliced from command

            elif self.parser.commandType(command) == ('A_COMMAND') and self.symbol_table.contains(command[1:]):
                input_dupe.append(command)

            elif self.parser.commandType(command) == 'A_COMMAND' and command[1].isalpha(): # Command is variable reference
                loc = "{0:016b}".format(varAddr)
                self.symbol_table.addEntry(command[1:], loc)
                varAddr += 1
                input_dupe.append(command)
            else:
                input_dupe.append(command)

        self.parser.input = input_dupe.copy() # replace original list with duplicate

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

    #TODO: Replace command with self.command
    def parse_file(self):
        f = open(self.pre + '.hack', 'w') # create output file

        while self.parser.hasMoreCommands():
            command = self.parser.advance() # pops first item to parsers current command
            hack_line = self.parse(command) # parse it to hack command
            f.write(hack_line + '\n') # write to file
        f.close() # Close output file
    
assembler = Assembler(sys.argv[1])
assembler.parse_file()
