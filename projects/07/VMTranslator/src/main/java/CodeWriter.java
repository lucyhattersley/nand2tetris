import java.io.*;
import java.util.*;

public class CodeWriter {
    String fileName;

    /**
     * Opens the output filestream and gets ready to write into it
     * @param output filestream. The file to save the filestream to
     * @return none
     */
    public void Constructor(String output) {
        try {
            File outfile = new File(output);
            FileWriter fw = new FileWriter(outfile);    
        }
        catch (Exception ex) {
            ex.printStackTrace();
            }
    }

    /**
     * Informs the code writer that the translation of a new VM file is started
     * @param fileName
     */
    public void setFileName(String fileName) {
        fileName = fileName;
    }

    /**
     * Writes the assembly code that is the translation of the given arithmetic command
     * @param command
     * @return
     * 
     * Todo: From This: 
     * 
     * push constant 7 
     * 
     * to this
     * 
     *  @7 / A -> 7
        D=A / D -> 7
        @SP / A -> SP value ()
        A=M
        M=D
        @SP
        M=M+1
     * 
     */
    public void writeArithmetic(String command) {
        //TODO write assembly of command to String
        System.out.println("Running writeArithmetic");
    }

    /**
     * Closes the output file
     */
    public void close() {
        // fw.close(); Todo. Close FW
    }
}