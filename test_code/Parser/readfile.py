with open('./Add.asm') as f:
    lines = f.readlines()
    

for line in lines:
    if not line.strip().startswith('//'): # gets rid of comments
       print(line)