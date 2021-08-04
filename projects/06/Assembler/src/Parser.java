import java.io.*;

public class Parser {
	
	public void initialize(String filename) {
		try{
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
				for (int i=0; i < line.length();i++) {
					char c = line.charAt(i);
					if (!finished) {
						if (c == comment) { //c, d both "//"
							finished = true; // we found a comment so stop writing to the newLine
						} else {
							newLine = newLine + c; // write the char to the newLine
						}
					}

				}
				if (!newLine.isEmpty()) {
					System.out.println(newLine); // let's see if it worked
				}
				
			}


			
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
