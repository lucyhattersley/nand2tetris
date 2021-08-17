import java.io.*;
import java.util.ArrayList;

public class Parser {
	ArrayList<String> input = new ArrayList<String>(); // where we hold our commands
	String currentCommand;

	public void initialize(String filename) {
		// First we open the file
		try {
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);

			// Sanatize file so we only have ASM code (not comments)
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
						} else {
							newLine = newLine + c; // write the ASM char to newLine
						}
					}
				}

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
	
	public void commandType() {
		/*
		 * Returns:
		 *  A_COMMAND
		 *  C_COMMMAND
		 *  L_COMMAND
		 */
	}
	
	

}
