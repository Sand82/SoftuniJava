package orm;

import Annotations.Column;
import Annotations.Entity;
import Annotations.Id;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class EntityManager<T> implements DbContext<T> {

    private Connection connection;

    public EntityManager(Connection connection) {
        this.connection = connection;
    }


    public void doCreate(Class<T> entityClass) throws SQLException {

        String tableName = getTableName(entityClass);
        String fieldsWithTypes = getSQLFieldsWithTypes(entityClass);

        String createQuery = String.format(
                "CREATE TABLE %s (id INT PRIMARY KEY AUTO_INCREMENT, %s)",
                tableName, fieldsWithTypes);

        PreparedStatement statement = connection.prepareStatement(createQuery);

        statement.execute();
    }

    public void doAlter(Class<T> entityClass) throws SQLException {
        String tableName = getTableName(entityClass);
        String addColumnStatements = getAddColumnStatementsForNewFields(entityClass);

        String createQuery = String.format("ALTER TABLE %s %s", tableName, addColumnStatements);

        PreparedStatement statement = connection.prepareStatement(createQuery);
        statement.execute();
    }

    @Override
    public boolean persist(T entity) throws IllegalAccessException, SQLException {

        Field idColumn = getIdColumn(entity.getClass());
        idColumn.setAccessible(true);
        Object idValue = idColumn.get(entity);

        if (idValue == null || (long) idValue <= 0) {
            return doInsert(entity);
        }

        return doUpdate(entity, (long) idValue);
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
    public T findFirst(Class<T> table, String where) throws SQLException, NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {

        String tableName = getTableName(table);

        String selectQuery = String.format(
                "SELECT * FROM %s %s LIMIT 1", tableName,  where != null ? "WHERE " + where : "");
        
        PreparedStatement statement = connection.prepareStatement(selectQuery);
        ResultSet resultSet = statement.executeQuery();
        
        resultSet.next();
        
        T result = table.getDeclaredConstructor().newInstance();
        
        fillEntity(table, resultSet, result);
        
        return result;
    }

    private void fillEntity(Class<T> table, ResultSet resultSet, T entity) throws SQLException, IllegalAccessException {
        Field [] declaredFields = table.getDeclaredFields();

        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);

            fillFiled(declaredField, resultSet, entity);

        }
    }

    private void fillFiled(Field declaredField, ResultSet resultSet, T entity) throws SQLException, IllegalAccessException {
        Class<?> fieldType = declaredField.getType();
        String fieldName = declaredField.getAnnotationsByType(Column.class)[0].name();

        Object value = null;

        if (fieldType == Integer.class || fieldType == int.class) {
            value = resultSet.getInt(fieldName);
        }else if (fieldType == String.class) {
            value = resultSet.getString(fieldName);
        }else if (fieldType == LocalDate.class) {
            value = LocalDate.parse(resultSet.getString(fieldName));
        }else if (fieldType == Long.class|| fieldType == long.class) {
            value = resultSet.getLong(fieldName);
        }

        declaredField.set(entity, value);
    }

    private Field getIdColumn(Class<?> clazz) {

        return Arrays.stream(clazz.getDeclaredFields())
                .filter(x -> x.isAnnotationPresent(Id.class))
                .findFirst()
                .orElseThrow(() -> new UnsupportedOperationException("Entity missing an Id column."));
    }

    private String getTableName(Class<?> aClass) {
        Entity[] annotationByType = aClass.getAnnotationsByType(Entity.class);

        if (annotationByType == null) {
            throw new UnsupportedOperationException("Class must be Entity");
        }

        return annotationByType[0].name();
    }

    private boolean doUpdate(T entity, long idColumn) throws IllegalAccessException, SQLException {
        String tableName = getTableName(entity.getClass());
        List<String> tableFields = getColumnsWithoutId(entity.getClass());
        List<String> tableValues = getColumValuesWithoutId(entity);


        List<String> setStatements = new ArrayList<>();

        for (int i = 0; i < tableFields.size(); i++) {
            String statement = tableFields.get(i) + " = " + tableValues.get(i);
            setStatements.add(statement);
        }

        String updateQuery = String.format("UPDATE %s SET %s WHERE id = %d",
                tableName, String.join(",", setStatements), idColumn);

        PreparedStatement statement = connection.prepareStatement(updateQuery);
        return statement.execute();
    }

    private boolean doInsert(T entity) throws SQLException, IllegalAccessException {

        String tableName = getTableName(entity.getClass());
        String tableFields = String.join(",", getColumnsWithoutId(entity.getClass()));
        String tableValues = String.join(",", getColumValuesWithoutId(entity));

        String insertQuery = String.format("INSERT INTO %s (%s) VALUES (%s)", tableName, tableFields, tableValues);

        return connection.prepareStatement(insertQuery).execute();
    }

    private List<String> getColumValuesWithoutId(T entity) throws IllegalAccessException {
        Class<?> aClass = entity.getClass();
        List<Field> fields = Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> values = new ArrayList<>();

        for (Field field : fields) {
            field.setAccessible(true);

            Object o = field.get(entity);

            if (o instanceof LocalDate || o instanceof String) {
                values.add("'" + o + "'");
            } else {
                values.add(String.valueOf(o));
            }
        }

        return values;
    }

    private List<String> getColumnsWithoutId(Class<?> aClass) {

        return Arrays.stream(aClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .map(f -> f.getAnnotationsByType(Column.class))
                .map(a -> a[0].name())
                .collect(Collectors.toList());

    }

    private String getSQLFieldsWithTypes(Class<T> entityClass) {

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        for (Field field : fields) {

            String fieldName = field.getAnnotationsByType(Column.class)[0].name();
            Class<?> type = field.getType();

            String sqlType = getSQLType(type);

            result.add(fieldName + " " + sqlType);
        }

        return String.join(",", result);
    }

    private static String getSQLType(Class<?> type) {

        String sqlType = "";

        if (type == Integer.class || type == int.class) {
            sqlType = "INT";
        } else if (type == String.class) {
            sqlType = "VARCHAR(200)";
        } else if (type == LocalDate.class) {
            sqlType = "DATE";
        }

        return sqlType;
    }

    private String getAddColumnStatementsForNewFields(Class<T> entityClass) throws SQLException {

        Set<String> sqlColumn = getSQLColumnNames(entityClass);

        List<Field> fields = Arrays.stream(entityClass.getDeclaredFields())
                .filter(f -> !f.isAnnotationPresent(Id.class))
                .filter(f -> f.isAnnotationPresent(Column.class))
                .collect(Collectors.toList());

        List<String> result = new ArrayList<>();

        for (Field field : fields) {

            String fieldName = field.getAnnotationsByType(Column.class)[0].name();

            if (sqlColumn.contains(fieldName)) {
                continue;
            }

            String sqlType = getSQLType(field.getType());

            result.add(String.format("ADD COLUMN %s %s", fieldName, sqlType));
        }

        return String.join(",", result);
    }

    private Set<String> getSQLColumnNames(Class<T> entityClass) throws SQLException {
        String schemaQuery = "select COLUMN_NAME from information_schema." +
                " columns c where c.TABLE_SCHEMA = 'custom-orm'" +
                " and COLUMN_NAME != 'id'" +
                " and TABLE_NAME = 'users'";

        PreparedStatement statement = connection.prepareStatement(schemaQuery);
        ResultSet resultSet = statement.executeQuery();

        Set<String> result = new HashSet<>();

        while (resultSet.next()) {

            String columnName = resultSet.getString("COLUMN_NAME");

            result.add(columnName);
        }

        return result;
    }

}
