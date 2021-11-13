import java.util.*;
import java.io.*;
import java.nio.file.Path;

public class Assembler {
	// HashMap<String, Integer> symbolTable = new HashMap<String, Integer>();
	SymbolTable symbolTable = new SymbolTable();
	Parser parser = new Parser();	
	Code code = new Code();
	ArrayList<String> output = new ArrayList<String>();
	
	public static void main(String[] args) {
		
		Assembler assembler = new Assembler();
		
		String asm = new String();
		if(args.length >= 0) {
			asm = args[0];

			assembler.firstPass(asm);			
			assembler.secondPass();
			// assembler.output()

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

	public String stringToBin(String num) {
		Integer i = Integer.parseInt(num);
		String b = String.format("%16s", Integer.toBinaryString(i)).replace(' ', '0');
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
			
			if( parser.commandType() == "A_COMMAND" ) {
				String currentSymbol = parser.symbol();

				if ( !parser.isNumeric() ) {
					if ( symbolTable.contains(currentSymbol) ) {
						Integer key = symbolTable.getAddress(currentSymbol);
						String binary = intToBin(key);
						output.add(binary);
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
	}

	// Output
	public void output(String fileName) {
		String[] tokens = fileName.split("\\.(?=[^\\.]+$)");
		String path = tokens[0];

		try {
			File file = new File(path + "1.hack");
			file.createNewFile();
			System.out.println("File" + file); 
		} catch(Exception ex) {
			ex.printStackTrace();
		}
		
		// implement FileWriter https://www.tutorialspoint.com/java-program-to-write-int-array-to-a-file
	}
		
	

}
