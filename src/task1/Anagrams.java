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
		String[] words = str.split(" ");
		String revString = "";
		String temp = "";
		for(String word : words) {
			temp = reverseWord(word);
			revString = revString + temp + " ";
			temp = "";
		}
		return revString.trim();
	}
	
	public static String reverseWord(String word){
		char [] letters = word.toCharArray();
		int r = letters.length - 1, l = 0;
        while (l < r){
            if (!Character.isLetter(letters[l]))
                l++;
            else if(!Character.isLetter(letters[r]))
                r--;
            else{
                char tmp = letters[l];
                letters[l] = letters[r];
                letters[r] = tmp;
                l++;
                r--;
            }
        }
        return new String(letters);
    }
}
