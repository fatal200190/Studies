package task1;

public class Anagrams {
	
	private static final String DELIMITER = " ";
	
	public static void main(String[] args) {
		String str = "abc";
		System.out.println(reverseString(str));
	}
	
	public static String reverseString (String str) {
		
		if((str == null) || (str.isEmpty())) 
		return str;
		str.trim();
		String[] words = str.split(DELIMITER);
		StringBuffer reversedPhrase = new StringBuffer();
		for(String word : words) {
			reversedPhrase = reversedPhrase.append(reverseWord(word)).append(DELIMITER);
		}
		return deleteDelimiterInTheEnd(reversedPhrase) + spacesInTheEndOfPhrase(str);
		}
	
	

	
	private static String reverseWord(String word){
		
		char [] letters = word.toCharArray();
		int countFromRightBarder = letters.length - 1; 
		int countFromLeftBoarder = 0;
		
        while (countFromLeftBoarder < countFromRightBarder){
            if (!Character.isLetter(letters[countFromLeftBoarder])) {
            	countFromLeftBoarder++;
            	}
            else {
            	if(!Character.isLetter(letters[countFromRightBarder])) {
            	countFromRightBarder--;
            	}
            	
            	else{
            	swapLetters(letters, countFromLeftBoarder, countFromRightBarder);
                countFromLeftBoarder++;
                countFromRightBarder--;
            	}
            }
        }
        return new String(letters);
    }
	
	private static void swapLetters (char [] letters, int countFromLeftBoarder, 
			int countFromRightBarder) {
        char tmp = letters[countFromLeftBoarder];
        letters[countFromLeftBoarder] = letters[countFromRightBarder];
        letters[countFromRightBarder] = tmp;
	}
	
	private static String deleteDelimiterInTheEnd (StringBuffer phrase) {
		if(phrase.length() > 0) {
		return phrase.toString().substring(0, phrase.length() - 1);
		}else 
			return phrase.toString();
	}
	
	   private static String spacesInTheEndOfPhrase(String phrase) {
	        int len = phrase.length();
	        char[] letters = phrase.toCharArray();
	        String spaces = "";

	        while ((letters[len - 1] <= ' ')) {
	            len--;
	            spaces+=' ';
	        }
	        return ((len < letters.length)) ? spaces : "";
	    }

}
