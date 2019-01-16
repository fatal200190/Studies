package task1;

import static org.junit.Assert.*;

import org.junit.Test;

public class AnagramsTest {

	@Test
	public void testLetters() {
		String result = Anagrams.reverseString("abc");
		assertEquals("cba",result);
	}
	
	@Test
	public void testLettersAndNonletters() {
		String result = Anagrams.reverseString("a1bc    d'efg2 ,2-");
		assertEquals("c1ba    g'fed2 ,2-",result);
	}
	
	@Test
	public void testNonLetters() {
		String result = Anagrams.reverseString(",2- 482");
		assertEquals(",2- 482",result);
	}

	@Test
	public void testNull() {
		String result = Anagrams.reverseString(null);
		assertEquals(null,result);
	}
	
	@Test
	public void testEmptyString() {
		String result = Anagrams.reverseString("");
		assertEquals("",result);
	}
	
	@Test
	public void testSpacesAtBeginAndInTheEnd() {
		String result = Anagrams.reverseString("   a1bc    d'efg2 ,2-   ");
		assertEquals("   c1ba    g'fed2 ,2-   ",result);
	}
}
