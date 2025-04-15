package com.sbertech.commands;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@CommandInfo(name = "show", description = "Показывает список всех задач")
public class Show extends Command{

    @Override
    public void action(String[] args, Connection connection) throws SQLException {
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
}
