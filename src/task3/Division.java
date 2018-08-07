package task3;

public class Division {
public static void main(String[] args) throws Exception {
		Calculate div = new Calculate(10,7);
		div.division();
		Printer printer = new Printer(div);
		printer.printResult();
	}
}
