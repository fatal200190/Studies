package task3;

import java.util.ArrayList;

public class Calculate {
	private int dividend;
	private int divisor;
	private int index;
	private StringBuffer quotient = new StringBuffer();
	private ArrayList<Integer> minuendList = new ArrayList<Integer>();
	private ArrayList<Integer> subtrahendList = new ArrayList<Integer>();
	private ArrayList<Integer> minuendIndexesList = new ArrayList<Integer>();
	private int period = 12;
	private char delimiter = '.';

	public Calculate(int dividend, int divisor) {
		super();
		this.dividend = dividend;
		this.divisor = divisor;
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
			periodQuotientForming();
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
		int j;
		for (j = 0; j < quotient.length(); j++) {
			if (quotient.charAt(j) == delimiter) {
				break;
			}
		}
		String remainder = quotient.substring(j + 1, quotient.length());
		String remainderTmp = remainder;
		if (remainder.length() > 2) {
			int i;
			for (i = 1; i < remainder.length(); i++) {
				if (remainder.charAt(0) != remainder.charAt(i)) {
					break;
				}
			}
			if (i == remainder.length()) {
				remainderTmp = "(" + remainder.charAt(0) + ")";
				quotient.replace(j + 1, quotient.length(), remainderTmp);
			} else {
				for (int m = 0; m < remainder.length(); m++) {
					for (int n = m; n < remainder.length(); n++) {
						if (remainder.charAt(m) == remainder.charAt(n) && (n - m) > 1
								&& (n - m + n - 1) < remainder.length()
								&& remainder.substring(m, n).equals(remainder.substring(n, n - m + n))) {
							remainderTmp = "(" + remainder.substring(m, n) + ")";
							quotient.replace(j + 1 + m, quotient.length(), remainderTmp);
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
