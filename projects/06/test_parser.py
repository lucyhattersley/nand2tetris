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
            self.assertEqual(self.test_parser.commandType(self), 'A_COMMAND')

        tests = ['A=0' 'A=1', 'A=-1', 'A=D,' 'A=A', 'A=!D', 'A=!A', 'A=-D', 'A=-A', 'A=D+1', 'A=D-1', 'A=A-1', 'A=D+A', 'A=D-A', 
                 'A=A-D', 'A=D&A', 'A=D|A', 'A=M', 'A=!M', 'A=-M', 'A=M+1', 'A=D+M', 'A=D-M', 'A=M-D', 'A=D&M', 'A=D|M']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'C_COMMAND', 'Failed: ' + test)
if __name__ == "__main__":
    unittest.main()
