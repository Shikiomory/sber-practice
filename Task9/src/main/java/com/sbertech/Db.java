package com.sbertech;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.*;

public class Db {
    private static final Logger log = LoggerFactory.getLogger(Db.class);
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Connection connection;

    Db(String URL, String USER, String  PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        initTable();
    }

    private void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS tasks (
                UID INT PRIMARY KEY AUTO_INCREMENT,
                Name VARCHAR(50),
                Status VARCHAR(50)
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица создана!");
        }
    }

    private void initTable() {
        try  {
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
            System.out.println("Соединение с H2 установлено!");
            if (connection != null) {
                createTable(connection);
            }
        } catch (SQLException e) {
            log.error("Ошибка при инициализации таблицы", e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void close() throws SQLException {
        if (connection != null) {
            connection.close();
        }
    }

    public void execUpdate(String sql, String[] args) throws SQLException {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setString(i + 1, args[i]);
            }
            statement.executeUpdate();
        }
    }

    public void execQuery(String sql, String[] args) throws SQLException {
        try (Statement statement = connection.createStatement()){

            try(ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    int uid = resultSet.getInt("UID");
                    String name = resultSet.getString("Name");
                    String status = resultSet.getString("Status");
                    System.out.printf("UID: %d, Name: %s, Status: %s%n", uid, name, status);
                }
            }
        }
    }
}
