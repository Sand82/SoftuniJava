package bankAccaunt;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    private static Map<Integer, BankAccount> bank = new LinkedHashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        String [] input = scanner.nextLine().split(" ");

        while (!input[0].equals("End")){

            String command = input[0];

            switch (command){

                case"Create":
                    createbankAccount();
                    break;
                case"Deposit":
                    int id = Integer.parseInt(input[1]);
                    int amount = Integer.parseInt(input[2]);
                    if (bank.containsKey(id)) {
                        BankAccount bankAccount = bank.get(id);
                        bankAccount.deposit(amount);
                        printString(String.format("%sed %d to ID%d",command,amount,id ));
                    }else {
                        printString("Account does not exist");
                    }
                    break;
                case"SetInterest":
                    double interest =Double.parseDouble(input[1]);
                    BankAccount.setInterestRate(interest);
                    break;
                case"GetInterest":
                    int idInterest = Integer.parseInt(input[1]);
                    int yearsInterest = Integer.parseInt(input[2]);
                    if (bank.containsKey(idInterest)) {
                        BankAccount bankAccount = bank.get(idInterest);
                        double currentInterest = bankAccount.getInterest(yearsInterest);
                        printString(String.format("%.2f",currentInterest));
                    }else {
                        printString("Account does not exist");
                    }
                    break;
            }

            input = scanner.nextLine().split(" ");
        }
    }

    private static void createbankAccount() {

        BankAccount bankAccount = new BankAccount();
        int id = bankAccount.getId();
        bank.put(id, bankAccount);

        String message = String.format("Account ID%d created", id);

        printString(message);
    }

    private static void printString(String message) {
        System.out.println(message);
    }
}
