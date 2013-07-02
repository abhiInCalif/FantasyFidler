package com.machine.main;

public class NameVector
{
	private int numberOfVowels;
	private int numberOfLetters;
	private int asciiValue;
	private int ending;
	private int[] doubleLetters;
	private int[] occuranceOfLetter;
	private String label;
	private String s;
	
	/**
	 * @return the label
	 */
	public String getLabel() {
		return label;
	}

	/**
	 * @param label the label to set
	 */
	public void setLabel(String label) {
		this.label = label;
	}

	public NameVector()
	{
		this.doubleLetters = new int[26]; 
	}
	
	/**
	 * @return the numberOfVowels
	 */
	public int getNumberOfVowels() {
		return numberOfVowels;
	}

	/**
	 * @param numberOfVowels the numberOfVowels to set
	 */
	public void setNumberOfVowels(int numberOfVowels) {
		this.numberOfVowels = numberOfVowels;
	}

	/**
	 * @return the numberOfLetters
	 */
	public int getNumberOfLetters() {
		return numberOfLetters;
	}

	/**
	 * @param numberOfLetters the numberOfLetters to set
	 */
	public void setNumberOfLetters(int numberOfLetters) {
		this.numberOfLetters = numberOfLetters;
	}

	/**
	 * @return the asciiValue
	 */
	public int getAsciiValue() {
		return asciiValue;
	}

	/**
	 * @param asciiValue the asciiValue to set
	 */
	public void setAsciiValue(int asciiValue) {
		this.asciiValue = asciiValue;
	}

	/**
	 * @return the ending
	 */
	public int getEnding() {
		return ending;
	}

	/**
	 * @param ending the ending to set
	 */
	public void setEnding(int ending) {
		this.ending = ending;
	}

	/**
	 * @return the doubleLetters
	 */
	public int[] getDoubleLetters() {
		return doubleLetters;
	}

	/**
	 * @param doubleLetters the doubleLetters to set
	 */
	public void setDoubleLetters(int[] doubleLetters) {
		this.doubleLetters = doubleLetters;
	}

	public boolean setVector(int vowels, int letters, int ascii, int ending, int[] doubles, int[] occurance)
	{
		this.numberOfLetters = letters;
		this.numberOfVowels = vowels;
		this.asciiValue = ascii;
		this.ending = ending;
		this.doubleLetters = doubles;
		this.occuranceOfLetter = occurance;
		return true;
	}
	
	public int calculateAscii(String firstName, String lastName)
	{
		int value = 0;
		char[] first = firstName.toCharArray();
		char[] last = lastName.toCharArray();
		
		for(int i = 0; i < first.length; i++)
		{
			value += (int) first[i];
		}
		for(int j = 0; j < last.length; j++)
		{
			value += (int) last[j];
		}
		
		return value;
	}
	
	public int calculuateLength(String s)
	{
		return s.length();
	}
	
	public int calculateVowels(String s)
	{
		int vowelCount = 0;
		char[] vowels = new char[5];
		vowels[0] = 'a';
		vowels[1] = 'e';
		vowels[2] = 'i';
		vowels[3] = 'o';
		vowels[4] = 'u';
		
		for(int i = 0 ; i < s.length(); i++)
		{
			for(int j = 0; j < vowels.length; j++)
			{
				if(s.toCharArray()[i] == vowels[j]) vowelCount++;
			}
		}
		
		return vowelCount;
	}
	
	public int[] calculateOccurance(String s)
	{
		char[] c = s.trim().toLowerCase().toCharArray();
		int[] occurance = new int[26];
		for(int i = 0; i < c.length; i++)
		{
			if(c[i] - 97 < 26 && c[i] - 97 > 0)
				occurance[c[i] - 97] += 1;
		}
		
		return occurance;
	}
	
	public int[] calculateDoubles(String s)
	{
		int prevValue = 0;
		s = s.toLowerCase();
		char[] c = s.toCharArray();
		int[] doubles = new int[26];
		
		for(int i = 0; i < s.length(); i++)
		{
			if(prevValue == c[i] && c[i] != '?') doubles[c[i] - 97] = 1;
			prevValue = c[i];
		}
		
		return doubles;
	}
	
	public void createVector(String firstName, String lastName)
	{
		int ascii;
		int vowels;
		int[] doubles;
		int[] occurance;
		int ending;
		String s = firstName +" " + lastName;
		this.s = s;
		ascii = this.calculateAscii(firstName, lastName);
		vowels = this.calculateVowels(s);
		doubles = this.calculateDoubles(s);
		occurance = this.calculateOccurance(s);
		ending = s.toCharArray()[s.length() - 1];
		
		this.setVector(vowels, s.length(), ascii, ending, doubles, occurance);
	}
	
	/**
	 * @return the s
	 */
	public String getS() {
		return s;
	}

	/**
	 * @param s the s to set
	 */
	public void setS(String s) {
		this.s = s;
	}

	public static double computeDistance(NameVector v1, NameVector v2)
	{
		int squaredMagnitude = 0;
		double distance = 0;
		
		squaredMagnitude += (v1.getNumberOfLetters() - v2.getNumberOfLetters()) * (v1.getNumberOfLetters() - v2.getNumberOfLetters());
		squaredMagnitude += (v1.getEnding() - v2.getEnding()) * (v1.getEnding() - v2.getEnding());
		for(int i = 0; i < v1.getDoubleLetters().length; i++)
		{
			squaredMagnitude += i*i*(v1.getDoubleLetters()[i] - v2.getDoubleLetters()[i]) * (v1.getDoubleLetters()[i] - v2.getDoubleLetters()[i]);
		}
		for(int i = 0; i < v1.getOccuranceOfLetter().length; i++)
		{
			squaredMagnitude += (v1.getOccuranceOfLetter()[i] - v2.getOccuranceOfLetter()[i]) * (v1.getOccuranceOfLetter()[i] - v2.getOccuranceOfLetter()[i]);
		}
		
		distance = Math.sqrt((double) squaredMagnitude); 
		return distance;
	}

	/**
	 * @param occuranceOfLetter the occuranceOfLetter to set
	 */
	public void setOccuranceOfLetter(int[] occuranceOfLetter) {
		this.occuranceOfLetter = occuranceOfLetter;
	}

	/**
	 * @return the occuranceOfLetter
	 */
	public int[] getOccuranceOfLetter() {
		return occuranceOfLetter;
	}
}
