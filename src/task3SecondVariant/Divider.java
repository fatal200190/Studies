package task3SecondVariant;

public class Divider {
public static void main(String[] args) throws Exception {
		Calculator div = new Calculator(7);
		div.makeADivision(1, 666);
		Printer printer = new Printer(div);
		System.out.println(printer.printResult());
	}
}
