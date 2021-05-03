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
        else:
             return('NO MATCH')
        
# init object 
parser = Parser(sys.argv[1])

# print parser input
print(parser.input)

# test commandType note missing commands in output
while parser.hasMoreCommands():
    command = parser.advance()
    print('Current command is: ' + parser.command)

    print('Command type is: ' + parser.commandType(command))

    print('')

    parser.advance()