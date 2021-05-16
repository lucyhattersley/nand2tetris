import unittest
import code

class TestCode(unittest.TestCase):
    def test_dest(self):
        unittest.assertEqual(code.dest("null"), '000')


if __name__ == "__main__":
    unittest.main()