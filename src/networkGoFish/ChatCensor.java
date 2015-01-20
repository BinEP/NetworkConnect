package networkGoFish;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

import utilityClasses.*;

public class ChatCensor {

	public String wordFile;
	public String checkString;
	ArrayList<String> allSwearWords = new ArrayList<String>();

	public static void main(String[] args) {
		ChatCensor runIt = new ChatCensor();
		runIt.runFromMain();
	}

	public void runFromMain() {

		checkString = "Hello, how are you!";
		censorString();
	}

	public ChatCensor() {
		// TODO Auto-generated constructor stub
		getCensoredWords();
	}

	public ChatCensor(String checkString) {

		getCensoredWords();
		this.checkString = checkString;

	}

	public void setCheckString(String checkString) {

		this.checkString = checkString;

	}

	public String censorString() {
		
		String[] badWords = arrayOfSwears();
		String[] replacementWords = { "apple", "magical", "unicorn", "fairies",
				"endear", "renowned", "butterflies", "sugar", "butterscotch", "philosppher" };
		Random r = new Random();
		for (int i = 0; i < badWords.length; i++) {
			int indexOfBadWord = (checkString.toLowerCase()).indexOf(badWords[i].toLowerCase());
			if (indexOfBadWord != -1 && checkPartialWord(indexOfBadWord, badWords[i].toLowerCase(), checkString)) {
				String begin = checkString.substring(0, indexOfBadWord);
				String replacement = replacementWords[r
						.nextInt(replacementWords.length)];
				String end = checkString.substring(indexOfBadWord
						+ badWords[i].length());
				checkString = begin + replacement + end;
				i = -1;
			}
		}

		return checkString; // now holy.
	}
	
	public boolean checkPartialWord(int indexOfBadWord, String badWord, String checkString) {
		
		if (true) return true;
		if (indexOfBadWord != -1 && checkString.length() == badWord.length()) return true;
		
		if (indexOfBadWord + badWord.length() + 1 == checkString.length()) return true;
		if (checkString.charAt(indexOfBadWord + 1) != ' ') return false;
		
		
		
		
		return true;
	}
	
	public String getCensoredString() {
		
		DoubleMetaphone censorString = new DoubleMetaphone();
		String filteredString = censorString.doubleMetaphone(checkString);
		
		
		return filteredString;
	}
	
public String getCensoredString(String checkString) {
		
		DoubleMetaphone censorString = new DoubleMetaphone();
		String filteredString = censorString.doubleMetaphone(checkString);
		
		return filteredString;
		
	}

	public String[] arrayOfSwears() {

		return allSwearWords.toArray(new String[allSwearWords.size()]);

	}

	public void getCensoredWords() {

		wordFile = "swearWords.txt";
		Scanner input;
		try {
			input = new Scanner(new File(wordFile));
			while (input.hasNext()) {
				allSwearWords.add(input.next());
			}
			input.close();

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
