package orm;

import Annotations.Column;
import Annotations.Entity;
import Annotations.Id;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class EntityManager<T> implements DbContext<T> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }

    @Override
    public boolean persist(T entity) throws IllegalAccessException, SQLException {

        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity, idColumn);
        }

        return false;
        //return doUpdate(entity, idColumn);
    }

    private boolean doInsert(T entity, Field idColumn) throws SQLException, IllegalAccessException {

        String tableName = getTableName(entity.getClass());
        String tableFields = getColumnsWithoutId(entity.getClass());
        String tableValues = getColumValuesWithoutId(entity);

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, tableFields, tableValues);

        return connection.prepareStatement(insertQuery).execute();
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
    }

    private String getTableName(Class<?> aClass) {
        Entity [] annotationByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationByType == null) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationByType[0].name();
    }

    private String getColumValuesWithoutId(T entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);

            Object o = field.get(entity);

            if (o != null && o instanceof  String) {
                values.add("'" + o + "'");
            }else {
                values.add(String.valueOf(o));
            }
        }

        return String.join(",", values);
    }

    private String getColumnsWithoutId(Class<?> aClass) {

        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.joining(","));

    }
}
