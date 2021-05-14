import unittest
import parser

class TestParser(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser(argv="max/Max.asm")

    def test_hasMoreCommands(self):
        self.assertTrue(self.test_parser.hasMoreCommands())

    
    # A_COMMANDS
    # @123 A_COMMAND
    # @
    # @TEST

    # C_COMMANDS
    # D=M
    
    # L_COMMANDS

    def test_commandType(self):
        tests = ['@', '@123', '@TEST']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'A_COMMAND')

        tests = ['A=M', 'A=D']
        for test in tests:
            self.test_parser.command = test
            self.assertEqual(self.test_parser.commandType(self), 'C_COMMAND')
if __name__ == "__main__":
    unittest.main()
