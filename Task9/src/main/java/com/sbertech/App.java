package com.sbertech;
import java.sql.*;
import java.util.Scanner;

public class App {
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    private static Boolean exit = false;
    public static void main(String[] args) throws SQLException {
//        try (Connection connection = DriverManager.getConnection(URL, USER, PASSWORD)) {
//            System.out.println("Соединение с H2 установлено!");
//            // Создание таблицы
//            createTable(connection);
//            // Вставка данных
//            insertData(connection);
////            // Чтение данных
//            readData(connection);
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
        Scanner scanner = new Scanner(System.in);
        Db db = new Db(URL, USER, PASSWORD);
        while (!exit) {
            String a = scanner.next();
            db.add(a, "Uncompleted");
//            System.out.println(a);
            db.show();
            if (a.equalsIgnoreCase("exit")) {
                exit = true;
            }
        }
    }

    private static void createTable(Connection connection) throws SQLException {
        String sql = """
                CREATE TABLE IF NOT EXISTS users (
                id INT PRIMARY KEY AUTO_INCREMENT,
                name VARCHAR(50),
                email VARCHAR(50)
                )
                """;

        try (Statement statement = connection.createStatement()) {
            statement.execute(sql);
            System.out.println("Таблица создана!");
        }
    }

    private static void insertData(Connection connection) throws SQLException {
        String sql = "INSERT INTO users (name, email) VALUES (?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, "Иван Иванов");
            statement.setString(2, "ivan@example.com");
            statement.executeUpdate();

            statement.setString(1, "Мария Петрова");
            statement.setString(2, "maria@example.com");
            statement.executeUpdate();

            System.out.println("Данные добавлены!");
        }
    }

    private static void readData(Connection connection) throws SQLException {
        String sql = "SELECT * FROM users";
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Список пользователей:");
            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String email = resultSet.getString("email");
                System.out.printf("ID: %d, Имя: %s, Email: %s%n", id, name, email);
            }
        }
    }

}