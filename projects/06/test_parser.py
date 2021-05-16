import unittest
import parser

class TestParser(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser(argv="max/Max.asm")

    def test_hasMoreCommands(self):
        self.assertTrue(self.test_parser.hasMoreCommands())

    def test_commandType(self):
        tests = ['@', '@123', '@TEST']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'A_COMMAND', 'Failed: ' + test)

        tests = ['A=0' 'A=1', 'A=-1', 'A=D,' 'A=A', 'A=!D', 'A=!A', 'A=-D', 'A=-A', 'A=D+1', 'A=D-1', 'A=A-1', 'A=D+A', 'A=D-A', 
                 'A=A-D', 'A=D&A', 'A=D|A', 'A=M', 'A=!M', 'A=-M', 'A=M+1', 'A=D+M', 'A=D-M', 'A=M-D', 'A=D&M', 'A=D|M']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'C_COMMAND', 'Failed: ' + test)

        tests = ['()', '(LOOP)', '(1)']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'L_COMMAND', 'Failed: ' + test)
    
    def test_symbol(self):
        self.test_parser.command = '@123'
        self.assertEqual(self.test_parser.symbol(), '123')
        
        self.test_parser.command = '@TEST'
        self.assertEqual(self.test_parser.symbol(), 'TEST')
        
        self.test_parser.command = '(LOOP)'
        self.assertEqual(self.test_parser.symbol(), 'LOOP')

    def test_dest(self):
        self.test_parser.command = 'D=M'
        self.assertEqual(self.test_parser.dest(), 'D')

    def test_comp(self):
        self.test_parser.command = 'D=M'
        self.assertEqual(self.test_parser.comp(), 'M')

        self.test_parser.command = 'D=D-A'
        self.assertEqual(self.test_parser.comp(), 'D-A')

        self.test_parser.command = 'D=D-A'
        self.assertEqual(self.test_parser.comp(), 'D-A')

        self.test_parser.command = 'D;JEQ'
        self.assertEqual(self.test_parser.comp(), 'D')

    def test_jump(self):
        self.test_parser.command = 'D;JEQ'
        self.assertEqual(self.test_parser.jump(), 'JEQ')
        
if __name__ == "__main__":
    unittest.main()

