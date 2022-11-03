import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {

        MyConnector myConnector = new MyConnector();

        myConnector.createConnection("root", "test","custom-orm");

        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<User>(connection);
    }
}
