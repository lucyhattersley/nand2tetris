import java.io.*;
import java.util.*;

public class CodeWriter {
    String fileName;
    BufferedWriter bw;

    /**
     * Opens the output filestream and gets ready to write into it
     * @param output filestream. The file to save the filestream to
     * @return none
     */
    CodeWriter(String output) {
        try {
            File outfile = new File(output);
            // outfile.createNewFile();
            FileWriter fw = new FileWriter(outfile);
            bw = new BufferedWriter(fw);
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
        if( command.equals("C_PUSH") ) {
            if( segment.equals("constant") ) {
                
                try {
                    //TODO Integrate command, segment, index here
                    String str = "@7\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1";
                    bw.append(str);
                }
                
                catch(Exception ex) {
                    ex.printStackTrace();
                }
            }
        }   
    }

    /**
     * Closes the output file
     */
    public void close() {
        try{
            bw.close();
        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}