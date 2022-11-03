package orm;

import com.sun.jdi.connect.Connector;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class MyConnector {
    private static Connection connection;
    private static final String jdbcString = "jdbc:mysql://localhost:3306/";

    private MyConnector() {
    }

    public Connection createConnection(String user, String password, String dbName) throws SQLException {

        Properties properties = new Properties();
        properties.setProperty("user", "sand");
        properties.setProperty("password", "test");

        this.connection = DriverManager.getConnection(jdbcString + dbName, properties);
        return connection;
    }

    public static Connection getConnection() {

        return connection;
    }
}
