public class VMTranslator {

    public static void main(String[] args) {
        System.out.println("Running VMTranslator.main");
        
        // Create objects
        Parser myParser = new Parser();
        CodeWriter myCodeWriter = new CodeWriter();

        // Init parser with arg
        myParser.initialize(args[0]);
    }
}
