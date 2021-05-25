import unittest
import parser

class TestCode(unittest.TestCase):
    def setUp(self):
        self.test_parser = parser.Parser(argv="max/Max.asm")
    
    def test_dest(self):
        test = self.test_parser.dest("null")
        self.assertEqual(test, '000')


if __name__ == "__main__":
    unittest.main()