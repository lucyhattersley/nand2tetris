import junit.framework.*;
import org.junit.Test;
import java.util.*;

public class TestParser extends TestCase {
   @Test
    public void TestParser(String name) {
        super(name); // Test creation
    }

    //Setup
	String testFile = "../../StackArithmetic/SimpleAdd/SimpleAdd.vm";
	Parser myParser = new Parser();	

    @Test
	public void testInput() {
		myParser.initialize(testFile);
        ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("push constant 7","push constant 8", "add"));

    }

}