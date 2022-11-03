package orm;

import java.sql.Connection;

public class EntityManager<T> implements DbContext<T>{

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(T entity) {
        return false;
    }

    @Override
    public Iterable<T> find(Class<T> table) {
        return null;
    }

    @Override
    public Iterable<T> find(Class<T> table, String where) {
        return null;
    }

    @Override
    public T findFirst(Class<T> table) {
        return null;
    }

    @Override
    public T findFirst(Class<T> table, String where) {
        return null;
    }
}
