import junit.framework.*;
import org.junit.Test;
import java.util.*;
import java.io.FileInputStream;
import java.io.PrintStream;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase  {
    //String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.asm"; // WSL
    String testFile = "/Users/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.asm"; // Mac

    CodeWriter myCodeWriter = new CodeWriter();

    @Test
	public void testConstructor() {
		myCodeWriter.Constructor(testFile);
        Path path = Paths.get(testFile);
        assertTrue(Files.exists(path)); // Check asm file exists

    }

    @Test
    public void setFileName() {
        String testFile = "SimpleAdd.vm";
        myCodeWriter.setFileName(testFile);
        assertEquals(testFile, myCodeWriter.fileName.toString());
    }

    @Test
    public void testWriteArithmetic() {
        String command = "push constant 7";
        
        myCodeWriter.writeArithmetic(command);        
        
        //Test contents writtent to testFile
        System.out.println("Running testWriteArithmetic");
        System.out.println(testFile.length()); 
        
        try {
            FileInputStream fs = new FileInputStream(testFile);
            int i = fs.read(); // Gets first byte
            while(i != -1) {
                System.out.print((char)i);
                i = fs.read(); // get next char
            }
            fs.close();
        }
        catch(Exception ex) {
            ex.getStackTrace();
        } 


    }

    //Teardown
    // @Test
    // public void testClose() {
    //     myCodeWriter.close();
    //     // assertTrue(); // TODO create test for closing of FW (FileWriter)
    // }



}