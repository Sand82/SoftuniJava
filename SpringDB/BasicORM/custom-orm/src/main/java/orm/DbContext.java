package orm;

import java.sql.SQLException;
import java.util.Iterator;

public interface DbContext<T> {

    boolean persist(T entity) throws IllegalAccessException, SQLException;

    Iterable<T> find(Class<T> table);

    Iterable<T> find(Class<T> table, String where);

    T findFirst(Class<T> table);

    T findFirst(Class<T> table, String where);
}
