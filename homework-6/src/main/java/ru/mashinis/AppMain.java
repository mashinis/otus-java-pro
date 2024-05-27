package ru.mashinis;

import ru.mashinis.addtestdata.DatabaseInitializer;
import ru.mashinis.proxy.Database;
import ru.mashinis.proxy.DatabaseProxy;

public class AppMain {
    public static void main(String[] args) {
        DatabaseInitializer.createDatabaseAndTestData();

        Database proxy = new DatabaseProxy();
        proxy.read("users");

        proxy.insert("users", "Emily");
        proxy.read("users");

        proxy.update("users", 1, "Debra");
        proxy.read("users");

        proxy.delete("users", 2);
        proxy.read("users");
    }
}
