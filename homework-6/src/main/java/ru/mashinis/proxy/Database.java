package ru.mashinis.proxy;

public interface Database {
    void insert(String nameTable, String data);
    void update(String nameTable, int id, String newData);
    void delete(String nameTable, int id);
    void read(String nameTable);
}
