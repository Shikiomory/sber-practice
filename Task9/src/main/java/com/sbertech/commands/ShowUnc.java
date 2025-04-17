package com.sbertech.commands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@CommandInfo(name = "showunc", description = "Показывает список всех невыполненных задач")
public class ShowUnc extends Command{

    @Override
    public void action(String[] args) throws SQLException {
        String sql = "SELECT * FROM users WHERE Status = 'UnDone'";

        Connection connection = database.getConnection();
        try (Statement statement = connection.createStatement();
             ResultSet resultSet = statement.executeQuery(sql)) {
            System.out.println("Список невыполненных задач:");
            while (resultSet.next()) {
                int uid = resultSet.getInt("UID");
                String name = resultSet.getString("Name");
                String status = resultSet.getString("Status");
                System.out.printf("UID: %d, Name: %s, Status: %s%n", uid, name, status);
            }
        }
    }
}
