package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "add", description = "Добавляет в список задачу")
public class Add extends Command{

    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "INSERT INTO tasks (Name, Status) VALUES (?, ?)";
        String name = args[1];
        String status = "Not Done";
        String[] params = {name, status};

        database.execUpdate(sql, params);
    }
}
