import java.io.*;
import java.util.*;

public class CodeWriter {
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
     * Closes the output file
     */
    // public void Close() {
    //     fw.close();
    // }
}