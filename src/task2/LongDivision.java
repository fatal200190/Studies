package task2;

public class LongDivision {
	private StringBuffer result = new StringBuffer();
	private StringBuffer quotient = new StringBuffer();
	private StringBuffer leftPartOfDividendMostDigits = new StringBuffer();

	public String makeDivision(int dividend, int divisor) {
		if (divisor == 0) {
			return null;
		}
		if (dividend == 0 || dividend < divisor) {
			return "0";
		}
		dividend = Math.abs(dividend);
		divisor = Math.abs(divisor);

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
				result.append(String.format("%" + (numberOfDigitInDividend + 2) + "s",
						String.valueOf(intermediateDividendFromLeftPartOfDivided))).append("\n");
			}
		}
		modifyResultToView(dividend, divisor);
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

	private void modifyResultToView(int dividend, int divisor) {
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
		result.replace(1, (indexesOfSymbol[0]), String.valueOf(dividend) + "|" + String.valueOf(divisor));
	}
}
