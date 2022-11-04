import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        MyConnector myConnector = new MyConnector();

        myConnector.createConnection("root", "test", "custom-orm");

        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<User>(connection);

        User user = new User("lub", 1, LocalDate.now());
        user.setId(2);
        user.setUsername("lubo");
        user.setAge(1);

//      userEntityManager.doAlter(User.class);
//      userEntityManager.doCreate(User.class);

//      userEntityManager.persist(user);
        User second = userEntityManager.findFirst(User.class, "id = 2");
        System.out.println(second);
    }
}
