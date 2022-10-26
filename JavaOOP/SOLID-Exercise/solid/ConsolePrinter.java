package solid;

import solid.Printer;

import java.util.List;

public class ConsolePrinter extends PrinterImpl {


    public ConsolePrinter(MyCalculator calculator) {
        super(calculator);
    }

    @Override
    public void printSum(List<Object> products) {
        System.out.printf("$s %f%n", GlobalConstants.SUM_FORMAT, calculator.sum(products));
    }

    @Override
    public void printAverage(List<Object> products) {
        System.out.printf("$s %f%n", GlobalConstants.AVERAGE_FORMAT, calculator.average(products));
    }
}
