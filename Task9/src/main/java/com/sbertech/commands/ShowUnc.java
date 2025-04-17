package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "showunc", description = "Показывает список всех невыполненных задач")
public class ShowUnc extends Command{

    @Override
    public void action(String[] args) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE Status = 'Not Done'";

        System.out.println("Список невыполненных задач:");
        database.execQuery(sql, args);
    }
}
