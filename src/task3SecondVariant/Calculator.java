package task3SecondVariant;

import java.util.ArrayList;

public class Calculator {
	private int dividend;
	private int divisor;
	private int index;
	private StringBuffer quotient = new StringBuffer();
	private ArrayList<Integer> minuendList = new ArrayList<Integer>();
	private ArrayList<Integer> subtrahendList = new ArrayList<Integer>();
	private ArrayList<Integer> minuendIndexesList = new ArrayList<Integer>();
	private int period = 7;
	private char delimiter = '.';

	public Calculator(int period) {
		this.period = period;
	}

	public Calculator() {
	}

	public void makeADivision(int dividendNumber, int divisorNumber) throws Exception {
		dividend = dividendNumber;
		divisor = divisorNumber;
		if (divisor == 0) {
			throw new Exception("divisor can not be null");
		}
		if (dividend == 0) {
			throw new Exception("result of division is 0");
		}

		if (dividend >= divisor) {
			dividingWithDividendMoreThanDivisor();
			if(dividend%divisor!=0) {
				formingPeriodOfQuotient();
			}
		} else {
			quotient.append("0");
			formingRemainderOfDivision(dividend);
			formingPeriodOfQuotient();
		}
	}

	private void dividingWithDividendMoreThanDivisor() {
		String[] digits = convertIntToString(dividend).split("");
		StringBuffer dividendLeftPart = new StringBuffer();

		for (index = 0; index < digits.length; index++) {
			dividendLeftPart.append(digits[index]);
			if (convertStringToInt(dividendLeftPart.toString()) >= divisor) {
				makingADivisionOfIntermediateDividerAndDivisor(dividendLeftPart);
				minuendIndexesList.add(index);
			} else {
				if (index >= calculateDigitOfNumber(divisor)) {
					quotient.append(0);
				}
			}
			if (index == digits.length - 1) {
				if (convertStringToInt(dividendLeftPart.toString()) == 0) {
					minuendList.add(convertStringToInt(dividendLeftPart.toString()));
					minuendIndexesList.add(checkNullsInTheEnd());
				} else {
					formingRemainderOfDivision(convertStringToInt(dividendLeftPart.toString()));
				}
			}
		}
	}

	private void formingRemainderOfDivision(int residualDividend) {
		if (index < 1) {
			index = (int) Math.log10(residualDividend);
		}
		StringBuffer leftPart = new StringBuffer();
		quotient.append(delimiter);
		while (residualDividend != 0 && period != 0) {
			for (int i = 0; residualDividend < divisor; i++) {
				residualDividend = convertStringToInt(convertIntToString(residualDividend) + "0");
				leftPart.delete(0, leftPart.length());
				leftPart.append(convertIntToString(residualDividend));
				index++;
				if (i > 0) {
					quotient.append("0");
					period--;
				}
			}
			makingADivisionOfIntermediateDividerAndDivisor(leftPart);
			residualDividend = convertStringToInt(leftPart.toString()) % divisor;
			period--;
			minuendIndexesList.add(index);
		}
		minuendIndexesList.add(index);
		minuendList.add(convertStringToInt(leftPart.toString()));
	}

	private void makingADivisionOfIntermediateDividerAndDivisor(StringBuffer changeableNumber) {
		minuendList.add(convertStringToInt(changeableNumber.toString()));
		subtrahendList.add(convertStringToInt(changeableNumber.toString()) / divisor * divisor);
		quotient.append(convertStringToInt(changeableNumber.toString()) / divisor);
		changeableNumber.replace(0, changeableNumber.length(),
				convertIntToString(convertStringToInt(changeableNumber.toString()) % divisor));
	}

	private int calculateDigitOfNumber(int number) {
		return (int) Math.log10(number) + 1;
	}

	private int checkNullsInTheEnd() {
		String nulls = "";
		if (dividend % 10 == 0) {
			for (int n = 0; n < quotient.length(); n++) {
				if (quotient.charAt(n) == '0') {
					nulls += '0';
				}
			}
			return (int) Math.log10(dividend) - nulls.length();
		}
		return index;
	}

	private void formingPeriodOfQuotient() {
		int indexDelimiter;
		for (indexDelimiter = 0; indexDelimiter < quotient.length(); indexDelimiter++) {
			if (quotient.charAt(indexDelimiter) == delimiter) {
				break;
			}
		}
		String remainder = quotient.substring(indexDelimiter + 1, quotient.length());
		String remainderTmp = remainder;
		if (remainder.length() > 2) {
			int indexOfDigit;
			for (indexOfDigit = 0; indexOfDigit < remainder.length(); indexOfDigit++) {
				if (remainder.charAt(0) != remainder.charAt(indexOfDigit)) {
					break;
				}
			}
			if (indexOfDigit == remainder.length()) {
				remainderTmp = "(" + remainder.charAt(0) + ")";
				quotient.replace(indexDelimiter + 1, quotient.length(), remainderTmp);
			} else {
				for (int indexFirstDigit = 0; indexFirstDigit < remainder.length(); indexFirstDigit++) {
					for (int indexNextDigit = indexFirstDigit; indexNextDigit < remainder.length(); indexNextDigit++) {
						if (remainder.charAt(indexFirstDigit) == remainder.charAt(indexNextDigit) && (indexNextDigit - indexFirstDigit) > 1
								&& (indexNextDigit - indexFirstDigit + indexNextDigit - 1) < remainder.length()
								&& remainder.substring(indexFirstDigit, indexNextDigit).equals(remainder.substring(indexNextDigit, indexNextDigit - indexFirstDigit + indexNextDigit))) {
							remainderTmp = "(" + remainder.substring(indexFirstDigit, indexNextDigit) + ")";
							quotient.replace(indexDelimiter + 1 + indexFirstDigit, quotient.length(), remainderTmp);
							return;
						}
					}
				}
			}
		}
	}
	
	private int convertStringToInt (String str) {
		return Integer.valueOf(str);
	}
	
	private String convertIntToString (int n) {
		return String.valueOf(n);
	}

	public int getDividend() {
		return dividend;
	}

	public int getDivisor() {
		return divisor;
	}

	public StringBuffer getQuotient() {
		return quotient;
	}

	public ArrayList<Integer> getMinuendList() {
		return minuendList;
	}

	public ArrayList<Integer> getSubtrahendList() {
		return subtrahendList;
	}

	public ArrayList<Integer> getMinuendIndexesList() {
		return minuendIndexesList;
	}
}
