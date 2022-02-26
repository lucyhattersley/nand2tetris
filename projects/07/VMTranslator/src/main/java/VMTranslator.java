public class VMTranslator {

    public static void main(String[] args) {
        System.out.println("Running VMTranslator.main");
        
        // Create objects
        Parser myParser = new Parser();
        CodeWriter myCodeWriter = new CodeWriter();

        // Init parser with arg
        myParser.initialize(args[0]);

        // TODO March through commands in parser and write code to asm file
        while(myParser.hasMoreCommands()) {
            myParser.advance();
            String command = myParser.getCurrentCommand();
            System.out.println(command);
        }
    }
}
