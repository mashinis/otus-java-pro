package ru.mashinis.db.interaction.ex05_repositories;

import ru.mashinis.db.interaction.ex05_repositories.for_remove.UsersDao;
import ru.mashinis.db.interaction.ex05_repositories.for_remove.UsersDaoImpl;

public class Application {
    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            dataSource = new DataSource("jdbc:h2:file:./db;MODE=PostgreSQL");
            dataSource.connect();

            DbMigrator dbMigrator = new DbMigrator(dataSource);
            dbMigrator.migrate();

            UsersDao usersDao = new UsersDaoImpl(dataSource);
            System.out.println(usersDao.findAll());

            AbstractRepository<User> repository = new AbstractRepository<>(dataSource, User.class);
            User user = new User("bob", "123", "bob");
            repository.create(user);

            System.out.println(usersDao.findAll());

            AbstractRepository<Account> accountAbstractRepository = new AbstractRepository<>(dataSource, Account.class);
            Account account = new Account(100L, "credit", "blocked");
            accountAbstractRepository.create(account);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSource.disconnect();
        }
    }
}
