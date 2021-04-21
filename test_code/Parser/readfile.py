f = open('./Add.asm')
o = open('./Add.hack', 'w')

input = f.readlines()
output = []

for line in input:
    if not line.strip().startswith(('//')): # gets rid of comments and blank lines
        o.write("%s" % line)

o.close()
f.close()

