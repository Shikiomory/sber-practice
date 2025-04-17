package com.sbertech;

import java.sql.*;

public class Db {
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

    void add(String task, String status) throws SQLException {
        String sql = "INSERT INTO users (Name, Status) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task);
            statement.setString(2, status);
            statement.executeUpdate();
        }
    }

    void show() throws SQLException{
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Список задач:");
            while (resultSet.next()) {
                int uid = resultSet.getInt("UID");
                String name = resultSet.getString("Name");
                String status = resultSet.getString("Status");
                System.out.printf("UID: %d, Name: %s, Status: %s%n", uid, name, status);
            }
        }
    }

    void done(String name) throws SQLException{
      String sql = "UPDATE users SET Status = 'Done' WHERE Name = '" + name + "'";
//        String sql = "UPDATE users SET Status = ? WHERE Name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, "Done");
            statement.executeUpdate();
        }
    }

    private void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
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
//            this.connection = connection;
            createTable(connection);
        } catch (SQLException e) {
            e.printStackTrace();
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

//    public void execQuery(String sql, String[] args) throws SQLException {
//        try (PreparedStatement statement = connection.prepareStatement(sql)){
//            for (int i = 0; i < args.length; i++) {
//                statement.setString(i + 1, args[i]);
//            }
//
//            try(ResultSet resultSet = statement.executeQuery(sql)) {
//                while (resultSet.next()) {
//                    int uid = resultSet.getInt("UID");
//                    String name = resultSet.getString("Name");
//                    String status = resultSet.getString("Status");
//                    System.out.printf("UID: %d, Name: %s, Status: %s%n", uid, name, status);
//                }
//            }
//        }
//    }
}
