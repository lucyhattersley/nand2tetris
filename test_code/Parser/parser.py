import sys # file support
import os # for filename extension
import re # regex support

class Parser:
    def __init__(self, argv):
        self.current_command = ''

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
            self.current_command = self.input.pop(0)

# init object 
parser = Parser(sys.argv[1])

# print parser input
print(parser.input)

# has more commands. should return True
print(parser.hasMoreCommands())

# get current command
command = parser.advance()
print('Current command is: ' + parser.current_command)