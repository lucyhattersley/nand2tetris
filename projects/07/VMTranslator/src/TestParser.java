import junit.framework.*;
import org.junit.Test;
import org.junit.jupiter.api.TestClassOrder;

import java.util.*;

public class TestParser extends TestCase  {

    // WSL Linux
    String testFile = "/home/lucy/nand2tetris/projects/07/StackArithmetic/SimpleAdd/SimpleAdd.vm";
    Parser myParser = new Parser();

    @Test
	public void testInput() {
        myParser.initialize(testFile);
        ArrayList<String> testInput = new ArrayList<String>(Arrays.asList("push constant 7","push constant 8", "add"));
        assertEquals(testInput, myParser.input);

    }

    @Test
    public void testHasMoreCommands() {
        myParser.initialize(testFile);
        assertTrue(myParser.hasMoreCommands());
    }

    @Test
    public void testCurrentCommand() {
        myParser.initialize(testFile);
        myParser.advance();
        String testInput = "push constant 7";
        assertEquals(testInput, myParser.getCurrentCommand());
    }

    @Test
    public void testCommandPush() {
        myParser.initialize(testFile);
        myParser.advance();
        String testCommandType = "C_PUSH";
        assertEquals(testCommandType, myParser.commandType());
    }

    @Test
    public void testCommandPull() {
        myParser.initialize(testFile);
        myParser.currentCommand = "pull arg1 arg2";
        String testCommandType = "C_PULL";
        assertEquals(testCommandType, myParser.commandType());

    }

    @Test
    public void testCommandArithmetic() {
        myParser.currentCommand = "add arg1 arg2";
        String testCommandAdd = "C_ARITHMETIC";
        assertEquals(testCommandAdd, myParser.commandType());

        myParser.currentCommand = "sub arg1 arg2";
        String testCommandSub = "C_ARITHMETIC";
        assertEquals(testCommandSub, myParser.commandType());

        myParser.currentCommand = "neg arg1 arg2";
        String testCommandNeg = "C_ARITHMETIC";
        assertEquals(testCommandNeg, myParser.commandType());

    }

    @Test
    public void testArg1() {
        myParser.currentCommand = "push constant 7";
        String testArg1Constant = "constant";
        assertEquals(testArg1Constant, myParser.arg1());

        myParser.currentCommand = "add";
        String testArg1Add = "add";
        assertEquals(testArg1Add, myParser.arg1());


    }

    @Test
    public void testArg2() {
        myParser.currentCommand = "push constant 7";
        String testArg2Constant = "7";
        assertEquals(testArg2Constant, myParser.arg2());

    }

    // TODO Chapter 8
    // @Test 
    // public void testCommandLabel() {
    //     myParser.currentCommand = "add arg1 arg2";
    //     String testCommandAdd = "C_ARITHMETIC";
    //     assertEquals(testCommandAdd, myParser.commandType());

    // }


}