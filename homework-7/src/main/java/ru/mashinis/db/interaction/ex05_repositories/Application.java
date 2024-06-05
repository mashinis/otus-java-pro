package ru.mashinis.db.interaction.ex05_repositories;

import ru.mashinis.db.interaction.ex05_repositories.entity.Account;
import ru.mashinis.db.interaction.ex05_repositories.entity.User;

public class Application {
    public static void main(String[] args) {
        DataSource dataSource = null;
        try {
            dataSource = new DataSource("jdbc:h2:file:./db;MODE=PostgreSQL");
            dataSource.connect();

            DbMigrator dbMigrator = new DbMigrator(dataSource);
            dbMigrator.migrate();

            AbstractRepository<User> repository = new AbstractRepository<>(dataSource, User.class);
            System.out.println(repository.findAll());

            User user = new User("bob", "123", "bob");
            repository.create(user);
            System.out.println(repository.findAll());

            AbstractRepository<Account> accountAbstractRepository = new AbstractRepository<>(dataSource, Account.class);
            System.out.println(accountAbstractRepository.findAll());

            Account account = new Account(100L, "credit", "blocked");
            accountAbstractRepository.create(account);
            System.out.println(accountAbstractRepository.findAll());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            dataSource.disconnect();
        }
    }
}
