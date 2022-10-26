package solid;

import solid.Printer;
import solid.products.Product;

import java.util.List;

public class ConsolePrinter extends PrinterImpl {

    public ConsolePrinter(MyCalculator calculator) {
        super(calculator);
    }

    @Override
    public void printSum() {
        System.out.printf("%s %.1f%n", GlobalConstants.SUM_FORMAT, calculator.sum());
    }

    @Override
    public void printAverage() {
        System.out.printf("%s %.1f%n", GlobalConstants.AVERAGE_FORMAT, calculator.average());
    }
}
