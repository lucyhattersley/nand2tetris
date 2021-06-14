import unittest
import parser

class TestParser(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser()

    def test_hasMoreCommands(self):
        self.assertFalse(self.test_parser.hasMoreCommands())

    def test_commandType(self):
        tests = ['@', '@123', '@TEST']
        for test in tests:
            self.assertEqual(self.test_parser.commandType(test), 'A_COMMAND', 'Failed: ' + test)

        tests = ['A=0' 'A=1', 'A=-1', 'A=D,' 'A=A', 'A=!D', 'A=!A', 'A=-D', 'A=-A', 'A=D+1', 'A=D-1', 'A=A-1', 'A=D+A', 'A=D-A', 
                 'A=A-D', 'A=D&A', 'A=D|A', 'A=M', 'A=!M', 'A=-M', 'A=M+1', 'A=D+M', 'A=D-M', 'A=M-D', 'A=D&M', 'A=D|M']
        for test in tests:
            self.assertEqual(self.test_parser.commandType(test), 'C_COMMAND', 'Failed: ' + test)

        tests = ['()', '(LOOP)', '(1)']
        for test in tests:
            self.assertEqual(self.test_parser.commandType(test), 'L_COMMAND', 'Failed: ' + test)
    
    def test_symbol(self):
        self.test_parser.command = '@123'
        self.assertEqual(self.test_parser.symbol(), '123')
        
        self.test_parser.command = '(LOOP)'
        self.assertEqual(self.test_parser.symbol(), 'LOOP')

    def test_dest(self):
        self.test_parser.command = 'A=0'
        self.assertEqual(self.test_parser.dest(), 'A')
        
        self.test_parser.command = 'D=A'
        self.assertEqual(self.test_parser.dest(), 'D')
    
    def test_comp(self):
        self.test_parser.command = 'A=M'
        self.assertEqual(self.test_parser.comp(), 'M')
        
        self.test_parser.command = 'A=A-1'
        self.assertEqual(self.test_parser.comp(), 'A-1')

    def test_jump(self):
        self.test_parser.command = 'D;JGT'
        self.assertEqual(self.test_parser.jump(), 'JGT')
        
        self.test_parser.command = 'D;JMP'
        self.assertEqual(self.test_parser.jump(), 'JMP')

if __name__ == "__main__":
    unittest.main()
