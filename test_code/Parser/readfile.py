import sys # file support
import os # for filename extension
import re # regex support

# open files
pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')

# instantiate lists
input = f.readlines()
output = []
clean_code = []

# new clean code approach
clean_code = re.sub('(^\s$| |/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/|//.*\n?)', '', input)

for item in output:
    o.write(f"{item}" + "\n") 
o.close()
f.close()

