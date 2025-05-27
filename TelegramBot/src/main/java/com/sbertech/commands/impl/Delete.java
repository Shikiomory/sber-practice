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
    public void action(String[] args, long chat_id) throws SQLException {
        try {
            int productIdToDelete = Integer.parseInt(args[0]);
            String chatId = String.valueOf(chat_id);

            // Проверка существования товара
            String checkSql = "SELECT * FROM tasks WHERE ChatId = ? AND productId = ?";
            List<Map<String, Object>> rows = database.execQuery(checkSql, new String[]{chatId, String.valueOf(productIdToDelete)});

            if (rows.isEmpty()) {
                returnMsg = "Нет такого товара";
                return;
            }

            // Удаление товара
            String deleteSql = "DELETE FROM tasks WHERE ChatId = ? AND productId = ?";
            database.execUpdate(deleteSql, new String[]{chatId, String.valueOf(productIdToDelete)});

            // Обновление номеров оставшихся товаров
            String updateSql = "UPDATE tasks SET productId = productId - 1 WHERE ChatId = ? AND productId > ?";
            database.execUpdate(updateSql, new String[]{chatId, String.valueOf(productIdToDelete)});

            returnMsg = "Товар успешно удалён";

        } catch (NumberFormatException e) {
            returnMsg = "Неверный формат номера товара";
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
