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

        def test_add(self):
            self.test_code = assembler.Assembler(argv='add/Add.asm') # create new assembler instance with file argument

            self.f = open('add/Add_test.hack') # first we open test file (created by course assembler) 
            test_file = self.f.read()

            self.test_code.parse_file() # This creates hack output file

            self.g = open('add/Add.hack') # Open hack output file
            out_file = self.g.read()

            self.assertEqual(out_file, test_file) # Check both files the same

            self.f.close() # close the files to tidy up
            self.g.close() 

        def test_max(self):
            self.test_code = assembler.Assembler(argv='max/Max.asm') 
            self.f = open('max/Max_test.hack')  
            test_file = self.f.read()

            self.test_code.parse_file() # This creates hack output file

            self.g = open('max/Max.hack') # Open hack output file
            out_file = self.g.read()

            self.assertEqual(out_file, test_file) # Check both files the same

            self.f.close() # close the files to tidy up
            self.g.close() 

        # def test_pongtest(self): # one line program that tests 'ponggame.0' command
        #     self.test_code = assembler.Assembler(argv='pongtest/Pongtest.asm') 
 
        #     self.f = open('pongtest/Pongtest_test.hack')  
        #     test_file = self.f.read()

        #     self.test_code.parse_file() # This creates hack output file

        #     self.g = open('pong/Pong.hack') # Open hack output file
        #     out_file = self.g.read()

        #     self.assertEqual(out_file, test_file) # Check both files the same

        #     self.f.close() # close the files to tidy up
        #     self.g.close() 

        def test_pong(self):
            self.test_code = assembler.Assembler(argv='pong/Pong.asm') 
 
            self.f = open('pong/Pong_test.hack')  
            test_file = self.f.read()

            self.test_code.parse_file() # This creates hack output file

            self.g = open('pongtest/Pongtest.hack') # Open hack output file
            out_file = self.g.read()

            self.assertEqual(out_file, test_file) # Check both files the same

            self.f.close() # close the files to tidy up
            self.g.close() 


if __name__ == "__main__":
    unittest.main(argv=['first-arg-is-ignored'], exit=False) # ignores arg to prevent attribute error
                                                             # args are handled by assembler.py
