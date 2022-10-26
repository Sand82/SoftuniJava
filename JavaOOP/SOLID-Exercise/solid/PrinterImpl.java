package solid;

public abstract class PrinterImpl implements Printer {

   protected MyCalculator calculator;

    public PrinterImpl(MyCalculator calculator) {
        this.calculator = calculator;
    }
}
