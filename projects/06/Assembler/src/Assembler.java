import java.util.HashMap;

import jdk.tools.jaotc.binformat.SymbolTable;

public class Assembler {
	HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
	
	public static void main(String[] args) {
		
		Parser parser = new Parser();
		Code code = new Code();
		SymbolTable symbolTable = new SymbolTable();
		symbolTable.initalize();
		
		// FIRST PASS
        // We loop through input stream and add L_COMMANDS and binary index to Symbol Table
        // First pass through input file. Remove L_COMMANDS and add Variables to symbol_table
		ArrayList inputDuplication = new ArrayList();
		
		while(parser.hasMoreCommands()) {
			parser.advance();
		}

		if(parser.commandType == "L_COMMAND") {
			Integer listSize = inputDuplication.size();
			symbolTable.put(parser.symbol(), Integer.toBinaryString(listSize));
		}

		// TODO
		// SECOND PASS
		// Parse each ASM command to corrosponding HACK command
		
		
		

	}
	
	public void initialize() {
		symbolTable.put("SP", 0);
		symbolTable.put("LCL", 1);
		symbolTable.put("ARG", 2);
		symbolTable.put("THIS", 3);
		symbolTable.put("THAT", 4);
		symbolTable.put("R0", 0);
		symbolTable.put("R1", 1);
		symbolTable.put("R2", 2);
		symbolTable.put("R3", 3);
		symbolTable.put("R4", 4);
		symbolTable.put("R5", 5);
		symbolTable.put("R6", 6);
		symbolTable.put("R7", 7);
		symbolTable.put("R8", 8);
		symbolTable.put("R9", 9);
		symbolTable.put("R10", 10);
		symbolTable.put("R11", 11);
		symbolTable.put("R12", 12);
		symbolTable.put("R13", 13);
		symbolTable.put("R14", 14);
		symbolTable.put("R15", 15);
		symbolTable.put("SCREEN", 16384);
		symbolTable.put("KBD", 24756);

	}

}
