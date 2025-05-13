package com.sbertech.commands.impl;

import com.sbertech.bot.KeyboardFactory;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;
import com.sbertech.service.Parser;
import com.sbertech.service.SelParser;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;

@CommandInfo(name = "add", description = "Добавляет в список задачу")
@ButtonName(name = "\uD83D\uDED2 Добавить товар")
@NeedsArgs
public class Add extends Command {
    private String returnMsg = "";
    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "INSERT INTO tasks (Name, Url, Price, Mode, ChatId) VALUES (?, ?, ?, ?, ?)";
        String url = args[0];
        String mode = args[1];
        Parser parser = new SelParser(url);
        String name = parser.getName();
        String price = String.valueOf(parser.getPrice());
        parser.close();
        if (price != "-1" && name != "\0") {
            if (Integer.valueOf(mode) > 3 || Integer.valueOf(mode) < 0) {
                returnMsg = "Нет такого режима отслеживания!";
            } else {
                String[] params = {name, url, price, mode, String.valueOf(chat_id)};
                database.execUpdate(sql, params);

                returnMsg = "Товар добавлен в отслеживаемые";
            }
        }

        else {
            returnMsg = "Проблема с добавлением товара, некорректная ссылка.";
        }
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Отправьте ссылку на товар.",
                "Выберите режим отслеживания:\n" +
                "1) Отслеживать только снижение цены\n" +
                "2) Отслеживать только повышение цены\n" +
                "3) Отслеживать любое изменение цены\n",
                returnMsg};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {KeyboardFactory.cancelButton(), KeyboardFactory.modeButtons()};
    }
}
