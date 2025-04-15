package com.sbertech.commands;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@CommandInfo(name = "done", description = "Отмечает выбранную задачу выполненной")
public class Done extends Command{
    @Override
    public void action(String[] args, Connection connection) throws SQLException {
        String name = args[1];
        String sql = "UPDATE users SET Status = 'Done' WHERE Name = '" + name + "'";
//        String sql = "UPDATE users SET Status = ? WHERE Name = ?;";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
//            statement.setString(1, "Done");
            statement.executeUpdate();
        }
    }
}
