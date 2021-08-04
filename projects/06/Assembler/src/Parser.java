import java.io.*;
import java.util.ArrayList;

public class Parser {
	ArrayList<String> input = new ArrayList<String>(); // where we hold our commands

	public void initialize(String filename) {
		try {
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);

			// Test code to print contents of file
			String line = null;
			char comment = '/';

			while ((line = br.readLine()) != null) {
				boolean finished = false;
				String newLine = "";

				// now let's remove comments
				for (int i = 0; i < line.length(); i++) {
					char c = line.charAt(i);
					if (!finished) {
						if (c == comment) { // we found a comment
							finished = true; // stop writing to the newLine
						} else {
							newLine = newLine + c; // write the char to the newLine
						}
					}
				}

				// Add ASM code to input
				if (!newLine.isEmpty()) {
					input.add(newLine);
				}
			}

			br.close(); // close the buffered reader now we're done

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

}
