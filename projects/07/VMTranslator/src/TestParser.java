import junit.framework.*;
import org.junit.Test;
import java.util.*;

public class TestParser extends TestCase  {

    // WSL Linux
	String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.vm";
	Parser myParser = new Parser();

    @Test
	public void testInput() {
        myParser.initialize(testFile);
        ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("push constant 7","push constant 8", "add"));
        assertEquals(testInput, myParser.input);

    }

    @Test
    public void testHasMoreCommands() {
        myParser.initialize(testFile);
        assertTrue(myParser.hasMoreCommands());
    }

    @Test
    public void testCurrentCommand() {
        myParser.initialize(testFile);
        myParser.advance();
        String testInput = "push constant 7";
        assertEquals(testInput, myParser.getCurrentCommand());
    }

    @Test
    public void testCommandType() {
        myParser.initialize(testFile);
        myParser.advance();
        String testCommandType = "C_PUSH";
        assertEquals(testCommandType, myParser.commandType());


    }


}