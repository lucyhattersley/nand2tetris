import junit.framework.*;
import org.junit.Test;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.PrintStream;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase {
    //String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/TestSimpleAdd.asm"; // WSL
    String testFile = "/Users/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/TestSimpleAdd.asm"; // Mac

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

    /***
     * Test: push constant 7
     * 
     * Expected result: these lines added to TestSimpleAdd.asm file
     * @7
     * D=A
     * @SP
     * A=M
     * M=D
     * @SP
     * M=M+1
     */
    @Test
    public void testWriteArithmeticPushConstant7() {
        String command = "push constant 7"; // Test simple command
        myCodeWriter.writeArithmetic(command); // Ask codeWriter to write to test file
        
        // TODO Remove debug print statements
        System.out.println("Running testWriteArithmetic");
        System.out.println(testFile.length()); 
        
        try {
            FileReader fr = new FileReader(testFile); // TODO compare two files
            BufferedReader br = new BufferedReader(fr);
            for(int i=0; i < 7; i++) {
                System.out.println(br.readLine()); // TODO Change to assert

            } { // TODO move to first 7 lines of testFile
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