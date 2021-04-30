import sys # file support
import os # for filename extension
import re # regex support

pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')
input = f.readlines()
output = []
clean_code = []

# first we remove blank lines and comments
for line in input:
    if re.match(r'^\s$', line): # skip blank line (has only \t\b\r and whitespace)
        continue 
    if re.match('//', line): # skip if comment
        continue
    else:
        clean_code.append(line)

# iter over clean code to find A and C instructions
for line in clean_code:
    if re.match("@", line): # line is A instruction
        binary = "{0:016b}".format(int(line[1:])) # convert digit to 16-bit binary number
        output.append(binary + '\n')

for item in output:
    o.write(f"{item}") 
o.close()
f.close()
    


