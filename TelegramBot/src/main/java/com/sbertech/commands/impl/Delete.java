package com.sbertech.commands.impl;

import com.sbertech.bot.KeyboardFactory;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;

@CommandInfo(name = "delete", description = "Удаляет из списка задачу")
@ButtonName(name = "\u274C Удалить товар")
@NeedsArgs
public class Delete extends Command {

    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE Uid = ?";
        String uid = args[0];
        String[] params = {uid};

        database.execUpdate(sql, params);
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Введите номер товара для удаления", "Товар удален из отслеживаемых"};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {KeyboardFactory.confirmButton()};
    }
}
