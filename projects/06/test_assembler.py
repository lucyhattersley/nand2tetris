import unittest
import parser
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

