import static org.junit.Assert.*;
import junit.framework.*;

public class TestCode extends TestCase {
	
	Code myCode = new Code();

	public void testComp() {
		// "0","0101010"
		assertEquals("0101010", myCode.comp("0"));
		
		
	}
}
