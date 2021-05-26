import unittest
import parser

class TestCode(unittest.TestCase):
    def setUp(self):
       self.test_code = parser.Code()
    
    def test_dest(self):
        test = self.test_code.dest("null")
        self.assertEqual(test, '000')

    def test_comp(self):
        test = self.test_code.comp("0")
        self.assertEqual(test, '0101010')

    def test_jump(self):
        test = self.test_code.jump("null")
        self.assertEqual(test, '000')
        
if __name__ == "__main__":
    unittest.main()