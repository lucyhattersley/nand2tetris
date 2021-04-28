#!/usr/bin/python3

import sys # To get file name

try: 
    print(sys.argv[1])
except:
    print("No file specified")

f = open('./Add.asm')
o = open('./Add.hack', 'w')

input = f.readlines()
output = []

for line in input:
    if not line.strip().startswith(('//')): # gets rid of comments and blank lines
        o.write("%s" % line)

o.close()
f.close()