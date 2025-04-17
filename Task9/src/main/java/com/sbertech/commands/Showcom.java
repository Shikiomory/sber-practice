package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "showcom", description = "Показывает список всех задач")
public class Showcom extends Command{

    @Override
    public void action(String[] args) throws SQLException {
        String sql = "SELECT * FROM tasks WHERE Status = 'Done'";

        System.out.println("Список выполненных задач:");
        database.execQuery(sql, args);
    }
}
