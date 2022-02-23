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
        String simpleAddExpected = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/SimpleAddExpected.asm");
        String simpleAdd = new String("/home/lucy/nand2tetris/projects/07/VMTranslator/src/test/resources/SimpleAdd.asm");

        //Todo initialise Parser, CodeWriter and parse SimpleAdd 

        try {
            BufferedReader brSimpleAddExpected = new BufferedReader(new FileReader(simpleAddExpected));
            BufferedReader brSimpleAdd = new BufferedReader(new FileReader(simpleAdd));
    
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
