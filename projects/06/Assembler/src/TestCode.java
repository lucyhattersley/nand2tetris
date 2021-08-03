
public class TestCode {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Code code = new Code();
		String test = code.comp("0");
		System.out.println("Code: " + test);				
		if (test == "0101010") {
			System.out.println("Pass");	
		} else {
			System.out.println("Fail");	
			
		}
	}

}
