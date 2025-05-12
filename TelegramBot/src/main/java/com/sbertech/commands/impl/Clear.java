package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;

import java.sql.SQLException;

@CommandInfo(name = "clear", description = "очищает все товары")
@ButtonName(name = "\uD83D\uDDD1\uFE0F Удалить все товары")
public class Clear extends Command {

    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE ChatId = ?";
        String[] params = {String.valueOf(chat_id)};

        database.execUpdate(sql, params);
//        database.save2File();
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Все товары удалены из отслеживаемых"};
        return messages;
    }
}
