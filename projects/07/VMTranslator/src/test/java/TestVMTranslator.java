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
        String pushConstant7Expected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7Expected.asm");
        String pushConstant7vm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7.vm");
        String pushConstant7asm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant7.asm");

        // Set up VMTranslator
        VMTranslator myVMTranslator = new VMTranslator();
        
        // Pass file location to main
        String[] args = new String[1];
        args[0] = pushConstant7vm;
        myVMTranslator.main(args);

        // Check file output
        try {
            BufferedReader brExpected = new BufferedReader(new FileReader(pushConstant7Expected));
            BufferedReader brASM = new BufferedReader(new FileReader(pushConstant7asm));
    
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

    @Test
    public void testMainPushConstant78() {
        // File locations
        String pushConstant78Expected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78Expected.asm");
        String pushConstant78vm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78.vm");
        String pushConstant78asm = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/PushConstant78.asm");

        // Set up VMTranslator
        VMTranslator myVMTranslator = new VMTranslator();
        
        // Pass file location to main
        String[] args = new String[1];
        args[0] = pushConstant78vm;
        myVMTranslator.main(args);

        // Check file output
        try {
            BufferedReader brExpected = new BufferedReader(new FileReader(pushConstant78Expected));
            BufferedReader brASM = new BufferedReader(new FileReader(pushConstant78asm));
    
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
