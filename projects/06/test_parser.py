import unittest
import parser

class TestParser(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser(argv="max/Max.asm")

    def test_hasMoreCommands(self):
        self.assertTrue(self.test_parser.hasMoreCommands())

    def test_commandType(self):
        self.test_parser.command = '@123'
        self.assertEqual(self.test_parser.commandType(self), 'A_COMMAND')

if __name__ == "__main__":
    unittest.main()
