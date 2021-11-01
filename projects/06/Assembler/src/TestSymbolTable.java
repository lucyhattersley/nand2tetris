import static org.junit.Assert.*;
import org.junit.Test;

public class TestAssembler {

    SymbolTable testSymbolTable = new SymbolTable();

    @Test
	public void testInit() {
        assertFalse(testSymbolTable.symbolTable);
    
    }

}