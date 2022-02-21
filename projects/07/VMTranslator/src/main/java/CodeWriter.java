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
     *  @7 / A -> 7 / constant 7
        D=A / D -> A(7)
        @SP / A -> SP (default value 256) / push to stack
        A=M / 256 -> M (M refers to the memory location addressed by A) 
        M=D / M(256) -> 7
        @SP / A -> 256 // increase stack pointer
        M=M+1 / 256 +1
     * 
     */
    public void writeArithmetic(String command) {

        // Split command into cmd, arg1, arg2
        String[] result = command.split(" ");
        String cmd = result[0];
        String arg1 = result[0];
        String arg2 = result[0];
        

        // If command C_PUSH

        // if arg1 constant

        // constant value
    }

    /**
     * Closes the output file
     */
    public void close() {
        // fw.close(); Todo. Close FW
    }
}