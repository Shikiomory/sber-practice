package com.sbertech.database;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.sql.*;
import java.util.*;

public class Db {
    private static final Logger log = LoggerFactory.getLogger(Db.class);
    private final String URL;
    private final String USER;
    private final String PASSWORD;
    private Connection connection;
    private final String path = "TelegramBot\\src\\main\\java\\com\\sbertech\\resources\\database.csv";

    public Db(String URL, String USER, String  PASSWORD) {
        this.URL = URL;
        this.USER = USER;
        this.PASSWORD = PASSWORD;
        initTable();
    }

    private void createTable(Connection connection) throws SQLException {
        String sql = """
            CREATE TABLE IF NOT EXISTS tasks (
            UID INT PRIMARY KEY AUTO_INCREMENT,
            productId INT,
            Name VARCHAR(200),
            Url VARCHAR(200),
            Price FLOAT,
            Mode INT,
            ChatId VARCHAR(200),
            UNIQUE (ChatId, productId)
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


    public List<Map<String, Object>> execQuery(String sql, String[] args) throws SQLException {
        List<Map<String, Object>> resultList = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            for (int i = 0; i < args.length; i++) {
                statement.setString(i + 1, args[i]);
            }
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Map<String, Object> row = new LinkedHashMap<>();
                    row.put("productId", resultSet.getInt("productId"));
                    row.put("Name", resultSet.getString("Name"));
                    row.put("Url", resultSet.getString("Url"));
                    row.put("Price", resultSet.getString("Price"));
                    row.put("ChatId", resultSet.getString("ChatId"));
                    row.put("Mode", resultSet.getString("Mode"));
                    resultList.add(row);
                }
            }
        }
        return resultList;
    }

}
