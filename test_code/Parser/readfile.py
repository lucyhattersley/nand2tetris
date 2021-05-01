import sys # file support
import os # for filename extension
import re # regex support

# open files
pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')

# instantiate lists
input = f.read()

# new clean code approach
comment_free = re.sub('(/\*([^*]|[\r\n]|(\*+([^*/]|[\r\n])))*\*+/)|(//.*)|[^\S\r\n]', '', input) # removes comments
lines = comment_free.splitlines() # splits into list
clean_code = [x for x in no_lines if x] # removes empty items / blank lines from list

# write list to file
for line in clean_code:
    o.write(f'{line}' + '\n') 

# close files
o.close()
f.close()

