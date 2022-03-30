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

        if( command.equals("C_PUSH") ) {
            if( segment.equals("constant") ) {
                
                try {
                    String indexString = "@" + String.valueOf(index);
                    String str = indexString + "\nD=A\n@SP\nA=M\nM=D\n@SP\nM=M+1\n";
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