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
            File myFile = new File(output);
            FileWriter fw = new FileWriter(myFile);    
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
     * Closes the output file
     */
    // public void Close() {
    //     fw.close();
    // }
}