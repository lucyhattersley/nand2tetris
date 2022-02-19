import junit.framework.*;
import org.junit.Test;
import org.w3c.dom.ls.LSOutput;

import java.util.*;
import java.io.*;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase {
    
    String testFile = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestSimpleAdd.asm"; // WSL
    //String testFile = "/Users/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestSimpleAdd.asm"; // Mac
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
 
        // File locations
        String input = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.vm";
        String output = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.asm";
        String expectedOutput = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7Expected.asm";

        CodeWriter myCodeWriter = new CodeWriter();
        myCodeWriter.Constructor(output); // creates output file
   
        // TODO Move inside try statement and loop over input file
        String command = "push constant 7";
        myCodeWriter.writeArithmetic(command);
                
        try {            
            BufferedReader brInput = new BufferedReader(new FileReader(output));
            BufferedReader brOutput = new BufferedReader(new FileReader(output));
            BufferedReader brExpectedOutput = new BufferedReader(new FileReader(expectedOutput));

            List<String> lOutput = new ArrayList<String>();
            List<String> lExpectedOutput = new ArrayList<String>();
            
            String sCurrentLine;

            // Parse input line by line
            while ((sCurrentLine = brInput.readLine()) != null) {
                myCodeWriter.writeArithmetic(sCurrentLine);
            }

            // Parse ExcepctedOutout to list
            while ((sCurrentLine = brExpectedOutput.readLine()) != null) {
                lExpectedOutput.add(sCurrentLine);
            }
            
            // Parse output to list
            while ((sCurrentLine = brOutput.readLine()) != null) {
                lOutput.add(sCurrentLine);
            }

            assertEquals(lExpectedOutput, lOutput);

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