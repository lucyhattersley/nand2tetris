import static org.junit.Assert.*;

import org.junit.Test;
import java.util.ArrayList;

public class TestAssembler {
	String asm = "/Users/lucy/nand2tetris/projects/06/add/Add.asm";
	Assembler assembler = new Assembler();
	
	
	@Test
	public void testMain(){
		assembler.main(new String[] {asm});
		assertNotNull(assembler);
		assertNotNull(assembler.parser);
		assertNotNull(assembler.code);
	}
	
	// @Test
	// public void testFirstPass() throws Exception {
	// 	assembler.firstPass(asm);
	// 	ArrayList testInputDuplication = new ArrayList();
	// 	assertEquals(assembler.inputDuplication, testInputDuplication); // should be empty with Add.hack
	// }	
	
	@Test
	public void testFirstPassMaxSymbolTable() {
		String asm = "/Users/lucy/nand2tetris/projects/06/max/Max.asm";
		assembler.firstPass(asm); // add the symbols from Max.asm to the SymbolTable

		assertTrue(assembler.symbolTable.containsKey("OUTPUT_FIRST"));
		assertTrue(assembler.symbolTable.containsValue(10));
		assertEquals(assembler.symbolTable.get("OUTPUT_FIRST"), Integer.valueOf(10));
		assertEquals(assembler.symbolTable.get("OUTPUT_D"), Integer.valueOf(12));
		assertEquals(assembler.symbolTable.get("INFINITE_LOOP"), Integer.valueOf(14));
	}

	@Test
	public void testFirstPassMaxInput() {
		String asm = "/Users/lucy/nand2tetris/projects/06/max/Max.asm";
		Assembler assembler = new Assembler();
		
		assembler.parser.initialize(asm);

		System.out.println("Initial input"); // Let's take a peek inside
		for (String i : assembler.parser.input) {System.out.println(i);}
		assertTrue(assembler.parser.input.contains("@R0")); // does it contain the first line?

		assembler.firstPass(asm);
		System.out.println("After first pass"); // Let's take a peek inside
		for (String i : assembler.parser.input) {System.out.println(i);}

		assertTrue(assembler.parser.input.contains("@R0")); // does it still contain the first line?

		ArrayList<String> testInput = new ArrayList<String>();
		testInput.add("@R0");
		testInput.add("D=M");
		testInput.add("@R1");
		testInput.add("D=D-M");
		testInput.add("@OUTPUT_FIRST");
		testInput.add("D;JGT");
		testInput.add("@R1");
		testInput.add("D=M");
		testInput.add("@OUTPUT_D");
		testInput.add("0;JMP"); 
		testInput.add("@R0");
		testInput.add("D=M");
		testInput.add("@R2");
		testInput.add("M=D");
		testInput.add("@INFINITE_LOOP");

		// assertEquals(testInput, assembler.parser.input);
		

		// for (String i : assembler.parser.input) {System.out.println(i);}
		
		// ArrayList testInput = new ArrayList();
		// testInput.add("@R0");

		// assembler.firstPass(asm);
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
