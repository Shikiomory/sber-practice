package com.sbertech.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CommandInfo(name = "add", description = "Добавляет в список задачу")
public class Add extends Command{

    @Override
    public void action(String[] args, Connection connection) throws SQLException {
        String sql = "INSERT INTO users (Name, Status) VALUES (?, ?)";
        String task = args[1];
        String status = "uncompleted";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, task);
            statement.setString(2, status);
            statement.executeUpdate();
        }
    }
}
