package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;

import java.sql.SQLException;

@CommandInfo(name = "delete", description = "Удаляет из списка задачу")
public class Delete extends Command {

    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE Uid = ?";
        String name = args[1];
        String[] params = {name};

        database.execUpdate(sql, params);
        database.save2File();
    }

    @Override
    public String getMsg() {
        return "Товар удален из отслеживаемых";
    }
}
