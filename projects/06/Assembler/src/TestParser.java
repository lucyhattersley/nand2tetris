import org.junit.Test;

public class TestParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		System.out.println("Initializing parser");
		Parser myParser = new Parser();
 
		String testFile = "/Users/lucy/nand2tetris/projects/06/add/Add.asm";
		System.out.println("Testfile is: " + testFile);

		System.out.println("Initializing file");
		myParser.initialize(testFile);

		System.out.println("Starting tests");
		System.out.println("Getting parser input:");		
		myParser.printInput();

		Boolean test = myParser.hasMoreCommands();
		System.out.println("Test: hasMoreCommands " + test);
		
		if (test == true) {
			System.out.println("Pass");
		} else {
			System.out.println("Fail");
		}

		

	}

}
