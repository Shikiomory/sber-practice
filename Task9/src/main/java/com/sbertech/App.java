package com.sbertech;
import java.sql.*;

public class App {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main(String[] args) throws SQLException {
        Db db = new Db(URL, USER, PASSWORD);
        Console console = new Console(db);
        console.exec();
    }
}