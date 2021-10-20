import junit.framework.*;
import junit.extensions.TestSetup;
import org.junit.Test;


public class TestCode extends TestCase {
	
	Code myCode = new Code();

	@Test
	public void testComp() {
	
		assertEquals("0101010", myCode.comp("0"));
	}
	
	@Test
	public void testDest() {

		assertEquals("001", myCode.dest("M"));
	}

	@Test
	public void testJump() {
		// "M","001"
		assertEquals("001", myCode.jump("JGT"));
	}


}
