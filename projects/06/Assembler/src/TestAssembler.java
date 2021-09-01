import static org.junit.Assert.*;

import org.junit.Test;
import java.io.*;

public class TestAssembler {
	Assembler myAssembler = new Assembler();
	
	@Test
	public void testAssembler() throws Exception {
		assertNotNull(myAssembler);
		
		// Load Add.hack
		FileReader fr = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add.hack");
		BufferedReader br = new BufferedReader(fr);
		String response = new String();
		for(String line; (line=br.readLine()) != null; response += line); 
		br.close();
				
		// Run Assembler with Add.asm (creates Add1.hack)
		// For setup testing we are importing file
		

		// Load Add1.hack
		//		String add1File = "/Users/lucy/nand2tetris/projects/06/add/Add.hack";
		FileReader fr2 = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add.hack");
		BufferedReader br2 = new BufferedReader(fr2);
		String response2 = new String();
		for(String line; (line=br2.readLine()) != null; response2 += line); 
		br2.close();

		// Diff files
		assertEquals(response, response2);
	}
	
	/*
	 * Assembler should initalize with the following
	 * SP 0
	 * LCL 1
	 * ARG 2
	 * THIS 3
	 * THAT 4
	 * R0-R15 0-15
	 * SCREEN 16384
	 * KBD 24576
	 */
	
	@Test
	public void testInitialize() {
		myAssembler.initialize();
		assertTrue(myAssembler.symbolTable.containsKey("SP"));

	}

}
