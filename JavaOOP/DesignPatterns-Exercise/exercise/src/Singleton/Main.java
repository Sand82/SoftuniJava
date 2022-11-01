package Singleton;

public class Main {
    public static void main(String[] args) {
        DatabaseConnection data = DatabaseConnection.createInstance();
        data.readData();

        DatabaseConnection data1 = DatabaseConnection.createInstance();
        data1.writeInData();
        System.out.println("Finished...");
    }
}
