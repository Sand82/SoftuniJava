package orm;

import Annotations.Entity;
import Annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.util.Arrays;

public class EntityManager<T> implements DbContext<T> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(T entity) throws IllegalAccessException {

        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity, idColumn);
        }

        return false;
        //return doUpdate(entity, idColumn);
    }

    private boolean doInsert(T entity, Field idColumn) {

        String tableName = getTableName(entity.getClass());

        getColumnsWithoutId(entity.getClass());

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES ???", tableName);

        return false;
    }

    private void getColumnsWithoutId(Class<?> aClass) { // Todo

       Arrays.stream(aClass.getDeclaredFields())
                .filter(x -> !x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column."));

    }

    private String getTableName(Class<?> aClass) {
       Entity [] annotationByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationByType == null) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationByType[0].name();
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

    private Field getIdColumn(Class<?> clazz) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column."));

//        Field[] declaredFields = clazz.getDeclaredFields();
//
//        for (Field declaredField : declaredFields) {
//
//            if (declaredField.isAnnotationPresent(Id.class)) {
//
//                return declaredField;
//            }
//        }
//        throw new UnsupportedOperationException("Entity missing an Id column.");
    }
}
