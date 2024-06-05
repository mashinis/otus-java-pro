package ru.mashinis.db.interaction.ex05_repositories;

import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryIdField;
import ru.mashinis.db.interaction.ex05_repositories.annotation.RepositoryTable;
import ru.mashinis.db.interaction.exceptions.ApplicationInitializationException;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class AbstractRepository<T> {
    private DataSource dataSource;
    private Class<T> cls;
    private List<Field> cachedFields;
    private Field idField;
    private Method idGetter;
    private Method idSetter;
    private List<Method> cachedGetters;
    private List<Method> cachedSetters;

    public AbstractRepository(DataSource dataSource, Class<T> cls) {
        this.dataSource = dataSource;
        this.cls = cls;
        prepare(cls);
    }

    private void prepare(Class<T> cls) {
        //String tableName = cls.getAnnotation(RepositoryTable.class).title();
        cachedFields = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RepositoryField.class))
                .filter(f -> !f.isAnnotationPresent(RepositoryIdField.class))
                .collect(Collectors.toList());
        idField = Arrays.stream(cls.getDeclaredFields())
                .filter(f -> f.isAnnotationPresent(RepositoryIdField.class))
                .findFirst()
                .orElseThrow(() -> new ApplicationInitializationException("ID field not found"));

        cachedFields.forEach(f -> f.setAccessible(true));
        idField.setAccessible(true);

        cachedGetters = cachedFields.stream()
                .map(this::getGetter)
                .collect(Collectors.toList());

        cachedSetters = cachedFields.stream()
                .map(this::getSetter)
                .collect(Collectors.toList());

        idGetter = getGetter(idField);
        idSetter = getSetter(idField);
    }

    private Method getGetter(Field field) {
        String getterName = "get" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            return cls.getMethod(getterName);
        } catch (NoSuchMethodException e) {
            throw new ApplicationInitializationException("Getter not found for field: " + field.getName(), e);
        }
    }

    private Method getSetter(Field field) {
        String setterName = "set" + field.getName().substring(0, 1).toUpperCase() + field.getName().substring(1);
        try {
            return cls.getMethod(setterName, field.getType());
        } catch (NoSuchMethodException e) {
            throw new ApplicationInitializationException("Setter not found for field: " + field.getName(), e);
        }
    }

    public void create(T entity) {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        StringBuilder query = new StringBuilder("INSERT INTO ").append(tableName).append(" (");
        cachedFields.forEach(f -> query.append(f.getName()).append(", "));
        query.setLength(query.length() - 2);
        query.append(") VALUES (");
        cachedFields.forEach(f -> query.append("?, "));
        query.setLength(query.length() - 2);
        query.append(");");

        try (PreparedStatement psCreate = dataSource.getConnection().prepareStatement(query.toString(), Statement.RETURN_GENERATED_KEYS)) {
            for (int i = 0; i < cachedFields.size(); i++) {
                psCreate.setObject(i + 1, cachedGetters.get(i).invoke(entity));
            }
            psCreate.executeUpdate();
            try (ResultSet generatedKeys = psCreate.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    idSetter.invoke(entity, generatedKeys.getLong(1));
                }
            }
        } catch (Exception e) {
            throw new ApplicationInitializationException(e);
        }
    }

    public Optional<T> findById(Long id) {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        String query = "SELECT * FROM " + tableName + " WHERE " + idField.getName() + " = ?";

        try (PreparedStatement psFindById = dataSource.getConnection().prepareStatement(query)) {
            psFindById.setObject(1, id);
            try (ResultSet rs = psFindById.executeQuery()) {
                if (rs.next()) {
                    T entity = cls.newInstance();
                    idSetter.invoke(entity, rs.getObject(idField.getName()));
                    for (int i = 0; i < cachedFields.size(); i++) {
                        cachedSetters.get(i).invoke(entity, rs.getObject(cachedFields.get(i).getName()));
                    }
                    return Optional.of(entity);
                }
            }
        } catch (Exception e) {
            throw new ApplicationInitializationException(e);
        }
        return Optional.empty();
    }

    public List<T> findAll() {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        String query = "SELECT * FROM " + tableName;
        List<T> resultList = new ArrayList<>();

        try (PreparedStatement psFindAll = dataSource.getConnection().prepareStatement(query);
             ResultSet rs = psFindAll.executeQuery()) {
            while (rs.next()) {
                T entity = cls.newInstance();
                idSetter.invoke(entity, rs.getObject(idField.getName()));
                for (int i = 0; i < cachedFields.size(); i++) {
                    cachedSetters.get(i).invoke(entity, rs.getObject(cachedFields.get(i).getName()));
                }
                resultList.add(entity);
            }
        } catch (Exception e) {
            throw new ApplicationInitializationException(e);
        }
        return resultList;
    }

    public void update(T entity) {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        StringBuilder query = new StringBuilder("UPDATE ").append(tableName).append(" SET ");
        cachedFields.forEach(f -> query.append(f.getName()).append(" = ?, "));
        query.setLength(query.length() - 2);
        query.append(" WHERE ").append(idField.getName()).append(" = ?");

        try (PreparedStatement psUpdate = dataSource.getConnection().prepareStatement(query.toString())) {
            for (int i = 0; i < cachedFields.size(); i++) {
                psUpdate.setObject(i + 1, cachedGetters.get(i).invoke(entity));
            }
            psUpdate.setObject(cachedFields.size() + 1, idGetter.invoke(entity));
            psUpdate.executeUpdate();
        } catch (Exception e) {
            throw new ApplicationInitializationException(e);
        }
    }

    public void deleteById(Long id) {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        String query = "DELETE FROM " + tableName + " WHERE " + idField.getName() + " = ?";

        try (PreparedStatement psDeleteById = dataSource.getConnection().prepareStatement(query)) {
            psDeleteById.setObject(1, id);
            psDeleteById.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationInitializationException(e);
        }
    }

    public void deleteAll() {
        String tableName = cls.getAnnotation(RepositoryTable.class).title();
        String query = "DELETE FROM " + tableName;

        try (PreparedStatement psDeleteAll = dataSource.getConnection().prepareStatement(query)) {
            psDeleteAll.executeUpdate();
        } catch (SQLException e) {
            throw new ApplicationInitializationException(e);
        }
    }
}
