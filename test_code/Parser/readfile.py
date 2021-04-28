#!/usr/bin/python3

import sys # To get file name

try: 
    filename = sys.argv[1]
    f = open(filename, 'r')
    o = open('output.hack', 'w')

except:
    print("No file specified")

input = f.readlines()
output = []

for line in input:
    if not line.strip().startswith(('//')): # gets rid of comments and blank lines
        o.write("%s" % line)

o.close()
f.close()