import static org.junit.Assert.*;

import org.junit.Test;

public class TestAssembler {
	Assembler myAssembler = new Assembler();
	
	@Test
	public void testAssembler() {
		assertNotNull(myAssembler);
	}
	
	@Test
	public void testInitialize() {
		myAssembler.initialize();
		assertTrue(myAssembler.symbolTable.containsKey("SP"));

	}

}
