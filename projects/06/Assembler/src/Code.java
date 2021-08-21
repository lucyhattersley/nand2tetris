import java.util.*;

public class Code {
    private  static Hashtable<String, String> c_table = 
            new Hashtable<String, String>();

    private  static Hashtable<String, String> d_table = 
            new Hashtable<String, String>();
    
    private  static Hashtable<String, String> j_table = 
            new Hashtable<String, String>();

    static {
	    c_table.put("0","0101010");
	    c_table.put("1","0111111");
	    c_table.put("-1","0111010");
	    c_table.put("D","0001100");
	    c_table.put("A","0110000");
	    c_table.put("M","1110000");
	    c_table.put("!D","0001101");
	    c_table.put("!A","0110001");
	    c_table.put("!M","1110001");
	    c_table.put("-D","0001111");
	    c_table.put("-A","0110011");
	    c_table.put("-M","1110011");
	    c_table.put("D+1","0011111");
	    c_table.put("A+1","0110111");
	    c_table.put("M+1","1110111");
	    c_table.put("D-1","0001110");
	    c_table.put("A-1","0110010");
	    c_table.put("M-1","1110010");
	    c_table.put("D+A","0000010");
	    c_table.put("D+M","1000010");
	    c_table.put("D-A","0010011");
	    c_table.put("D-M","1010011");
	    c_table.put("A-D","0000111");
	    c_table.put("M-D","1000111");
	    c_table.put("D&A","0000000");
	    c_table.put("D&M","1000000");
	    c_table.put("D|A","0010101");
	    c_table.put("D|M","1010101");
    
    }
    
    static {
    	d_table.put("null","000");
    	d_table.put("M","001");
    	d_table.put("D","010");
    	d_table.put("MD","011");
    	d_table.put("A","100");
    	d_table.put("AM","101");
    	d_table.put("AD","110");
    	d_table.put("AMD","111");
    }
    
    static {
    	j_table.put("null","000");
    	j_table.put("JGT","001");
    	j_table.put("JEQ","010");
    	j_table.put("JGE","011");
    	j_table.put("JLT","100");
    	j_table.put("JNE","101");
    	j_table.put("JLE","110");
    	j_table.put("JMP","111");
    }

    public String comp(String mnem) {    	
    	return c_table.get(mnem);
    }

    public String dest(String mnem) {
    	return d_table.get(mnem);
    }

    public String jump(String mnem) {
    	return j_table.get(mnem);
    }
 
}