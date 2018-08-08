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
		Calculate div = new Calculate(4,2,0);
		div.division();
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
		Calculate div = new Calculate(5,2,3);
		div.division();
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
		Calculate div = new Calculate(1,3,3);
		div.division();
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
		Calculate div = new Calculate(1,666,7);
		div.division();
		Printer printer = new Printer(div);
		assertEquals(expected, printer.printResult().toString());
	}
	
	
	@Test(expected = Exception.class)
	public void testSimpleDivisionDivisorNull() throws Exception {
		Calculate div = new Calculate(1,0,3);
		div.division();
		Printer printer = new Printer(div);
		printer.printResult();
	}
	
	@Test(expected = Exception.class)
	public void testSimpleDividendDivisorNull() throws Exception {
		Calculate div = new Calculate(0,1,3);
		div.division();
		Printer printer = new Printer(div);
		printer.printResult();
	}
}
