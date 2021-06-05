import unittest
import assembler
import sys

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
            self.f = open('add/Add_test.hack') # malloc problmem. refactor 
            test_file = self.f.read()
            # self.f = open(self.pre + self.ext, 'r') 

            self.test_code.parse_file() # creates hack output file

            self.g = open('add/Add.hack')
            out_file = self.g.read()

            self.f.close() # close the file
            self.g.close() # close the file

            self.assertEqual(out_file, test_file)


if __name__ == "__main__":
    unittest.main(argv=['first-arg-is-ignored'], exit=False) # ignores arg to prevent attribute error
                                                             # args are handled by assembler.py
