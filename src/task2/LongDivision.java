package task2;

public class LongDivision {
	StringBuffer dividendString = new StringBuffer();
	StringBuffer divisorString = new StringBuffer();
	StringBuffer intermediateDividend = new StringBuffer();
	StringBuffer result = new StringBuffer();
	StringBuffer resultToView = new StringBuffer();
	String[] intermediateDividends;
	String[] biggestTempDivisors;
	
	
	public StringBuffer makeDivision(int dividend, int divisor) {
		dividendString.append(Integer.toString(dividend));
		divisorString.append(Integer.toString(divisor));
		
		
		while(Integer.parseInt(dividendString.toString()) > divisor) {
		intermediateDividend.append(dividendString.substring(0, divisorString.length()));
		
			if(Integer.parseInt(intermediateDividend.toString()) < divisor) {
				intermediateDividend.append(dividendString.substring(divisorString.length(), divisorString.length()+1));
		}
			result.append(intermediateDividend).append('\n');
			
			Integer biggestTempDivisor = (Integer.parseInt(intermediateDividend.toString()) / divisor) * divisor;
			
			result.append(biggestTempDivisor).append('\n');;
			
			Integer div = Integer.parseInt(intermediateDividend.toString()) % divisor;
//			result.append(" ").append(div).append('\n');
			
			dividendString.replace(0, intermediateDividend.length(), div.toString());
			intermediateDividend.delete(0,intermediateDividend.length());
		}
		return result;
	}
	
	private StringBuffer viewOfPrintedDataOfDivision (int dividend, int divisor) {
		resultToView.append("_").append(dividendString).append("|").append(divisor).append('\n');
		
		return null;
	}

}
