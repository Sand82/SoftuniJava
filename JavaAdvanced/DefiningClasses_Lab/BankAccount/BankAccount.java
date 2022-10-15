package bankAccaunt;

public class BankAccount {

    private int id = banckAccountCounter;
    private double balance;
    private static double interest = 0.02;
    private static int banckAccountCounter = 1;

    public BankAccount() {
        banckAccountCounter++;
    }

    public static void setInterestRate(double interest) {

        BankAccount.interest = interest;
    }

    public double getInterest(int years) {

        return BankAccount.interest * years * this.balance;
    }

    public void deposit(double amount) {

        this.balance += amount;
    }

    public int getId(){
        return  this.id;
    }

}
