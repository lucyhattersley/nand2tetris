import junit.framework.*;
import org.junit.Test;

public class TestSymbolTable extends TestCase {
	
	SymbolTable mySymbolTable = new SymbolTable();
	String symbol = "A";
	Integer address = 1;

	@Test
	public void testConstructor() {
		assertNotNull(mySymbolTable);
	}
	
	@Test
	public void testAddEntry( ) {
		mySymbolTable.addEntry(symbol, address);
		assertTrue(mySymbolTable.symbolTable.containsKey("A"));
		
	}
	
	@Test
	public void testContains() {
		mySymbolTable.addEntry(symbol, address);
		assertTrue(mySymbolTable.contains("A"));
	}
	
	@Test
	public void testGetAddress() {
		mySymbolTable.addEntry(symbol, address);
		assertEquals(Integer 1, mySymbolTable.GetAddress("A"));
		
	}

}
