public class VMTranslator {

    public static void main(String[] args) {
        System.out.println("Running VMTranslator.main");
        
        // Create objects
        Parser myParser = new Parser();
        CodeWriter myCodeWriter = new CodeWriter();

        String fileNameVM = args[0];
        String fileNameASM = fileNameVM.replaceAll(".vm", ".asm");

        // Init parser with arg
        myParser.initialize(fileNameVM);

        // Init output file
        myCodeWriter.Constructor(fileNameASM);

        // March through commands in parser and write code to ASM file
        while(myParser.hasMoreCommands()) {
            myParser.advance();
            String command = myParser.getCurrentCommand();
            System.out.println(command);
        }
    }
}
