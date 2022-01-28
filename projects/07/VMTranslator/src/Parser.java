import java.io.*;
import java.util.*;

public class Parser {

    // NOTE. Code repurposed from Parser.java (Project 06)
    ArrayList<String> input = new ArrayList<String>(); // where we hold our commands
	public String currentCommand;
	// public Code myCode = new Code(); 

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
				//newLine = newLine.replaceAll(" ", "");

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

	public boolean hasMoreCommands() {
		return (input.size() > 0);
	}

	public void advance() {
		if (hasMoreCommands()) {
			currentCommand = input.remove(0);
		}
		
	}

	public String commandType() {
	String[] commands = currentCommand.split(" ");
	String command = commands[0];

	switch (command) {
		case "push": return "C_PUSH";
		case "pull": return "C_PULL";
		case "add":
		case "sub":
		case "neg":
				   return "C_ARITHMETIC";
		default: return "";
		}
	}

	/************************************************************************** 
	Returns the first argument of currentCommand.
	IE: returns constant if currentCommand "push constant 7"
	Returns first constant for C_ARITHMATIC commandType, ie 'add'
	Should not be called if commandType C_RETURN

	@param none (uses currentCommand instance variable)
	@return arg1 the first argument of currentCommand
	**************************************************************************/
	public String arg1() {
		String command;

		if (commandType() == "C_PUSH" || commandType() == "C_PULL") {
			String[] commands = currentCommand.split(" ");
			command = commands[1];
			
		} else if (commandType() == "C_ARITHMETIC") {
			String[] commands = currentCommand.split(" ");
			command = commands[0];
		} else {
			command = "";
		}
		
		return command;
	}

	public String getCurrentCommand() {
		return currentCommand;
	}

}
