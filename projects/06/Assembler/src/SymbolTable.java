import java.util.HashMap;

public class SymbolTable {
	HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
	
	public void addEntry(String symbol, Integer address) {
		symbolTable.put(symbol, address);
	}
	
	public Boolean contains(String symbol) {
		return symbolTable.containsKey(symbol);
	}
}
