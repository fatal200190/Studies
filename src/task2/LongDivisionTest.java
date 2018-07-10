package task2;

import static org.junit.Assert.*;

import org.junit.Test;

public class LongDivisionTest {
	
	private LongDivision div = new LongDivision();

	@Test
	public void testForNullDividend() {
		assertEquals("0",div.makeDivision(0, 2));
	}
	
	@Test
	public void testForNullDivisor() {
		assertEquals(null,div.makeDivision(2, 0));
	}
	
	 @Test
	    public void viewOfResultOfMakingDivision() {
	        String expected =   "_4|2\n" + 
	        		" 4|-\n" + 
	        		" -|2\n" + 
	        		" 0\n";
	        assertEquals(expected, div.makeDivision(4, 2));
	    }

}
