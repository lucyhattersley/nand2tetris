import static org.junit.Assert.*;
import org.junit.Test;

public class TestSymbolTable {

    SymbolTable testSymbolTable = new SymbolTable();

    @Test
	public void testInit() {
        testSymbolTable.initialize();
        
        assertTrue( testSymbolTable.contains("R0") );
    
    }

}