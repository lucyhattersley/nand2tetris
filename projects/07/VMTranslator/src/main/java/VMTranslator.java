import java.io.File;
import java.nio.file.Path;

public class VMTranslator {

    public static void main(String[] args) {
        System.out.println("Running VMTranslator.main");
        
        // Create objects
        Parser myParser = new Parser();
        CodeWriter myCodeWriter = new CodeWriter();

        String filePathVM = args[0];
        String filePathASM = filePathVM.replaceAll(".vm", ".asm");

        // Init parser with arg
        myParser.initialize(filePathVM);

        // Init output file
        myCodeWriter.Constructor(filePathASM);

        // TODO Use set fileName

        // March through commands in parser and write code to ASM file
        while(myParser.hasMoreCommands()) {
            myParser.advance();
            String command = myParser.getCurrentCommand();
            if (myParser.commandType() == "C_PUSH") {
                // TODO writePushPop (find command)
                myCodeWriter.writePushPop(command, segment, index);
            }
            
        }
    }
}
