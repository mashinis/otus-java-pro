package ru.mashinis.proxy;

import java.sql.*;

public class RealDatabase implements Database {
    private Connection connection;

    public RealDatabase() {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void insert(String nameTable, String data) {
        if (nameTable == null || data == null) {
            throw new IllegalArgumentException("Table name and data cannot be null");
        }
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT INTO " + nameTable + "(name) VALUES(?)");
            statement.setString(1, data);
            statement.executeUpdate();
            System.out.println("Inserted data into real database: " + data);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void update(String nameTable, int id, String newData) {
        if (nameTable == null || newData == null) {
            throw new IllegalArgumentException("Table name and new data cannot be null");
        }
        try {
            PreparedStatement statement = connection.prepareStatement("UPDATE " + nameTable + " SET name=? WHERE id=?");
            statement.setString(1, newData);
            statement.setInt(2, id);
            statement.executeUpdate();
            System.out.println("Updated data in real database with id " + id + ": " + newData);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void delete(String nameTable, int id) {
        if (nameTable == null) {
            throw new IllegalArgumentException("Table name cannot be null");
        }
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM " + nameTable + " WHERE id=?");
            statement.setInt(1, id);
            statement.executeUpdate();
            System.out.println("Deleted data from real database with id " + id);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void read(String nameTable) {
        if (nameTable == null) {
            throw new IllegalArgumentException("Table name cannot be null");
        }
        try {
            Statement statement = connection.createStatement();
            String selectDataSQL = "SELECT * FROM " + nameTable;
            ResultSet resultSet = statement.executeQuery(selectDataSQL);

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");

                System.out.println("ID: " + id + ", Name: " + name);
            }

        } catch (SQLException e) {
            System.out.println("Ошибка при выборке данных из базы данных.");
            e.printStackTrace();
        }
    }
}
