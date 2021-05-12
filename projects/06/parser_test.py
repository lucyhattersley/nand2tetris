import unittest
import parser

class TestSum(unittest.TestCase):
    def has_more_commands_test(self):
        self.assertEqual(test_parser.hasMoreCommands, True)

if __name__ == "__main__":
    test_parser = parser.Parser(argv="max/Max.asm")
    unittest.main()
