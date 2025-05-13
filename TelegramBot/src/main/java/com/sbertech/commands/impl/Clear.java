package com.sbertech.commands.impl;

import com.sbertech.bot.KeyboardFactory;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;

@CommandInfo(name = "clear", description = "очищает все товары")
@ButtonName(name = "\uD83D\uDDD1\uFE0F Удалить все товары")
@NeedsArgs
public class Clear extends Command {

    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE ChatId = ?";
        String[] params = {String.valueOf(chat_id)};

        database.execUpdate(sql, params);
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Вы точно хотите удалить все товары?", "Все товары удалены из отслеживаемых"};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {KeyboardFactory.confirmButton()};
    }
}
