import entities.Address;
import entities.User;
import orm.EntityManager;
import orm.MyConnector;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException, IllegalAccessException, InvocationTargetException, NoSuchMethodException, InstantiationException {

        MyConnector myConnector = new MyConnector();

        myConnector.createConnection("root", "test", "custom-orm");

        Connection connection = MyConnector.getConnection();

        EntityManager<User> userEntityManager = new EntityManager<User>(connection);

//        User user = new User("sand", 40, LocalDate.now());
//        user.setId(2);
//        user.setUsername("lubo");
//        user.setAge(1);
//
//        userEntityManager.doAlter(User.class);
//        userEntityManager.doCreate(User.class);
//
//        userEntityManager.persist(user);
//        User first = userEntityManager.findFirst(User.class);
//        User second = userEntityManager.findFirst(User.class, "id = 2");
//        System.out.println(first);
//        System.out.println(second);
//        userEntityManager.find(User.class).forEach(i -> System.out.println(i.toString()));
//        userEntityManager.find(User.class).forEach(i -> System.out.println(i.toString()));
//
//        User toDelete = userEntityManager.findFirst(User.class, "id = 4");
//        System.out.println(toDelete);
//        System.out.println("------------------------------------");
//        userEntityManager.delete(toDelete);
//        userEntityManager.find(User.class).forEach(i -> System.out.println(i.toString()));

        EntityManager<Address> addressEntityManager = new EntityManager<>(connection);
        //addressEntityManager.doCreate(Address.class);
        //Address address = new Address("Xan Krum", 25, "Sofia", "Bulgaria", "1220");
        //addressEntityManager.persist(address);

//        Address addressToDelete = addressEntityManager.findFirst(Address.class, "id = 3");
//        System.out.println(addressToDelete);
//        addressEntityManager.delete(addressToDelete);
//        addressEntityManager.find(Address.class).forEach(i -> System.out.println(i.toString()));
    }
}
