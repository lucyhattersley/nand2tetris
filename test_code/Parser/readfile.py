import sys # file support
import os # for filename extension
import re # regex support

pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')
input = f.readlines()

for line in input:
    if re.match(r'^\s$', line): # skip blank line (has only \t\b\r and whitespace)
        continue 
    if re.match('//', line): # skip if comment
        continue
    else:
        o.write("%s" % line) 

o.close()
f.close()
    


