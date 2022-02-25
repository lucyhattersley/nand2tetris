import org.junit.Test;
import java.io.*;
import java.util.*;
import junit.framework.TestCase;

public class TestVMTranslator extends TestCase {

    /**
     * Tests main program.
     * Input -> SimpleAdd.vm
     * Output -> SimpleAdd.asm
     * AssertEquals(SimpleAddExpected.asm, SimpleAdd.asm)
     */
    @Test
    public void testMain() {
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
            BufferedReader brSimpleAddExpected = new BufferedReader(new FileReader(pushConstant7Expected));
            BufferedReader brSimpleAdd = new BufferedReader(new FileReader(pushConstant7asm));
    
            // Check contents of both files
            List<String> lExpectedOutput = new ArrayList<String>();
            List<String> lOutput = new ArrayList<String>();
            
            String sCurrentLine;
    
            // Parse SimpleAddExpected to list
            while ((sCurrentLine = brSimpleAddExpected.readLine()) != null) {
                lExpectedOutput.add(sCurrentLine);
            }
    
            // Parse SimpleAdd to list
            while ((sCurrentLine = brSimpleAdd.readLine()) != null) {
                lOutput.add(sCurrentLine);
            }
    
            assertEquals(lExpectedOutput, lOutput);
            
            brSimpleAdd.close();
            brSimpleAddExpected.close();
            
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}
