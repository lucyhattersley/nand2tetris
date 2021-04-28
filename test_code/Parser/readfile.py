import sys # file support
import os # for filename extension
import re # regex support

pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')
input = f.readlines()

for line in input:
    # if not line.strip().startswith(('//')): # gets rid of comments and blank lines
    if not re.match(r'^\s$', line): # (has only \t\b\r and whitespace)
        o.write("%s" % line) 

o.close()
f.close()
    


