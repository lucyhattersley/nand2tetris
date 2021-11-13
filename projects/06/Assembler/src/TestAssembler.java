import static org.junit.Assert.*;
import org.junit.Test;
import java.util.ArrayList;
import java.io.*;

public class TestAssembler {

	String addAsm = "../add/Add.asm";
	String maxAsm = "../max/Max.asm";
	Assembler assembler = new Assembler();

	@Test
	public void testMain(){

		assembler.main(new String[] {addAsm});
		assertNotNull(assembler);
		assertNotNull(assembler.parser);
		assertNotNull(assembler.code);
		assertNotNull(assembler.symbolTable);
		assembler = null; // clear assembler object

	}
	
	@Test
	public void testFirstPassMaxSymbolTable() {
		Assembler assembler = new Assembler();
		assembler.firstPass(maxAsm); // add the symbols from Max.asm to the SymbolTable

		assertTrue(assembler.symbolTable.contains("OUTPUT_FIRST"));

		// assertEquals(assembler.symbolTable.get("OUTPUT_FIRST"), Integer.valueOf(10));
		// assertEquals(assembler.symbolTable.get("OUTPUT_D"), Integer.valueOf(12));
		// assertEquals(assembler.symbolTable.get("INFINITE_LOOP"), Integer.valueOf(14));
		
		// Teardown
		assembler = null; // clear assembler object
	}

	@Test
	public void testFirstPassMaxInput() {
		Assembler assembler = new Assembler();
		assembler.firstPass(maxAsm);

		System.out.println("Initial input"); // Let's take a peek inside
		for (String i : assembler.parser.input) {System.out.println(i);}
		assertTrue(assembler.parser.input.contains("@R0")); // does it contain the first line?

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
		testInput.add("0;JMP");

		assertEquals(testInput, assembler.parser.input);

		assembler = null; // clear Assembler object
		
	}
	
	@Test
	public void testSecondPassAdd() {
		
		Assembler assembler = new Assembler();
		assembler.firstPass(addAsm);
		assembler.secondPass();

		ArrayList testOutput = new ArrayList<String>();
		testOutput.add("0000000000000010"); // @2
		testOutput.add("1110110000010000"); // D=A
		testOutput.add("0000000000000011"); // @3
		testOutput.add("1110000010010000"); // D=D+A
		testOutput.add("0000000000000000"); // @0
		testOutput.add("1110001100001000"); // M=D

		// Test first item. A_COMMAND
		assertEquals(testOutput.get(0), assembler.output.get(0));

		// Test second item. C_COMMAND
		assertEquals(testOutput.get(1), assembler.output.get(1));

		// Test array
		assertEquals(testOutput, assembler.output);

		// Teardown
		assembler = null;
	}

	@Test
	public void testIntToBin() {
		Assembler assembler = new Assembler();
		Integer num = 0;
		assertEquals(assembler.intToBin(num), "0000000000000000");

		num = 1;
		assertEquals(assembler.intToBin(num), "0000000000000001");

		num = 2;
		assertEquals(assembler.intToBin(num), "0000000000000010");

		assembler = null;

	}

	@Test
	public void testStringToBin() {
		Assembler assembler = new Assembler();
		String num = "0";
		assertEquals(assembler.stringToBin(num), "0000000000000000");

		num = "1";
		assertEquals(assembler.stringToBin(num), "0000000000000001");

		num = "2";
		assertEquals(assembler.stringToBin(num), "0000000000000010");

		assembler = null;


	}

	@Test
	public void testSecondPassMax() {
		
		Assembler assembler = new Assembler();

		assembler.firstPass(maxAsm);

		assembler.secondPass();

		ArrayList testOutput = new ArrayList<String>();

		testOutput.add("0000000000000000");
		testOutput.add("1111110000010000");
		testOutput.add("0000000000000001");
		testOutput.add("1111010011010000");
		testOutput.add("0000000000001010");
		testOutput.add("1110001100000001");
		testOutput.add("0000000000000001");
		testOutput.add("1111110000010000");
		testOutput.add("0000000000001100");
		testOutput.add("1110101010000111");
		testOutput.add("0000000000000000");
		testOutput.add("1111110000010000");
		testOutput.add("0000000000000010");
		testOutput.add("1110001100001000");
		testOutput.add("0000000000001110");
		testOutput.add("1110101010000111");
		
		// Test first item. A_COMMAND: R0
		assertEquals(testOutput.get(0), assembler.output.get(0));

		// Test first item. C_COMMAND: D=M
		assertEquals(testOutput.get(1), assembler.output.get(1));

		//Test
		assertEquals(testOutput.get(3), assembler.output.get(3));

		//Test
		assertEquals(testOutput.get(4), assembler.output.get(4));

		//Test
		assertEquals(testOutput.get(5), assembler.output.get(5));

		//Test
		assertEquals(testOutput.get(6), assembler.output.get(6));

		//Test
		assertEquals(testOutput.get(7), assembler.output.get(7));

		//Test
		assertEquals(testOutput.get(8), assembler.output.get(8));

		//Test array
		assertEquals(testOutput, assembler.output);

		// Teardown
		assembler = null;

	}
	
	@Test
	public void testAssembler() throws Exception {
		String asm = new String();
		addAsm = "../add/Add.asm";
	
		Assembler assembler = new Assembler();

		assembler.firstPass(addAsm);
		assembler.secondPass();
		assembler.output(addAsm);
		
		// Load Add.hack
		FileReader fr = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add.hack");
		BufferedReader br = new BufferedReader(fr);
		String response = new String();
		for(String line; (line=br.readLine()) != null; response += line); 
		br.close();
				
		// Load Add1.hack
		String add1File = "/Users/lucy/nand2tetris/projects/06/add/Add.hack";
		FileReader fr2 = new FileReader("/Users/lucy/nand2tetris/projects/06/add/Add1.hack");
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
	

}
