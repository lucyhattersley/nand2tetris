import static org.junit.Assert.*;
import junit.framework.*;

public class TestCode extends TestCase {
	
	Code myCode = new Code();

	public void testComp() {
	
		assertEquals("0101010", myCode.comp("0"));
	}
	
	public void testDest() {

		assertEquals("001", myCode.dest("M"));
	}

	public void testJump() {
		// "M","001"
		assertEquals("001", myCode.jump("JGT"));
	}


}
