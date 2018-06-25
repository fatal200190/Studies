package task1;

public class Anagrams {
	
	private static final String DELIMITER = " ";
	
	public static void main(String[] args) {
		String str = "               ";
		System.out.println(reverseString(str));
	}
	
	public static String reverseString (String str) {
		
		String[] words = str.split(DELIMITER);
		StringBuffer reversedPhrase = new StringBuffer();

		if((str == null) || (str.isEmpty())) {
		return str;
		}else {
		
		for(String word : words) {
			reversedPhrase = reversedPhrase.append(reverseWord(word)).append(DELIMITER);
		}
		return deleteDelimiterInTheEnd(reversedPhrase);
		}
	}
	
	private static String reverseWord(String word){
		
		char [] letters = word.toCharArray();
		int countFromRight = letters.length - 1; 
		int countFromLeft = 0;
		
        while (countFromLeft < countFromRight){
            if (!Character.isLetter(letters[countFromLeft])) {
            	countFromLeft++;
            	}
            else {
            	if(!Character.isLetter(letters[countFromRight])) {
            	countFromRight--;
            	}
            	else{
                char tmp = letters[countFromLeft];
                letters[countFromLeft] = letters[countFromRight];
                letters[countFromRight] = tmp;
                countFromLeft++;
                countFromRight--;
            	}
            }
        }
        return new String(letters);
    }
	
	private static String deleteDelimiterInTheEnd (StringBuffer phrase) {
		if(phrase.length() > 0) {
		return phrase.toString().substring(0, phrase.length() - 1);
		}else 
			return phrase.toString();
	}
}
