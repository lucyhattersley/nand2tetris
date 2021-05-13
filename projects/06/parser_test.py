import unittest
import parser

class TestParser(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser(argv="max/Max.asm")

    def test_hasMoreCommands(self):
        self.assertTrue(self.test_parser.hasMoreCommands())

    def test_commandType(self):
        self.assertEqual(self.test_parser.commandType(command='@123'), 'A_COMMAND')

if __name__ == "__main__":
    unittest.main()
