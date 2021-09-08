import static org.junit.Assert.*;

import org.junit.Test;
import java.io.*;
import java.util.ArrayList;

public class TestAssembler {
	String asm = "/Users/lucy/nand2tetris/projects/06/add/Add.hack";
	Assembler assembler = new Assembler();
	
	
	@Test
	public void testMain(){
		assembler.main(new String[] {asm});
		assertNotNull(assembler);
		assertNotNull(assembler.parser);
		assertNotNull(assembler.code);

	
	}
	
//	@Test
	public void testFirstPass() throws Exception {
		assembler.firstPass(asm);
		ArrayList testInputDuplication = new ArrayList();
		assertEquals(assembler.inputDuplication, testInputDuplication); // should be empty with Add.hack
		
	}	
	
	@Test
	public void testFirstPassMax() {
		String asm = "/Users/lucy/nand2tetris/projects/06/max/Max.hack";
		Assembler assembler = new Assembler();
		assembler.firstPass(asm);
		assertTrue(assembler.symbolTable.containsKey("OUTPUT_FIRST"));
		assertTrue(assembler.symbolTable.containsValue(0101));
	}
	
//	@Test
//	public void testAssembler() throws Exception {
//		assertNotNull(myAssembler);
//		
//		// Load Add.hack
//		FileReader fr = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add.hack");
//		BufferedReader br = new BufferedReader(fr);
//		String response = new String();
//		for(String line; (line=br.readLine()) != null; response += line); 
//		br.close();
				
		// Load Add1.hack
		//		String add1File = "/Users/lucy/nand2tetris/projects/06/add/Add.hack";
		// FileReader fr2 = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add1.hack");
		// BufferedReader br2 = new BufferedReader(fr2);
		// String response2 = new String();
		// for(String line; (line=br2.readLine()) != null; response2 += line); 
		// br2.close();
		
		// Addbad.hack (different file, should fail)
		// FileReader fr3 = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Addbad.hack");
		// BufferedReader br3 = new BufferedReader(fr3);
		// String response3 = new String();
		// for(String line; (line=br3.readLine()) != null; response3 += line); 
		// br3.close();
		

		// Diff files
//		assertEquals(response, response2);
//		
//		// Diff files
//		assertNotEquals(response, response3);
//	}
//	
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
	

}
