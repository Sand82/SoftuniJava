package Singleton;

public class DatabaseConnection {

    private static DatabaseConnection data;
    private DatabaseConnection() {

        try{
            Thread.sleep(5_000);

        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    public void readData () {
        System.out.println("Reading data...");
    }

    public void writeInData () {
        System.out.println("Write in data...");
    }

    public static DatabaseConnection createInstance () {

        if (data == null) {
            data = new DatabaseConnection();
        }

        return data;
    }
}
