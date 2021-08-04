import java.io.*;

public class Parser {
	
	public void initialize(String filename) {
		try{
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			
			String line = null;
			while ((line = br.readLine()) != null) {
				System.out.println(line);
			}
			
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
