import sys # file support
import os # for filename extension
import re # regex support

pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')
input = f.readlines()
output = []

for line in input:
    if re.match(r'^\s$', line): # skip blank line (has only \t\b\r and whitespace)
        continue 
    if re.match('//', line): # skip if comment
        continue
    else:
        output.append(line)

for item in output:
    o.write(f"{item}") 
o.close()
f.close()
    


