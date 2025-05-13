package com.sbertech.commands.impl;

import com.sbertech.bot.KeyboardFactory;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.commands.annotation.NeedsArgs;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@CommandInfo(name = "delete", description = "Удаляет из списка задачу")
@ButtonName(name = "\u274C Удалить товар")
@NeedsArgs
public class Delete extends Command {
    private String returnMsg;
    @Override
    public void action(String[] args, long chat_id) throws SQLException, IndexOutOfBoundsException{
        String sql = "Delete From tasks WHERE UID = ? AND ChatId = ?";
        String uid = args[0];
        String[] params = {uid, String.valueOf(chat_id)};

        String sqlSelect = String.format("SELECT * FROM tasks WHERE ChatId = '%s' AND UID = '%s'", String.valueOf(chat_id), uid);
        List<Map<String, Object>> rows = database.execQuery(sqlSelect);
        if (!rows.isEmpty()) {
            database.execUpdate(sql, params);
            returnMsg = "Товар удален из отслеживаемых";
        }
        else {
            returnMsg = "Нет такого товара";
        }
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{"Введите номер товара для удаления", "Вы точно хотите удалить этот товар?", returnMsg};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {KeyboardFactory.cancelButton(), KeyboardFactory.confirmButton()};
    }
}
