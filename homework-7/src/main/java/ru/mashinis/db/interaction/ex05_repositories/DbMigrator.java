package ru.mashinis.db.interaction.ex05_repositories;

import ru.mashinis.db.interaction.exceptions.ApplicationInitializationException;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class DbMigrator {
    private DataSource dataSource;

    public DbMigrator(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public void migrate() {
        try {
            Connection connection = dataSource.getConnection();
            Statement statement = connection.createStatement();
            BufferedReader reader = new BufferedReader(new FileReader("init.sql"));

            StringBuilder sql = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sql.append(line).append("\n");
            }

            statement.execute(sql.toString());

        } catch (SQLException | IOException e) {
            e.printStackTrace();
            throw new ApplicationInitializationException();
        }
    }

}
