package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;

import java.sql.SQLException;

@CommandInfo(name = "delete", description = "Удаляет из списка задачу")
@NeedsArgs
public class Delete extends Command {

    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE Uid = ?";
        String name = args[1];
        String[] params = {name};

        database.execUpdate(sql, params);
//        database.save2File();
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Введите номер товара для удаления", "Товар удален из отслеживаемых"};
        return messages;
    }
}
