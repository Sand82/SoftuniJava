import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException {

        MyConnector myConnector = new MyConnector();

        myConnector.createConnection("root", "test","custom-orm");

        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<User>(connection);

        User user = new User("lub", 1, LocalDate.now());

        //userEntityManager.doCreate(User.class);
        userEntityManager.persist(user);
    }
}
