import java.util.*;

public class Assembler {
	HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
	Parser parser = new Parser();	
	Code code = new Code();
	
	public static void main(String[] args) {
		
		Assembler assembler = new Assembler();
		String asm = new String();
		if(args.length >= 0) {
			asm = args[0];

			assembler.firstPass(asm);			
			// assembler.secondPass(asm);

		} else {
			System.out.println("Missing argument: ASM file");
		}
		
	}

	public void firstPass(String asm) {
		parser.initialize(asm);
		
		int lineNumber = 0;
		ArrayList<String> inputWithoutL = new ArrayList<String>(); // where we hold all commands bar L_COMMANDs

		while(parser.hasMoreCommands()) {
			parser.advance();
			
			if(parser.commandType() == "L_COMMAND") {
				symbolTable.put(parser.symbol(), lineNumber);
			} else {
				inputWithoutL.add(parser.currentCommand);
				lineNumber += 1;
			}
		}
		// parser.input.clear();
		parser.input.addAll(inputWithoutL);
	}
	
	public void secondPass(String asm) {
		//TODO
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
