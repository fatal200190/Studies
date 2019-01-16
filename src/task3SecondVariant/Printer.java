package task3SecondVariant;

public class Printer {
	private StringBuffer result = new StringBuffer();
	private Calculator calculator;

	public Printer(Calculator calculator) {
		this.calculator = calculator;
	}

	public String printResult() {
		int minuedIndex = 0;
		int subtrahendIndex = 0;
		int textIndex;
		for (textIndex = 0; textIndex < calculator.getMinuendIndexesList().size() - 1; textIndex++) {
			String fisrstLine = puttingStringInPosition(calculator.getMinuendIndexesList().get(textIndex) + 2,
					"_" + String.valueOf(calculator.getMinuendList().get(minuedIndex)));
			result.append(fisrstLine).append("\n");
			String secondLine = puttingStringInPosition(calculator.getMinuendIndexesList().get(textIndex) + 2,
					String.valueOf(calculator.getSubtrahendList().get(subtrahendIndex)));
			result.append(secondLine).append("\n");
			String separator = puttingStringInPosition(calculator.getMinuendIndexesList().get(textIndex) + 2,
					addingString((int) Math.log10(calculator.getMinuendList().get(minuedIndex)) + 1, "-"));
			result.append(separator).append("\n");
			minuedIndex++;
			subtrahendIndex++;
		}
		String lastLine = puttingStringInPosition(calculator.getMinuendIndexesList().get(textIndex) + 2,
				String.valueOf(calculator.getMinuendList().get(minuedIndex)));
		result.append(lastLine).append("\n");

		formingFirstThreeLines();
		
		return String.valueOf(result);
	}

	private void formingFirstThreeLines() {
		String[] lines = result.toString().split("\n");
		int index =(int)Math.log10(calculator.getDividend())+2 - lines[1].length();
		if(index > 0) {
			lines[0] = "_" + calculator.getDividend() + "|" + calculator.getDivisor();
			lines[1] += String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] += String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + calculator.getQuotient();
		}
		if(index < 0) {
			lines[0] = "_" + calculator.getDividend() +String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + calculator.getDivisor();
			lines[1] = lines[1] + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] = lines[2] + "|" + calculator.getQuotient().toString();
		}
		if(index == 0) {
			lines[0] = "_" + calculator.getDividend() + "|" + calculator.getDivisor();
			lines[1] = lines[1] + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] = lines[2] + "|" + calculator.getQuotient().toString();
		}
		
		
		StringBuffer temp = new StringBuffer();
		for(String line : lines) {
			temp.append(line).append("\n");
		}
		result.replace(0, result.length(), temp.toString());
	}

	private int findingLongesBetweenDivAndQuot() {
		return (int) Math.log10(calculator.getDividend() + 1) >= calculator.getQuotient().length()
				? (int) Math.log10(calculator.getDividend() + 1)
				: calculator.getQuotient().length();
	}

	private String puttingStringInPosition(int position, String str) {
		String line = String.format("%" + position + "s", str);
		return line;
	}

	private String addingString(int number, String text) {
		String str = "";
		for (int i = 0; i < number; i++) {
			str += text;
		}
		return str;
	}

}
