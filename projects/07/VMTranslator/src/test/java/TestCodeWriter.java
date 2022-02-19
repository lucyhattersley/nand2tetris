import junit.framework.*;
import org.junit.Test;
import java.util.*;
import java.io.*;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase {
    
    // String testFile = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestSimpleAdd.asm"; // WSL
    String testFile = "/Users/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestSimpleAdd.asm"; // Mac
    CodeWriter myCodeWriter = new CodeWriter();

    @Test
	public void testConstructor() {        
		myCodeWriter.Constructor(testFile); // creates output file
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
        String outputFile = "/Users/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.asm"; // Mac
        CodeWriter myCodeWriter = new CodeWriter();
        myCodeWriter.Constructor(outputFile); // creates output file
   
        // Write asm code to output file
        String command = "push constant 7";
        myCodeWriter.writeArithmetic(command);
                
        try {
            FileReader fr = new FileReader(outputFile);
            BufferedReader br = new BufferedReader(fr);
            // TODO: import TestPushConstant7Expected.asm
            // TODO diff outputFile and TestPushConstant7Expected.asm

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