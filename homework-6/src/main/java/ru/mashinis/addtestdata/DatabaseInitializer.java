package ru.mashinis.addtestdata;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseInitializer {
    private static Connection connection;

    static {
        try {
            connection = DriverManager.getConnection("jdbc:h2:mem:test;DB_CLOSE_DELAY=-1", "sa", "");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void createDatabaseAndTestData() {
        try  {
            Statement statement = connection.createStatement();
            String createTableSQL = "CREATE TABLE IF NOT EXISTS users ("
                    + "id INT AUTO_INCREMENT PRIMARY KEY, "
                    + "name VARCHAR(20))";
            statement.executeUpdate(createTableSQL);

            String insertDataSQL = "INSERT INTO users (name) VALUES "
                    + "('John'), "
                    + "('Alice'), "
                    + "('Bob')";
            statement.executeUpdate(insertDataSQL);

            System.out.println("The table has been created and test data has been added to it.");

        } catch (SQLException e) {
            System.out.println("Error when creating a database or adding data.");
            e.printStackTrace();
        }
    }
}
