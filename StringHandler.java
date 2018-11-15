

/*
 * This class is used to analyze strings and extract information from them
 */
public class StringHandler {
	
	
	/*--- Methods ---*/

	/*
	 * Counts amount of words in a string
	 */
	public static int wordCount(String s){

		int count = 1;

		for (int i = 0; i < s.length() - 1; i++)
		{

			//Adds to count if new word detected
			if ((s.charAt(i) == ' ') && (s.charAt(i + 1) != ' '))
			{
				count++;

			}
		}

		return count;
	}

	/*
	 * Extract a word from a string. 
	 * The word extracted is the indexOfWord:th word.
	 * If indexOfWord exceeds number of words in string, an empty string is returned
	 * To extract first word, set indexOfWord = 1.
	 */
	public static String extractWordFromString(String s,int indexOfWord){

		int count = 1;

		String result = "";

		//Adding space to end of string s,
		//so that last char of last word is identified as a part of result
		s = s + " ";
		
		for (int i = 0; i < s.length() - 1; i++)
		{

			//Adds to count if new word detected
			if ((s.charAt(i) == ' ') && (s.charAt(i + 1) != ' '))
			{
				count++;

			}

			//If currently looking at indexOfWord:th word, add each non-space character to result 
			if(count == indexOfWord && s.charAt(i) != ' '){
				result = result + s.charAt(i); 
			}
		}

		return result;
	}


	/*
	 * Checks if a string contains non-numeric characters.
	 * Note: Spaces are considered as valid characters.
	 */
	public static boolean isStringNumeric(String s)
	{
		//Converts string to characters
		char[] charArray = s.toCharArray();

		//Checks if alphanumerical or space for each character
		for(char c:charArray){
			if (!Character.isDigit(c) && c != ' '){
			
				return false;
			}
		}
		return true;
	}

	/*
	 * Checks if a string contains non-alphabetic characters.
	 * Note: Spaces are considered as valid characters.
	 */
	public static boolean isStringAlphabetic(String s)
	{
		//Converts string to characters
		char[] charArray = s.toCharArray();

		//Checks if alphanumerical or space for each character
		for(char c:charArray){
			if (!Character.isLetter(c) && c != ' '){
			
				return false;
			}
		}
		return true;
	}
	
	
	/*
	 * Checks if a string contains non-alphanumeric characters.
	 * Note: Spaces are considered as valid characters.
	 */
	public static boolean isStringAlphanumeric(String s)
	{
		//Converts string to characters
		char[] charArray = s.toCharArray();

		//Checks if alphanumerical or space for each character
		for(char c:charArray){
			if (!Character.isLetterOrDigit(c) && c != ' '){
			
				return false;
			}
		}
		return true;
	}

	

}
