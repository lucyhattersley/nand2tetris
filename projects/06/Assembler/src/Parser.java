import java.io.*;

public class Parser {
	
	private static void initialize(String filename) {
		try{
			File myFile = new File(filename);
			FileReader fr = new FileReader(myFile);
			BufferedReader br = new BufferedReader(fr);
			
			br.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
