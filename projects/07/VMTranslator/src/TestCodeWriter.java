import junit.framework.*;
import org.junit.Test;
import java.util.*;
import java.nio.file.*; 

public class TestCodeWriter extends TestCase  {
    String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.asm";
    CodeWriter myCodeWriter = new CodeWriter();

    @Test
	public void testConstructor() {
		myCodeWriter.Constructor(testFile);
        Path path = Paths.get(testFile);
        assertTrue(Files.exists(path)); // Check asm file exists

    }

    @Test
    public void setFileName() {
        String testFile = "SimpleAdd.vm";
        myCodeWriter.setFileName(testFile);
        assertEquals(myCodeWriter.fileName.toString(), testFile);
    }



}