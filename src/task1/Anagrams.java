package task1;

public class Anagrams {

	public static void main(String[] args) throws Exception {
		String str = "a1bcd efg!h";
		System.out.println(reverseString(str));
	}
	
	public static String reverseString (String str) throws Exception {
		if (str == null) {
				throw new Exception("String can not be null");
			}
		if(str.isEmpty()) {
			return str;
		}
		final String[] WORDS = str.split(" ");
		String revString = "";
		String temp = "";
		for(String word : WORDS) {
			temp = reverseWord(word);
			revString = revString + temp + " ";
			temp = "";
		}
		return revString.trim();
	}
	
	private static String reverseWord(String word){
		
		char [] letters = word.toCharArray();
		int countFromRight = letters.length - 1; 
		int countFromLeft = 0;
		
        while (countFromLeft < countFromRight){
            if (!Character.isLetter(letters[countFromLeft]))
            	countFromLeft++;
            else if(!Character.isLetter(letters[countFromRight]))
            	countFromRight--;
            else{
                char tmp = letters[countFromLeft];
                letters[countFromLeft] = letters[countFromRight];
                letters[countFromRight] = tmp;
                countFromLeft++;
                countFromRight--;
            }
        }
        return new String(letters);
    }
}
