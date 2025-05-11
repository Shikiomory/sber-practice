package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;
import com.sbertech.service.Parser;
import com.sbertech.service.SelParser;

import java.sql.SQLException;

@CommandInfo(name = "add", description = "Добавляет в список задачу")
@NeedsArgs
public class Add extends Command {
    private String returnMsg = "";
    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        System.out.println(args[0]);
        String sql = "INSERT INTO tasks (Name, Url, Price, ChatId) VALUES (?, ?, ?, ?)";
        String url = args[1];
        Parser parser = new SelParser(url);
        String name = parser.getName();
        String price = String.valueOf(parser.getPrice());
        parser.close();
        if (price != "-1" && name != "\0") {
            String[] params = {name, url, price, String.valueOf(chat_id)};

            database.execUpdate(sql, params);
//            database.save2File();

            returnMsg = "Товар добавлен в отслеживаемые";
        }
        else {
            returnMsg = "Проблема с добавлением товара, некорректная ссылка.";
        }
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Отправьте ссылку на товар.", "Выберите режим отслеживания:\n" +
                "1) Отслеживать только снижение цены\n" +
                "2) Отслеживать только повышение цены\n" +
                "3) Отслеживать любое изменение цены\n" +
                "4) Отслеживать изменение цены в заданном диапазоне", "Товар добавлен в отслеживаемые"};
        return messages;
    }
}
