package mkapil.date;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;

/**
 * This static class is used to generate an English worded
 * numerical value of an Integer
 * @author kapilm
 * @since  2016-07-19
 */
public class NumberInWords {

	private static final Map<Integer, String> teenMapping;
	private static final Map<Integer, String> tens;
	private static final String[] bigValue;
	public static final String invalidReturn = "NA";

	static
	{
		teenMapping = new HashMap<Integer, String>();
		tens		  = new HashMap<Integer, String>();

		teenMapping.put(1, "one");
		teenMapping.put(2, "two");
		teenMapping.put(3, "three");
		teenMapping.put(4, "four");
		teenMapping.put(5, "five");
		teenMapping.put(6, "six");
		teenMapping.put(7, "seven");
		teenMapping.put(8, "eight");
		teenMapping.put(9, "nine");
		teenMapping.put(10, "ten");
		teenMapping.put(11, "eleven");
		teenMapping.put(12, "twelve");
		teenMapping.put(13, "thirteen");
		teenMapping.put(14, "fourteen");
		teenMapping.put(15, "fifteen");
		teenMapping.put(16, "sixteen");
		teenMapping.put(17, "seventeen");
		teenMapping.put(18, "eighteen");
		teenMapping.put(19, "nineteen");
		teenMapping.put(00, "");

		tens.put(2, "twenty");
		tens.put(3, "thirty");
		tens.put(4, "forty");
		tens.put(5, "fifty");
		tens.put(6, "sixty");
		tens.put(7, "seventy");
		tens.put(8, "eighty");
		tens.put(9, "ninety");

		bigValue = new String [] {"-thousand-", "-million-", "-billion-"};
	}

	/**
	 * This method should be called by an external API to generate
	 * the English worded String value of a number
	 * @param number
	 * @return String
	 */
	public static String numberConversiontoEnglish(Integer number){
		
		if(number==null){
			return invalidReturn;
		}
		
		String numberString = "";	
		
		try{
			numberString = number.toString();
			numberString = StringUtils.deleteWhitespace(numberString);
		} catch(Exception e){}
		
		if(!NumberUtils.isNumber(numberString)){
			//do appropiate logging using log4j or print statement to the console like System.out.println("Invalid number "+number);
			return invalidReturn;
		}

		StringBuilder word = new StringBuilder();
		boolean negative = false;

		if(number==0){
			return "zero";
		}

		if(number<0){
			negative = true;
			number = Math.abs(number);
		}

		int i = -1;

		while(number>0){

			int lastThreeDigits = number % 1000;
			String conversion = convertThreeDigits(lastThreeDigits);

			if(i>=0){
				if(lastThreeDigits!=0)
					conversion+=(bigValue[i]);
			}

			word.insert(0, conversion);
			i++;
			number/=1000;
		}

		if(negative){
			word.insert(0, "minus-");
		}

		String englishWord = new String(word);

		//if any "-" at the end then we take that off
		if(englishWord.length()>1 && englishWord.charAt(englishWord.length()-1)=="-".charAt(0)){
			englishWord = englishWord.substring(0,englishWord.length()-1);		  
		}

		return englishWord;
	}

	/**
	 * This method is used to generate English wording of only 3 digits
	 * @param lastThreeDigits
	 * @return String
	 */
	private static String convertThreeDigits(int lastThreeDigits) {

		//if teen digit then just return
		if(teenMapping.containsKey(lastThreeDigits)){
			return teenMapping.get(lastThreeDigits);
		}

		String num = lastThreeDigits+"";
		String word ="";

		//true if 3 digit else false for 2 digit
		boolean threeDigit = num.length()==3?true:false;

		if(threeDigit){

			Integer hundredPositionNumber = Integer.valueOf(num.charAt(0)+"");
			word+=(teenMapping.get(hundredPositionNumber)+"-hundred-");
		}

		Integer tenPositionNumber = Integer.valueOf(num.charAt(num.length()-2)+"");
		Integer onePositionNumber = Integer.valueOf(num.charAt(num.length()-1)+"");
		Integer twoDigitsTogether = tenPositionNumber*10 + onePositionNumber;


		if(teenMapping.containsKey(twoDigitsTogether)){

			String desc = teenMapping.get(twoDigitsTogether);

			if(desc.equals("")){
				word = word.substring(0, word.length()-1);
			}

			else word+=desc;
			return new String(word);
		}

		word+=(tens.get(tenPositionNumber)+"-");

		//done to avoid "--"
		if(teenMapping.get(onePositionNumber).equals("")){
			word = word.substring(0, word.length()-1);
		}
		else word+=(teenMapping.get(onePositionNumber));

		return word;
	}
	
	public static void main(String[] args){
		System.out.println(NumberInWords.numberConversiontoEnglish(-123));
	}


	/**
	 * This method is for the purpose of JUnit testing where in the returned String 
	 * we check the number of words should be one count greater than the count of "-"
	 * @param word
	 * @return boolean
	 */
	public static boolean check(String word) {

		String[] words = word.split("-");
		int wordNum = words.length;
		int dashcount=0;

		for(int i = 0; i < word.length(); i++){
			if(word.charAt(i)=="-".charAt(0)){
				dashcount++;
			}
		}

		if(dashcount+1!=wordNum){
			return false;
		}
		return true;
	}
}
