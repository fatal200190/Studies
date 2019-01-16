package task3SecondVariant;

import static org.junit.Assert.*;

import org.junit.Test;

public class DivisionTest {

	
	@Test
	public void testSimpleDivision() throws Exception {
		String expected =   "_4|2\n" + 
        		" 4|-\n" + 
        		" -|2\n" + 
        		" 0\n";
		Calculator div = new Calculator(0);
		div.makeADivision(4, 2);
		Printer printer = new Printer(div);
		assertEquals(expected, printer.printResult().toString());
	}
	
	@Test
	public void testSimpleDivisionWithRemain() throws Exception {
		String expected =   
				"_5|2\n" + 
        		" 4|---\n" + 
        		" -|2.5\n" + 
        		"_10\n"+
        		" 10\n"+
        		" --\n"+
        		"  0\n"
        		;
		Calculator div = new Calculator(3);
		div.makeADivision(5, 2);
		Printer printer = new Printer(div);
		assertEquals(expected, printer.printResult().toString());
	}
	
	@Test
	public void testSimpleDivisionWithRemainInPeriodSameDigits() throws Exception {
		String expected =   
				"_1 |3\n" + 
        		"  9|-----\n" + 
        		" --|0.(3)\n" + 
        		" _10\n"+
        		"   9\n"+
        		"  --\n"+
        		"  _10\n"+
        		"    9\n"+
        		"   --\n"+
        		"    1\n"
        		;
		Calculator div = new Calculator(3);
		div.makeADivision(1, 3);
		Printer printer = new Printer(div);
		assertEquals(expected, printer.printResult().toString());
	}
	
	@Test
	public void testSimpleDivisionWithRemainInPeriod() throws Exception {
		String expected =   
				"_1   |666\n" + 
        		"  666|--------\n" + 
        		" ----|0.0(015)\n" + 
        		" _3340\n"+
        		"  3330\n"+
        		"  ----\n"+
        		"   _1000\n"+
        		"     666\n"+
        		"    ----\n"+
        		"    _3340\n"+
        		"     3330\n"+
        		"     ----\n"+
        		"       10\n"
        		;
		Calculator div = new Calculator(7);
		div.makeADivision(1, 666);
		Printer printer = new Printer(div);
		assertEquals(expected, printer.printResult().toString());
	}
	
	
	@Test(expected = Exception.class)
	public void testSimpleDivisionDivisorNull() throws Exception {
		Calculator div = new Calculator(3);
		div.makeADivision(1, 0);
		Printer printer = new Printer(div);
		printer.printResult();
	}
	
	@Test(expected = Exception.class)
	public void testSimpleDividendDivisorNull() throws Exception {
		Calculator div = new Calculator(3);
		div.makeADivision(0, 1);
		Printer printer = new Printer(div);
		printer.printResult();
	}
}
