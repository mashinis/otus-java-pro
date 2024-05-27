package ru.mashinis.proxy;

import java.sql.*;

public class DatabaseProxy implements Database {
    private RealDatabase realDatabase;

    public DatabaseProxy() {
        this.realDatabase = new RealDatabase();
    }

    public void insert(String nameTable, String data) {
        try {
            realDatabase.getConnection().setAutoCommit(false);
            realDatabase.insert(nameTable, data);
            realDatabase.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                realDatabase.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void update(String nameTable, int id, String newData) {
        try {
            realDatabase.getConnection().setAutoCommit(false);
            realDatabase.update(nameTable, id, newData);
            realDatabase.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                realDatabase.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void delete(String nameTable, int id) {
        try {
            realDatabase.getConnection().setAutoCommit(false);
            realDatabase.delete(nameTable, id);
            realDatabase.getConnection().commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                realDatabase.getConnection().rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void read(String nameTable) {
        realDatabase.read(nameTable);
    }
}
