import sys # To get file name
import os # for filename extension

pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')
input = f.readlines()

for line in input:
    if not line.strip().startswith(('//')): # gets rid of comments and blank lines
        o.write("%s" % line)

o.close()
f.close()
    


