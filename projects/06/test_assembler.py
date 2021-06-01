import unittest
import assembler

# @2
# D=A
# @3
# D=D+A
# @0
# M=D

# 0000000000000010
# 1110110000010000
# 0000000000000011
# 1110000010010000
# 0000000000000000
# 1110001100001000

class TestAssembler(unittest.TestCase):
        def setUp(self):
            self.test_code = assembler.Assembler()

        def test_parse(self):
            test = self.test_code.parse("@2")
            self.assertEqual(test, '0000000000000010')

            test = self.test_code.parse("D=A")
            self.assertEqual(test, '1110110000010000')

            test = self.test_code.parse("@3")
            self.assertEqual(test, '0000000000000011')

            test = self.test_code.parse("D=D+A")
            self.assertEqual(test, '1110000010010000')
            
            test = self.test_code.parse("@0")
            self.assertEqual(test, '0000000000000000')
            
            test = self.test_code.parse("M=D")
            self.assertEqual(test, '1110001100001000')

        def test_parse_file(self):
            # load Add_test.hack file and test output against 
if __name__ == "__main__":
    unittest.main()
