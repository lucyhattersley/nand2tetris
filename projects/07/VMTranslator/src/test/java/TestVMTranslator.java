import org.junit.Test;
import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestVMTranslator extends TestCase {

    /**
     * Tests main program.
     * Input -> PushConstant.vm
     * Output -> PushConstant.asm
     * AssertEquals(PushConstantExpected.asm, PushConstant.asm)
     */
    @Test
    public void testMainPushConstant7() {
        // File locations
        String expected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7Expected.asm");
        String vm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7.vm");
        String asm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7.asm");

        // Call Runner
        testRunner(expected, vm, asm);
    }

    @Test
    public void testMainPushConstant78() {
        // File locations
        String expected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78Expected.asm");
        String vm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78.vm");
        String asm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78.asm");

         // Call Runner
         testRunner(expected, vm, asm);
    }

    @Test
    public void test78Add() {
        // File locations
        String expected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78AddExpected.asm");
        String vm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78Add.vm");
        String asm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78Add.asm");

        // Call Runner
        testRunner(expected, vm, asm);
    }

    public void testRunner(String expected, String vm, String asm) {
        // Set up VMTranslator
        VMTranslator myVMTranslator = new VMTranslator();
        
        // Pass file location to main
        String[] args = new String[1];
        args[0] = vm;
        myVMTranslator.main(args);

        // Check file output
        try {
            BufferedReader brExpected = new BufferedReader(new FileReader(expected));
            BufferedReader brASM = new BufferedReader(new FileReader(asm));
    
            // Check contents of both files
            List<String> lExpectedOutput = new ArrayList<String>();
            List<String> lASM = new ArrayList<String>();
            
            String sCurrentLine;
    
            // Parse Expected to list
            while ((sCurrentLine = brExpected.readLine()) != null) {
                lExpectedOutput.add(sCurrentLine);
            }
    
            // Parse ASM to list
            while ((sCurrentLine = brASM.readLine()) != null) {
                lASM.add(sCurrentLine);
            }

            // Check equality
            assertEquals(lExpectedOutput, lASM);
    
            brASM.close();
            brExpected.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }    
}
