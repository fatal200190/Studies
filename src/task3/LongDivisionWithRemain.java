package task3;

public class LongDivisionWithRemain {
	private StringBuffer result = new StringBuffer();
	private StringBuffer quotient = new StringBuffer();
	private StringBuffer leftPartOfDividendMostDigits = new StringBuffer();

	public String makeDivision(int dividend, int divisor) {
		if (divisor == 0) {
			return null;
		}
		if (dividend == 0) {
			return "0";
		}
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);
		
		if(dividend<divisor) {
			quotient.append("0");
			remainderToView(dividend, divisor, calculateDigitInNumber(dividend));
			modifyFirstThreeLinesToView(dividend, divisor);
			return result.toString();
		} else {
			
		String[] digitsOfDividend = String.valueOf(dividend).split("");
		int intermediateDividendFromLeftPartOfDivided;
		int biggestMultiplyNumberForSubstraction;
		int intermediateRemainder;
		for (int numberOfDigitInDividend = 0; numberOfDigitInDividend < digitsOfDividend.length; numberOfDigitInDividend++) {
			leftPartOfDividendMostDigits.append(digitsOfDividend[numberOfDigitInDividend]);
			intermediateDividendFromLeftPartOfDivided = Integer.parseInt(leftPartOfDividendMostDigits.toString());
			if (intermediateDividendFromLeftPartOfDivided >= divisor) {
				intermediateRemainder = intermediateDividendFromLeftPartOfDivided % divisor;
				biggestMultiplyNumberForSubstraction = intermediateDividendFromLeftPartOfDivided / divisor * divisor;
				String firstLineOfSubtraction = String.format("%" + (numberOfDigitInDividend + 2) + "s",
						"_" + String.valueOf(intermediateDividendFromLeftPartOfDivided));
				result.append(firstLineOfSubtraction).append("\n");
				String secondLineOfSubtraction = String.format("%" + (numberOfDigitInDividend + 2) + "d",
						biggestMultiplyNumberForSubstraction);
				result.append(secondLineOfSubtraction).append("\n");
				String separatorOfSubtractions = String.format("%" + (numberOfDigitInDividend + 2) + "s",
						addToStringCertainNumberOfSameSymbols(
								calculateDigitInNumber(intermediateDividendFromLeftPartOfDivided), '-'));
				result.append(separatorOfSubtractions).append("\n");
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
					result.append(String.format("%" + (numberOfDigitInDividend + 2) + "s",
							String.valueOf(intermediateDividendFromLeftPartOfDivided))).append("\n");
							modifyFirstThreeLinesToView(dividend, divisor);
				}else {
				remainderToView(intermediateDividendFromLeftPartOfDivided, divisor, numberOfDigitInDividend);
				modifyFirstThreeLinesToView(dividend, divisor);
				}
			}
		}

		return result.toString();
		}
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
		if(dividend<divisor) {
			result.replace(1, (indexesOfSymbol[0]), String.valueOf(dividend) + addToStringCertainNumberOfSameSymbols(calculateDigitInNumber(divisor),' ') + "|" + String.valueOf(divisor));
		}else {
			result.replace(1, (indexesOfSymbol[0]), String.valueOf(dividend) + "|" + String.valueOf(divisor));
		}
	}

	private void remainderToView(int dividend, int divisor, int numberOfWhitespaces) {
		quotient.append(".");
		int numberOfDigitsAfterComma = 10;
		int biggestMultiplyNumberForSubstraction;
		int intermediateRemainder;

		while (dividend != 0 && numberOfDigitsAfterComma != 0) {
			for (int i = 0; dividend < divisor; i++) {
				dividend = Integer.parseInt(String.valueOf(dividend) + "0");
				numberOfWhitespaces++;
				if (i > 0) {
					quotient.append("0");
					numberOfDigitsAfterComma--;
				}
			}
			intermediateRemainder = dividend % divisor;
			biggestMultiplyNumberForSubstraction = dividend / divisor * divisor;
			String firstLineOfSubtraction = String.format("%" + (numberOfWhitespaces + 1) + "s",
					"_" + String.valueOf(dividend));
			result.append(firstLineOfSubtraction).append("\n");
			String secondLineOfSubtraction = String.format("%" + (numberOfWhitespaces + 1) + "d",
					biggestMultiplyNumberForSubstraction);
			result.append(secondLineOfSubtraction).append("\n");
			String separatorOfSubtractions = String.format("%" + (numberOfWhitespaces + 1) + "s",
					addToStringCertainNumberOfSameSymbols(calculateDigitInNumber(dividend), '-'));
			result.append(separatorOfSubtractions).append("\n");
			quotient.append(dividend / divisor);
			leftPartOfDividendMostDigits.replace(0, leftPartOfDividendMostDigits.length(),
					String.valueOf(intermediateRemainder));
			dividend = Integer.parseInt(leftPartOfDividendMostDigits.toString());

			numberOfDigitsAfterComma--;
		}
		
		result.append(String.format("%" + (numberOfWhitespaces + 1) + "s", String.valueOf(dividend))).append("\n");

	}
}
