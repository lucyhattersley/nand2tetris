import java.util.*;

public class Assembler {
	HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();

	Parser parser = new Parser();	
	Code code = new Code();
	ArrayList output = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Assembler assembler = new Assembler();
		assembler.initialize();
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
	
	public void secondPass() {

		while( parser.hasMoreCommands() ) {
			parser.advance();
			Integer symbolTableVal = 16;
			
			if (parser.commandType() == "A_COMMAND") {
				String currentSymbol = parser.symbol();

				if ( !parser.isNumeric() ) {
					if ( symbolTable.containsKey(currentSymbol) ) {
						output.add(parser.symbol());
					} 
					else {
						symbolTable.put(parser.symbol(), symbolTableVal);
						symbolTableVal += 1;
					}
				} 
				
				else {

					int i = Integer.parseInt(currentSymbol);
					String b = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
					
					output.add(b);	
				}

			}

			else if (parser.commandType() == "C_COMMAND") {
				
				String d = code.dest(parser.dest());
				String c = code.comp(parser.comp());
				String j = code.jump(parser.jump());

				output.add("111" + c + d + j);
			}

			else if (parser.commandType() == "L_COMMAND") {

			}

			else {
				String currentSymbol = parser.symbol();
				output.add(currentSymbol);
			}
		}

		// Parse each line

		// Where A-instruction -> look up number in symbol table
			// if found replace with numeric meaning and translate

			// if not found add to to Symbol table and translate
			// RAM Numbers start at address 16

		// C_Command?
		// L_Command?

		// OUTPUT to filename.hack? Here or in separate function

		
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
