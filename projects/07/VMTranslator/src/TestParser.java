import junit.framework.*;
import org.junit.Test;
import java.util.*;

public class TestParser extends TestCase  {

    //Setup
	String testFile = "/Users/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.vm";
	Parser myParser = new Parser();	

    @Test
	public void testInput() {
		myParser.initialize(testFile);
        ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("push constant 7","push constant 8", "add"));
        assertEquals(testInput, myParser.input);

    }

}