import sys # file support
import os # for filename extension
import re # regex support

class Parser:
    def __init__(self, argv):
        self.command = ''

        # open file
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
        # comp hash table
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

# MOVE TO TEST
# parser = Parser(sys.argv[1])

# # print parser input
# print(parser.input)

# # test commandType note missing commands in output
# while parser.hasMoreCommands():
#     command = parser.advance()
#     print('Current command is: ' + parser.command)

#     print('Command type is: ' + parser.commandType(command))

#     print('Command type symbol is: ' + parser.symbol())

#     print('Command dest symbol is: ' + parser.dest())

#     print('Command comp symbol is: ' + parser.comp())

#     print('Command jump symbol is: ' + parser.jump())

#     print('')
