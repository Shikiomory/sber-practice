package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "show", description = "Показывает список всех задач")
public class Show extends Command{

    @Override
    public void action(String[] args) throws SQLException {
        String sql = "SELECT * FROM tasks";

        System.out.println("Список задач:");
        database.execQuery(sql, args);
    }
}
