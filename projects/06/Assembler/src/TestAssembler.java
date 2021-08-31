import static org.junit.Assert.*;

import org.junit.Test;

public class TestAssembler {
	Assembler myAssembler = new Assembler();
	
	@Test
	public void testAssembler() {
		assertNotNull(myAssembler);
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
