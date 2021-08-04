import java.io.*;

public class Parser {
	
	public void initialize(String filename) {
		try{
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			
			// Test code to print contents of file
			String line = null;
			String newLine = null;
			char comment = '/';

			while ((line = br.readLine()) != null) {
				boolean finished = false;

				// now let's remove comments
				for (int i=0; i < line.length()-1;i++) {
					char c = line.charAt(i);
					char d = line.charAt(i+1); 
					if (!finished) {
						if (c == comment && d == comment) {
							finished = true; // we found a comment so stop writing to the newLine
							break;
						} else {
							newLine += Character.toString(c); // write the char to the newLine
						}
					}

				}
				
				System.out.println(newLine); // let's see if it worked
			}


			
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
