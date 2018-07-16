package task3;

public class LongDivisionWithRemain {
	private StringBuffer result = new StringBuffer();
	private StringBuffer quotient = new StringBuffer();
	private int numberOfDigitsAfterComma = 10;

	public String makeDivision(int dividend, int divisor) {
		if (divisor == 0) {
			return null;
		}
		if (dividend == 0) {
			return "0";
		}
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

		if (dividend < divisor) {
			divisionWithRemainderToView(dividend, divisor);
		} else {
			divisionToView(dividend, divisor);
		}
		return result.toString();
	}

	private int calculateDigitInNumber(int number) {
		return (int) Math.log10(number) + 1;
	}

	private String addToStringCertainNumberOfSameSymbols(int numberOfChars, char symbol) {
		String str = "";
		for (int i = 0; i < numberOfChars; i++) {
			str += symbol;
		}
		return str;
	}

	private String puttingStringOnThePositionFromBeginOfLine(int position, String str) {
		String line = String.format("%" + position + "s", str);
		return line;
	}

	private void modifyFirstThreeLinesToView(int dividend, int divisor) {
		int[] indexesOfSymbol = new int[3];
		int index = 0;
		char symbol = '\n';
		for (int i = 0; i < result.length(); i++) {
			if (result.charAt(i) == symbol) {
				indexesOfSymbol[index] = i;
				index++;
			}
			if (index == 3) {
				break;
			}
		}
		int numberOfWhitespaces = calculateDigitInNumber(dividend) + 1 - indexesOfSymbol[0];
		result.insert(indexesOfSymbol[2],
				addToStringCertainNumberOfSameSymbols(numberOfWhitespaces, ' ') + "|" + quotient.toString());
		result.insert(indexesOfSymbol[1], addToStringCertainNumberOfSameSymbols(numberOfWhitespaces, ' ') + "|"
				+ addToStringCertainNumberOfSameSymbols(quotient.length(), '-'));
		if (dividend < divisor) {
			result.replace(1, (indexesOfSymbol[0]),
					String.valueOf(dividend)
							+ addToStringCertainNumberOfSameSymbols(calculateDigitInNumber(divisor), ' ') + "|"
							+ String.valueOf(divisor));
		} else {
			result.replace(1, (indexesOfSymbol[0]), String.valueOf(dividend) + "|" + String.valueOf(divisor));
		}
	}

	private void divisionWithRemainderToView(int dividend, int divisor) {
		int intermediateDividend = dividend;
		int intermediateRemainder;
		int numberOfWhitespaces = calculateDigitInNumber(dividend);
		int biggestMultiplyNumberForSubstraction;
		StringBuffer leftPartOfDividendMostDigits = new StringBuffer();

		quotient.append("0").append(".");
		while (intermediateDividend != 0 && numberOfDigitsAfterComma != 0) {
			for (int i = 0; intermediateDividend < divisor; i++) {
				intermediateDividend = Integer.parseInt(String.valueOf(intermediateDividend) + "0");
				numberOfWhitespaces++;
				if (i > 0) {
					quotient.append("0");
					numberOfDigitsAfterComma--;
				}
			}
			intermediateRemainder = intermediateDividend % divisor;
			biggestMultiplyNumberForSubstraction = intermediateDividend / divisor * divisor;

			result.append(puttingStringOnThePositionFromBeginOfLine(numberOfWhitespaces + 1,
					"_" + String.valueOf(intermediateDividend))).append("\n");
			result.append(puttingStringOnThePositionFromBeginOfLine(numberOfWhitespaces + 1,
					String.valueOf(biggestMultiplyNumberForSubstraction))).append("\n");
			result.append(puttingStringOnThePositionFromBeginOfLine(numberOfWhitespaces + 1,
					addToStringCertainNumberOfSameSymbols(calculateDigitInNumber(intermediateDividend), '-')))
					.append("\n");

			quotient.append(intermediateDividend / divisor);
			leftPartOfDividendMostDigits.replace(0, leftPartOfDividendMostDigits.length(),
					String.valueOf(intermediateRemainder));
			intermediateDividend = Integer.parseInt(leftPartOfDividendMostDigits.toString());

			numberOfDigitsAfterComma--;
		}
		result.append(puttingStringOnThePositionFromBeginOfLine(numberOfWhitespaces + 1,
				String.valueOf(intermediateDividend))).append("\n");
		modifyFirstThreeLinesToView(dividend, divisor);
	}

	private void divisionToView(int dividend, int divisor) {
		String[] digitsOfDividend = String.valueOf(dividend).split("");
		int intermediateDividendFromLeftPartOfDivided;
		int biggestMultiplyNumberForSubstraction;
		int intermediateRemainder;
		StringBuffer leftPartOfDividendMostDigits = new StringBuffer();
		for (int numberOfDigitInDividend = 0; numberOfDigitInDividend < digitsOfDividend.length; numberOfDigitInDividend++) {

			leftPartOfDividendMostDigits.append(digitsOfDividend[numberOfDigitInDividend]);
			intermediateDividendFromLeftPartOfDivided = Integer.parseInt(leftPartOfDividendMostDigits.toString());

			if (intermediateDividendFromLeftPartOfDivided >= divisor) {
				intermediateRemainder = intermediateDividendFromLeftPartOfDivided % divisor;
				biggestMultiplyNumberForSubstraction = intermediateDividendFromLeftPartOfDivided / divisor * divisor;

				result.append(puttingStringOnThePositionFromBeginOfLine(numberOfDigitInDividend + 2,
						"_" + String.valueOf(intermediateDividendFromLeftPartOfDivided))).append("\n");

				result.append(puttingStringOnThePositionFromBeginOfLine(numberOfDigitInDividend + 2,
						String.valueOf(biggestMultiplyNumberForSubstraction))).append("\n");

				result.append(
						puttingStringOnThePositionFromBeginOfLine(numberOfDigitInDividend + 2,
								addToStringCertainNumberOfSameSymbols(
										calculateDigitInNumber(intermediateDividendFromLeftPartOfDivided), '-')))
						.append("\n");

				quotient.append(intermediateDividendFromLeftPartOfDivided / divisor);

				leftPartOfDividendMostDigits.replace(0, leftPartOfDividendMostDigits.length(),
						String.valueOf(intermediateRemainder));
				intermediateDividendFromLeftPartOfDivided = Integer.parseInt(leftPartOfDividendMostDigits.toString());

			} else {
				if (numberOfDigitInDividend >= calculateDigitInNumber(divisor)) {
					quotient.append(0);
				}
			}
			if (numberOfDigitInDividend == digitsOfDividend.length - 1) {
				if (intermediateDividendFromLeftPartOfDivided == 0) {
					result.append(puttingStringOnThePositionFromBeginOfLine(numberOfDigitInDividend + 2,
							String.valueOf(intermediateDividendFromLeftPartOfDivided))).append("\n");
					modifyFirstThreeLinesToView(dividend, divisor);
				} else {
					divisionWithRemainderToView(intermediateDividendFromLeftPartOfDivided, divisor);
					modifyFirstThreeLinesToView(dividend, divisor);
				}
			}
		}

	}
}
