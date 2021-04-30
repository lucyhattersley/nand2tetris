import sys # file support
import os # for filename extension
import re # regex support

# open files
pre, ext = os.path.splitext(sys.argv[1])
f = open(pre + ext, 'r')
o = open(pre + '.hack', 'w')

# instantiate lists
input = f.readlines()
output = []
clean_code = []

# c-instruction table
c_table = {
        "0"  : "0101010",
        "1"  : "0111111",
        "-1" : "0111010", 
        "D"  : "0001100", 
        "A"  : "0110000", 
        "M"  : "1110000", 
        "!D" : "0001101", 
        "!A" : "0110001", 
        "!M" : "1110001", 
        "-D" : "0001111", 
        "-A" : "0110011", 
        "-M" : "1110011", 
        "D+1": "0011111", 
        "A+1": "0110111", 
        "M+1": "1110111", 
        "D-1": "0001110", 
        "A-1": "0110010", 
        "M-1": "1110010", 
        "D+A": "0000010", 
        "D+M": "1000010", 
        "D-A": "0010011", 
        "D-M": "1010011", 
        "A-D": "0000111", 
        "M-D": "1000111", 
        "D&A": "0000000", 
        "D&M": "1000000", 
        "D|A": "0010101",
        "D|M": "1010101" 
}

# dest table
d_table = {
    "null" : "000",
    "M"    : "001",
    "D"    : "010",
    "MD"   : "011",
    "A"    : "100",
    "AM"   : "101",
    "AD"   : "110",
    "AMD"  : "111"
}

j_table = {
    "null" : "000",
    "JGT"  : "001",
    "JEQ"  : "010",
    "JGE"  : "011",
    "JLT"  : "100",
    "JNE"  : "101",
    "JLE"  : "110",
    "JMP"  : "111"
}

# first we remove blank lines and comments
for line in input:
    if re.match(r'^\s$', line): # skip blank line (has only \t\b\r and whitespace)
        continue 
    if re.match('//', line): # skip if comment
        continue
    else:
        clean_code.append(line[:-1]) # strip off trailing '\n'

# loop over clean code to find A and C instructions
for line in clean_code:
    if re.match("@", line): # line is A instruction
        binary = "{0:016b}".format(int(line[1:])) # convert digit to 16-bit binary number
        output.append(binary)
    elif re.search("=", line): # line is C instruction (contract states that all code is correct and lines are either A or C)
        #implement D=A to 1110110000010000
        dest, comp = line.split('=')
        binary = "111" + c_table[comp] + d_table[dest] + "000"
        output.append(binary)
    elif re.search(";", line): # line is jmp instruction
        comp, jmp = line.split(";")
        binary = "111" + c_table[comp] + "000" + j_table[jmp]
        output.append(binary)

for item in output:
    o.write(f"{item}" + "\n") 
o.close()
f.close()

