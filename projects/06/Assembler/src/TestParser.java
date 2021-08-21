import junit.framework.*;
import org.junit.Test;
import java.util.*;

public class TestParser extends TestCase {

	public TestParser(String name) {
		super(name);
	}

	//Setup
	String testFile = "/Users/lucy/nand2tetris/projects/06/add/Add.asm";
	Parser myParser = new Parser();	
	
	@Test
	public void testInput() {
		myParser.initialize(testFile);	
		ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("@2", "D=A", "@3", "D=D+A", "@0", "M=D"));
		
		assertEquals(testInput, myParser.input);

	}
	
	@Test
	public void testHasMoreCommands() {
		myParser.initialize(testFile);
		assertTrue(myParser.hasMoreCommands());
	}
	
	@Test
	public void testAdvance() {
		myParser.initialize(testFile);
//		System.out.print(myParser.input);
		
		myParser.advance();
		assertEquals("@2", myParser.currentCommand);
		
		myParser.advance();
		assertEquals("D=A", myParser.currentCommand);

		
		myParser.advance();
		assertEquals("@3", myParser.currentCommand);

		
		myParser.advance();
		assertEquals("D=D+A", myParser.currentCommand);

		
		myParser.advance();
		assertEquals("@0", myParser.currentCommand);

		
		myParser.advance();
		assertEquals("M=D", myParser.currentCommand);

	}
	
	@Test
	public void testCommandType() {
		myParser.initialize(testFile);
		
		myParser.currentCommand = "@2";
		assertEquals("A_COMMAND", myParser.commandType());
		
		myParser.currentCommand = "@0";
		assertEquals("A_COMMAND", myParser.commandType());
		
		myParser.currentCommand = "D=A";
		assertEquals("C_COMMAND", myParser.commandType());
		
		myParser.currentCommand = "D=D+A";
		assertEquals("C_COMMAND", myParser.commandType());
		
		myParser.currentCommand = "(LOOP)";
		assertEquals("L_COMMAND", myParser.commandType());
		
		
	}
	
	@Test
	public void testSymbol() {
		myParser.initialize(testFile);
		
		myParser.currentCommand = "@2";
		assertEquals("2", myParser.symbol());
		
		myParser.currentCommand = "(LOOP)";
		assertEquals("LOOP", myParser.symbol());
	}
	
	@Test
	public void testDest() {
		myParser.initialize(testFile);
		
		myParser.currentCommand = "D=A";
		assertEquals("A", myParser.dest());
		
		myParser.currentCommand = "D=D+A";
		assertEquals("D+A", myParser.dest());
	}

	@Test
	public void testComp() {
		myParser.initialize(testFile);
		myParser.currentCommand = "0";
		assertEquals("101010", myParser.comp());
	}

}