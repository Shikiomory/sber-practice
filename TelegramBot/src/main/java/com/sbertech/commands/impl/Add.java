package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.service.Parser;
import com.sbertech.service.SelParser;

import java.sql.SQLException;

@CommandInfo(name = "add", description = "Добавляет в список задачу")
public class Add extends Command {
    private String returnMsg;
    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "INSERT INTO tasks (Name, Url, Price, ChatId) VALUES (?, ?, ?, ?)";
        String url = args[1];
        String chatid = args[2];
        Parser parser = new SelParser(url);
        String name = parser.getName();
        String price = String.valueOf(parser.getPrice());
        parser.close();
        if (price != "-1" && name != "\0") {
            String[] params = {name, url, price, chatid};

            database.execUpdate(sql, params);
            database.save2File();

            returnMsg = "Товар добавлен в отслеживаемые";
        }
        else {
            returnMsg = "Проблема с добавлением товара, некорректная ссылка.";
        }
    }

    @Override
    public String getMsg() {
        return returnMsg;
    }
}
