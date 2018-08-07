package task3;

public class Printer {
	private StringBuffer result = new StringBuffer();
	private Calculate calculate = new Calculate();

	public Printer(Calculate calculate) {
		super();
		this.calculate = calculate;
	}

	public void printResult() {
		int minuedIndex = 0;
		int subtrahendIndex = 0;
		int textIndex;
		for (textIndex = 0; textIndex < calculate.getMinuendIndexesList().size() - 1; textIndex++) {
			String fisrstLine = puttingStringInPosition(calculate.getMinuendIndexesList().get(textIndex) + 2,
					"_" + String.valueOf(calculate.getMinuendList().get(minuedIndex)));
			result.append(fisrstLine).append("\n");
			String secondLine = puttingStringInPosition(calculate.getMinuendIndexesList().get(textIndex) + 2,
					String.valueOf(calculate.getSubtrahendList().get(subtrahendIndex)));
			result.append(secondLine).append("\n");
			String separator = puttingStringInPosition(calculate.getMinuendIndexesList().get(textIndex) + 2,
					addingString((int) Math.log10(calculate.getMinuendList().get(minuedIndex)) + 1, "-"));
			result.append(separator).append("\n");
			minuedIndex++;
			subtrahendIndex++;
		}
		String lastLine = puttingStringInPosition(calculate.getMinuendIndexesList().get(textIndex) + 2,
				String.valueOf(calculate.getMinuendList().get(minuedIndex)));
		result.append(lastLine).append("\n");

		formFirstThreeLines();
		System.out.println(result); 
	}

	private void formFirstThreeLines() {
		String[] lines = result.toString().split("\n");
		int index =(int)Math.log10(calculate.getDividend())+2 - lines[1].length();
		if(index > 0) {
			lines[0] = "_" + calculate.getDividend() + "|" + calculate.getDivisor();
			lines[1] += String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] += String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + calculate.getQuotient();
		}
		if(index < 0) {
			lines[0] = "_" + calculate.getDividend() +String.format("%" + index + "s", puttingStringInPosition(index," ")) + "|" + calculate.getDivisor();
			lines[1] = lines[1] + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] = lines[2] + "|" + calculate.getQuotient().toString();
		}
		if(index == 0) {
			lines[0] = "_" + calculate.getDividend() + "|" + calculate.getDivisor();
			lines[1] = lines[1] + "|" + addingString(findingLongesBetweenDivAndQuot(), "-");
			lines[2] = lines[2] + "|" + calculate.getQuotient().toString();
		}
		
		
		StringBuffer temp = new StringBuffer();
		for(String line : lines) {
			temp.append(line).append("\n");
		}
		result.replace(0, result.length(), temp.toString());
	}

	private int findingLongesBetweenDivAndQuot() {
		return (int) Math.log10(calculate.getDividend() + 1) >= calculate.getQuotient().length()
				? (int) Math.log10(calculate.getDividend() + 1)
				: calculate.getQuotient().length();
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
