import junit.framework.*;
import org.junit.Test;

public class TestSymbolTable extends TestCase {
	
	SymbolTable mySymbolTable = new SymbolTable();

	@Test
	public void testConstructor() {
		assertNotNull(mySymbolTable);
	}
	
	@Test
	public void testAddEntry( ) {
		String symbol = "A";
		Integer address = 1;
		mySymbolTable.addEntry(symbol, address);
		assertTrue(mySymbolTable.symbolTable.containsKey("A"));
		
	}

}
