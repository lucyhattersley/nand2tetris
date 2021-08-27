import static org.junit.Assert.*;

import org.junit.Test;

public class TestAssembler {
	Assembler myAssembler = new Assembler();
	
	@Test
	public void testAssembler() {
		assertNotNull(myAssembler);
	}
	
	@Test
	public void testInitializer() {
		myAssembler.initialize();
		assertTrue(myAssembler.symbolTable.contains("SP"));
	}

}
