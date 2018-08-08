package task3SecondVariant;

import java.util.ArrayList;

public class Calculate {
	private int dividend;
	private int divisor;
	private int index;
	private StringBuffer quotient = new StringBuffer();
	private ArrayList<Integer> minuendList = new ArrayList<Integer>();
	private ArrayList<Integer> subtrahendList = new ArrayList<Integer>();
	private ArrayList<Integer> minuendIndexesList = new ArrayList<Integer>();
	private int period = 7;
	private char delimiter = '.';

	public Calculate(int dividend, int divisor, int period) {
		super();
		this.dividend = dividend;
		this.divisor = divisor;
		this.period = period;
	}

	public Calculate() {
	}

	public void division() throws Exception {
		if (divisor == 0) {
			throw new Exception("divisor can not be null");
		}
		if (dividend == 0) {
			throw new Exception("0");
		}

		if (dividend >= divisor) {
			dividendMoreThanDivisor();
			if(dividend%divisor!=0) {
				periodQuotientForming();
			}
		} else {
			quotient.append("0");
			remainderOfDivision(dividend);
			periodQuotientForming();
		}
	}

	private void dividendMoreThanDivisor() {
		String[] digits = String.valueOf(dividend).split("");
		StringBuffer dividendLeftSB = new StringBuffer();

		for (index = 0; index < digits.length; index++) {
			dividendLeftSB.append(digits[index]);
			if (Integer.valueOf(dividendLeftSB.toString()) >= divisor) {
				count(dividendLeftSB);
				minuendIndexesList.add(index);
			} else {
				if (index >= calculateDigit(divisor)) {
					quotient.append(0);
				}
			}
			if (index == digits.length - 1) {
				if (Integer.parseInt(dividendLeftSB.toString()) == 0) {
					minuendList.add(Integer.parseInt(dividendLeftSB.toString()));
					minuendIndexesList.add(checkNullsInTheEnd());
				} else {
					remainderOfDivision(Integer.valueOf(dividendLeftSB.toString()));
				}
			}
		}
	}

	private void remainderOfDivision(int residualDividend) {
		if (index < 1) {
			index = (int) Math.log10(residualDividend);
		}
		StringBuffer leftPart = new StringBuffer();
		quotient.append(delimiter);
		while (residualDividend != 0 && period != 0) {
			for (int i = 0; residualDividend < divisor; i++) {
				residualDividend = Integer.parseInt(String.valueOf(residualDividend) + "0");
				leftPart.delete(0, leftPart.length());
				leftPart.append(String.valueOf(residualDividend));
				index++;
				if (i > 0) {
					quotient.append("0");
					period--;
				}
			}
			count(leftPart);
			residualDividend = Integer.valueOf(leftPart.toString()) % divisor;
			period--;
			minuendIndexesList.add(index);
		}
		minuendIndexesList.add(index);
		minuendList.add(Integer.valueOf(leftPart.toString()));
	}

	private void count(StringBuffer changeableNumber) {
		minuendList.add(Integer.parseInt(changeableNumber.toString()));
		subtrahendList.add(Integer.valueOf(changeableNumber.toString()) / divisor * divisor);
		quotient.append(Integer.valueOf(changeableNumber.toString()) / divisor);
		changeableNumber.replace(0, changeableNumber.length(),
				String.valueOf(Integer.valueOf(changeableNumber.toString()) % divisor));
	}

	private int calculateDigit(int number) {
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

	private void periodQuotientForming() {
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
