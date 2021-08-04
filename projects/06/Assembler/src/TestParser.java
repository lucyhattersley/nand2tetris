public class TestParser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Parser myParser = new Parser();
		
		String testFile = "/Users/lucy/nand2tetris/projects/06/add/Add.asm";
		myParser.initialize(testFile);

		myParser.printInput();

	}

}
