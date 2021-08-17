import junit.framework.*;
import org.junit.Test;
import java.util.*;


public class TestParser extends TestCase {

	public TestParser(String name) {
		super(name);
	}

	//Setup
	String testFile = "/Users/lucy/nand2tetris/projects/06/add/Add.asm";
	Parser testParser = new Parser();	
	
	@Test
	public void testInput() {
		testParser.initialize(testFile);	
		ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("@2", "D=A", "@3", "D=D+A", "@0", "M=D"));
		
		assertEquals(testInput, testParser.input);

	}
	
	@Test
	public void testHasMoreCommands() {
		testParser.initialize(testFile);
		assertTrue(testParser.hasMoreCommands());
	}

}