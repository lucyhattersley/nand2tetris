import java.io.*;
import java.util.*;

public class CodeWriter {
    String fileName;
    FileWriter fw;

    /**
     * Opens the output filestream and gets ready to write into it
     * @param output filestream. The file to save the filestream to
     * @return none
     */
    CodeWriter(String output) {
        try {
            File outfile = new File(output);
            outfile.createNewFile();
            fw = new FileWriter(outfile);    
        }
        catch (Exception ex) {
            ex.printStackTrace();
            }
    }

    /**
     * Informs the code writer that the translation of a new VM file is started
     * @param fileName
     */
    public void setFileName(String fileNameInput) {
        fileName = fileNameInput;
    }

    /**
     * Writes the assembly code that is the translation of the given arithmetic command
     * @param command
     * @return none
     */
    public void writeArithmetic(String command) {
        //TODO implement writeArithmetic

        // if arg1 constant
    
        // constant value
    }

    /**
     * WritePushPop
     * Writes the assembly code that is the translation of the given command, where command is either C_PUSH or C_POP
     * @param command (C_PUSH or C_POP, a string)
     * @param segment (string)
     * @param index (int)
     */
    public void writePushPop(String command, String segment, int index) {
        System.out.println("Running writePushPop");
        String[] output;

        System.out.println("command: " + command);
        System.out.println("segment: " + segment);
        System.out.println("index: " + index);

        if(command == "C_PUSH") {
            System.out.println("C_PUSH");
            try {
                String str = "@7";
                fw.write(str);
            }
            catch(Exception ex) {
                ex.printStackTrace();
            }
        }
        
        // TODO test and implement writePushPop
        /* 
        * From This: 
        * 
        * push constant 7 
        * 
        * to this
        * 
        *  @7 / A -> 7 - constant 7 
           D=A / D -> A(7)
           @SP / A -> SP (0)
           A=M / A -> 256  
           M=D / M(256) -> 7
           @SP / A -> 256 
           M=M+1 / 256 +1
           */
    }

    /**
     * Closes the output file
     */
    public void close() {
        try{
            fw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}