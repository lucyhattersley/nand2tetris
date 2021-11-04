import java.util.*;

public class Assembler {
	// HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
	SymbolTable symbolTable = new SymbolTable();
	Parser parser = new Parser();	
	Code code = new Code();
	ArrayList output = new ArrayList<String>();
	
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

		assembler.firstPass(asm);
		assembler.secondPass();
		
	}

	public String intToBin(Integer num) {
		String b = String.format("%16s", Integer.toBinaryString(num)).replace(' ', '0');
		return(b);

	}


	public void firstPass(String asm) {
		parser.initialize(asm);
		symbolTable.initialize();
		
		int lineNumber = 0;
		ArrayList<String> inputWithoutL = new ArrayList<String>(); // where we hold all commands bar L_COMMANDs

		while(parser.hasMoreCommands()) {
			parser.advance();
			
			if(parser.commandType() == "L_COMMAND") {
				symbolTable.addEntry(parser.symbol(), lineNumber);
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
					if ( symbolTable.contains(currentSymbol) ) {

						output.add(symbolTable.getAddress(currentSymbol));
					} 
					else {
						symbolTable.addEntry(parser.symbol(), symbolTableVal);
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

}
