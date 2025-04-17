package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "delete", description = "Удаляет из списка задачу")
public class Delete extends Command{

    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From users WHERE Name = ?";
        String name = args[1];
        String[] params = {name};

        database.execUpdate(sql, params);
    }
}
