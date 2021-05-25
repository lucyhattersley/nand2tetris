import unittest
import parser

class TestCode(unittest.TestCase):
    def setUp(self):
       self.test_code = parser.Code()
    
    def test_dest(self):
        test = self.test_code.dest("null")
        self.assertEqual(test, '000')


if __name__ == "__main__":
    unittest.main()