import static org.junit.Assert.*;
import org.junit.Test;

public class TestSymbolTable {

    SymbolTable testSymbolTable = new SymbolTable();

    @Test
	public void testInit() {
        testSymbolTable.initialize();
        
        assertTrue( testSymbolTable.contains("R0") );
    
    }

    @Test
    public void testAddEntry() {
        testSymbolTable.addEntry("Test", 0);
        assertTrue( testSymbolTable.contains("Test") );
    }

    @Test
    public void testGetAddress() {
        testSymbolTable.addEntry("Test", 0);
        assertEquals (testSymbolTable.getAddress("Test"), Integer.valueOf(0));    
    }

}