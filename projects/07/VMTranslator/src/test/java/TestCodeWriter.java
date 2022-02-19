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
        String outputFile = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.asm"; // WSL
        CodeWriter myCodeWriter = new CodeWriter();
        myCodeWriter.Constructor(outputFile); // creates output file
   
        // Write asm code to output file
        String command = "push constant 7";
        myCodeWriter.writeArithmetic(command);
                
        try {            
            String input = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.vm";
            String output = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7.asm";
            String expectedOutput = "/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/TestPushConstant7Expected.asm";

            BufferedReader rOutput = new BufferedReader(new FileReader(output));
            BufferedReader rExpectedOutput = new BufferedReader(new FileReader(expectedOutput));

            List<String> lOutput = new ArrayList<String>();
            List<String> lExpectedOutput = new ArrayList<String>();
            
            // Parse output to list
            String sCurrentLine;

            while ((sCurrentLine = rOutput.readLine()) != null) {
                lOutput.add(sCurrentLine);
            }

            // Parse ExcepctedOutout to list
            while ((sCurrentLine = rExpectedOutput.readLine()) != null) {
                lExpectedOutput.add(sCurrentLine);
            }

            assertEquals(lExpectedOutput, lOutput);

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    // Diff files code from https://www.baeldung.com/java-compare-files
    // @Test
    // public void whenFilesIdenticalIgnoreEOF_thenReturnTrue() throws IOException {
    //     Path path1 = Files.createTempFile("file1Test", ".txt");
    //     Path path2 = Files.createTempFile("file2Test", ".txt");
    
    //     Files.writeString(path1, "testing line 1 \n line 2");
    //     Files.writeString(path2, "testing line 1 \r\n line 2");
    
    //     Reader reader1 = new BufferedReader(new FileReader(path1.toFile()));
    //     Reader reader2 = new BufferedReader(new FileReader(path2.toFile()));
    
    //     assertTrue(IOUtils.contentEqualsIgnoreEOL(reader1, reader2));
    // }


    //Teardown
    // @Test
    // public void testClose() {
    //     myCodeWriter.close();
    //     // assertTrue(); // TODO create test for closing of FW (FileWriter)
    // }

}