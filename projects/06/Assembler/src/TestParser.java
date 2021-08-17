import junit.framework.*;
import org.junit.Test;
import java.util.*;


public class TestParser extends TestCase {
	
	public TestParser(String name) {
		super(name);
	}

	@Test
	public void testInput() {
		Parser testParser = new Parser();
		
		String testFile = "/Users/lucy/nand2tetris/projects/06/add/Add.asm"; 
		testParser.initialize(testFile);
		
		ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("@2", "D=A", "@3", "D=D+A", "@0", "M=D"));
		
		assertEquals(testInput, testParser.input);

	}

}