import junit.framework.*;
import org.junit.Test;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase {
    String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.asm"; // WSL
    //String testFile = "/Users/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.asm"; // Mac

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
        String command = "push constant 7"; // Test simple command
        
        myCodeWriter.writeArithmetic(command); // Ask codeWriter to write to test file
        
        // Test contents written to testFile
        // TODO Move from print statements to assert
        System.out.println("Running testWriteArithmetic");
        System.out.println(testFile.length()); 
        
        try {
            FileReader fr = new FileReader(testFile);
            BufferedReader br = new BufferedReader(fr);
            while(br.ready()) {
                System.out.println(br.readLine());
            }
        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    //Teardown
    // @Test
    // public void testClose() {
    //     myCodeWriter.close();
    //     // assertTrue(); // TODO create test for closing of FW (FileWriter)
    // }

}