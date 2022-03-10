import java.io.File;
import java.nio.file.Path;

public class VMTranslator {

    public static void main(String[] args) {
        Parser myParser = new Parser();        
        String filePathVM = args[0];
        String filePathASM = filePathVM.replaceAll(".vm", ".asm");
        
        CodeWriter myCodeWriter = new CodeWriter(filePathASM);

        // Init parser with arg
        myParser.initialize(filePathVM);

        // TODO Use set fileName

        // March through commands in parser and write code to ASM file
        while(myParser.hasMoreCommands()) {
            myParser.advance();
            if (myParser.commandType() == "C_PUSH") {
                String command = myParser.commandType();
                String segment = myParser.arg1();
                int index = myParser.arg2();

                myCodeWriter.writePushPop(command, segment, index);
            }
            
        }
    }
}
