import sys # file support
import os # for filename extension
import re # regex support

class Parser:
    def __init__(self, argv):
        # open files
        pre, ext = os.path.splitext(argv)
        f = open(pre + ext, 'r')

        # instantiate lists
        self.input = f.read()

        # new clean code approach
        comment_free = re.sub('(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)|[^\S\r\n]', '', self.input) # removes comments
        lines = comment_free.splitlines() # splits into list
        self.input = [x for x in lines if x] # removes empty items / blank lines from list

        # close files
        f.close()
    
    def hasMoreCommands(self):
        return(not not self.input)

# init object 
parser = Parser(sys.argv[1])

# print parser input
print(parser.input)

# has more commands. should return True
print(parser.hasMoreCommands())