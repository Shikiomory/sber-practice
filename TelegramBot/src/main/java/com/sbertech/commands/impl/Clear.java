package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;

import java.sql.SQLException;

@CommandInfo(name = "clear", description = "очищает все товары")
public class Clear extends Command {

    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE Name = ?";
        String name = args[1];
        String[] params = {name};

        database.execUpdate(sql, params);
        database.save2File();
    }

    @Override
    public String getMsg() {
        return "Все товары удалены из отслеживаемых";
    }
}
