import java.io.*;
import java.util.ArrayList;

public class Parser {
	ArrayList<String> input = new ArrayList<String>(); // where we hold our commands
	public String currentCommand;
	public Code myCode = new Code(); 

	public void initialize(String filename) {

		// First we open the file
		try {
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);

			// Sanitise file so we only have ASM code (not comments)
			String line = null;
			char comment = '/';

			// Loop over line to remove any comments
			while ((line = br.readLine()) != null) {
				boolean finished = false;
				String newLine = "";

				// now let's remove comments
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (!finished) {
						if (c == comment) { // we found a comment so...
							finished = true; // we stop writing chars to newLine
							break;
						} else {
							newLine = newLine + c; // write the ASM char to newLine
						}
					}
				}

				// Remove whitespace
				newLine = newLine.replaceAll(" ", "");

				// Now add newLine of ASM code to our input
				if (!newLine.isEmpty()) {
					input.add(newLine);
				}
			}

			br.close(); // close the buffered reader now we're done. Input contains pure ASM code

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// Test method to see what's inside input
	public void printInput() {
		for (String command : input) {
			System.out.println(command);
		}
	}

	public boolean hasMoreCommands() {
		return !input.isEmpty();
	}
	
	public void advance() {
		currentCommand = input.remove(0);
		
	}
	
	public String commandType() {
		/*
		 * Returns:
		 *  A_COMMAND
		 *  C_COMMMAND
		 *  L_COMMAND
		 */
		
		if( currentCommand.startsWith("@") ) {
			return("A_COMMAND");
		} else if(currentCommand.contains("=") || currentCommand.contains(";")) {
			return("C_COMMAND");
		} else {
			return("L_COMMAND");
		}
	}
	
	public String symbol() {
		if( this.commandType() == "A_COMMAND" ) {
			return currentCommand.substring(1);
		} else if (this.commandType() == "L_COMMAND") {
			return currentCommand.substring(1, currentCommand.length() - 1);
		} else {
			return ""; // failsafe
		}

	}

	public String getCurrent() {
		return currentCommand;
	}


	public boolean isNumeric() {
	// From https://www.baeldung.com/java-check-string-number
	// Outputs true if currentCommand.symbol is numeric. Otherwise false 
	// Output: Bool
		if ( this.symbol() == null ) {
			return false;
		}
		try {
			double d = Double.parseDouble( this.symbol() );
		} catch ( NumberFormatException nfe ) {
			return false;
		}
		return true;
	}
	
	public String dest() {
		if (this.commandType() == "C_COMMAND") {
			if (currentCommand.contains("=")) {
				String[] commands = currentCommand.split("=");
				return commands[0];	
			} else if (currentCommand.contains(";")) {
				String[] commands = currentCommand.split("=");
				return commands[0];	
			}
		}
		return ""; // failsafe
	}
	
	// This not working for D;JGT command (returning D;JGT not in code table (so returning 000))
	public String comp() {
		if (this.commandType() == "C_COMMAND")  {
			if (currentCommand.contains(";")) {
				String[] commands = currentCommand.split(";");
				return commands[0];	
			}
			else if (currentCommand.contains("=")) {
				String[] commands = currentCommand.split("=");
				return commands[1];	

			}
		}
		return "";
	}
	
	public String jump() {
		if (this.commandType() == "C_COMMAND") {
			String[] commands = currentCommand.split(";");
			return commands[commands.length-1];

		}
		return "";
	}
		

}
