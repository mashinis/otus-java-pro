package ru.mashinis.db.interaction.ex05_repositories.for_remove;

import ru.mashinis.db.interaction.ex05_repositories.entity.User;

import java.sql.SQLException;
import java.util.List;

public interface UsersDao {
    List<User> findAll() throws SQLException;
}
