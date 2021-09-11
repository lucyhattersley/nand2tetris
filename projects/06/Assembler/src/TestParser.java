import junit.framework.*;
import org.junit.Test;
import java.util.*;

public class TestParser extends TestCase {
	
	/* Add.asm -> Add.hack
	 * 
	 * @2    -> 0000000000000010
     * D=A   -> 1110110000010000
     * @3    -> 0000000000000011
     * D=D+A -> 1110000010010000
     * @0    -> 0000000000000000
     * M=D   -> 1110001100001000
	 * 
	 * */

	public TestParser(String name) {
		super(name);
	}

	//Setup
	String testFile = "../add/Add.asm";
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
		assertEquals("D", myParser.dest());
		
		myParser.currentCommand = "D=D+A";
		assertEquals("D", myParser.dest());
		
		myParser.currentCommand = "M=D";
		assertEquals("M", myParser.dest());
	}
	
	@Test
	public void testComp() {
		myParser.initialize(testFile);
		
		myParser.currentCommand = "D=A";
		assertEquals("A", myParser.comp());
		
		myParser.currentCommand = "D=D+A";
		assertEquals("D+A", myParser.comp());
		
		myParser.currentCommand = "M=D";
		assertEquals("D", myParser.comp());

	}
	
	@Test
	public void testJump() {
		myParser.initialize(testFile);
		
		myParser.currentCommand = "0;JMP";
		assertEquals("JMP", myParser.jump());
		
		myParser.currentCommand = " D;JLE";
		assertEquals("JLE", myParser.jump());
		
	}
	
//	@Test
//	public void testDest() {
//		myParser.initialize(testFile);
//		myParser.currentCommand = "null";
//		assertEquals("000", myParser.dest());
//	}

}