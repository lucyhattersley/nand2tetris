import java.io.*;

public class Parser {
	
	public void initialize(String filename) {
		try{
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			
			// Test code to print contents of file
			String line = null;
			while ((line = br.readLine()) != null) {
				// System.out.println(line);

				// now let's remove comments
				for (char c : line.toCharArray()) {
					System.out.print(Character.toString(c));
				}
			}


			
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
