package task1;

public class Anagrams {

	private static final String DELIMITER = " ";

	public static String reverseString(String str) {

		if ((str == null) || (str.isEmpty())) {
			return str;
		}

		String[] words = str.split(DELIMITER);
		StringBuffer reversedPhrase = new StringBuffer();
		for (String word : words) {
			reversedPhrase = reversedPhrase.append(reverseWord(word)).append(DELIMITER);
		}
		return deleteDelimiterInTheEnd(reversedPhrase) + spacesInTheEndOfPhrase(str);
	}

	private static String reverseWord(String word) {

		char[] letters = word.toCharArray();
		int countFromRightBoarder = letters.length - 1;
		int countFromLeftBoarder = 0;

		while (countFromLeftBoarder < countFromRightBoarder) {
			if (!Character.isLetter(letters[countFromLeftBoarder])) {
				countFromLeftBoarder++;
			} else {
				if (!Character.isLetter(letters[countFromRightBoarder])) {
					countFromRightBoarder--;
				}
				else {
					swapLetters(letters, countFromLeftBoarder, countFromRightBoarder);
					countFromLeftBoarder++;
					countFromRightBoarder--;
				}
			}
		}
		return new String(letters);
	}

	private static void swapLetters(char[] letters, int firstChar, int lastChar) {
		char tmp = letters[firstChar];
		letters[firstChar] = letters[lastChar];
		letters[lastChar] = tmp;
	}

	private static String deleteDelimiterInTheEnd(StringBuffer phrase) {
		if (phrase.length() > 0) {
			return phrase.toString().substring(0, phrase.length() - DELIMITER.length());
		} else
			return phrase.toString();
	}

	private static String spacesInTheEndOfPhrase(String phrase) {
		int len = phrase.length();
		char[] letters = phrase.toCharArray();
		String spaces = "";

		while ((letters[len - 1] <= ' ')) {
			len--;
			spaces += ' ';
		}
		return ((len < letters.length)) ? spaces : "";
	}

}
