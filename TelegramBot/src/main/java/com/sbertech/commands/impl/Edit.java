package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;

import java.sql.SQLException;

@CommandInfo(name = "edit", description = "Меняет цену товара")
public class Edit extends Command {
    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "UPDATE tasks SET Price = ? WHERE Name = ?";
        String name = args[1];
        String price = args[2];
        String[] params = {price, name};

        database.execUpdate(sql, params);
    }

    @Override
    public String getMsg() {
        return "Цена товара изменена";
    }
}
