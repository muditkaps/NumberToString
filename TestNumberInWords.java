/**
 *  Testing OnPrem/src/mkapil/date/NumberInWords.java
 */
package mkapil.test;

import mkapil.date.NumberInWords;
import java.util.Random;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author kapilm
 *
 */
public class TestNumberInWords {

	Integer testingSample;

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		testingSample = 10000;
	}
	
	@Test
	public void testNull() {
		Integer toTest = null;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals(NumberInWords.invalidReturn));
	}

	@Test
	public void testOctalNumber() {
		Integer toTest = 0010;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("eight"));
	}

	@Test
	public void testZero() {
		Integer toTest = 0;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("zero"));
	}

	@Test
	public void testSingleDigit() {
		Integer toTest = 5;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("five"));
	}

	@Test
	public void testTeenNumber() {
		Integer toTest = 17;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("seventeen"));
	}

	@Test
	public void test2digitwithZero() {
		Integer toTest = 30;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("thirty"));
	}

	@Test
	public void test2digit() {
		Integer toTest = 67;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("sixty-seven"));
	}

	@Test
	public void testNegativeInteger() {
		Integer toTest = -123;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("minus-one-hundred-twenty-three"));
	}
	@Test
	public void test3digitWithZero1() {
		Integer toTest = 540;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("five-hundred-forty"));
	}

	@Test
	public void test3digitWithZero2() {
		Integer toTest = 306;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("three-hundred-six"));
	}

	@Test
	public void test3digit() {
		Integer toTest = 579;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("five-hundred-seventy-nine"));
	}	

	@Test
	public void testHundred() {
		Integer toTest = 200;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("two-hundred"));
	}

	@Test
	public void testThousand() {
		Integer toTest = 13490;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("thirteen-thousand-four-hundred-ninety"));
	}

	@Test
	public void testMillion() {
		Integer toTest = 51309490;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("fifty-one-million-three-hundred-nine-thousand-four-hundred-ninety"));
	}

	@Test
	public void testBillion() {
		Integer toTest = 1951309490;
		String converted = NumberInWords.numberConversiontoEnglish(toTest);
		Assert.assertTrue(converted.equals("one-billion-nine-hundred-fifty-one-million-three-hundred-nine-thousand-four-hundred-ninety"));
	}

	@Test
	public void testFormatting() {
		Random r = new Random(Integer.MAX_VALUE);
		while(testingSample>0){
			Integer toTest = r.nextInt();
			String converted = NumberInWords.numberConversiontoEnglish(toTest);
			Assert.assertTrue(NumberInWords.check(converted));
			testingSample--;
		} 
	}
}