package com.sbertech.commands.impl;

import com.sbertech.bot.KeyboardFactory;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.util.FieldFormatter;
import com.sbertech.util.PriceFormatter;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@CommandInfo(name = "show", description = "Показывает список всех задач")
@ButtonName(name = "\uD83D\uDCCB Посмотреть товары")
public class Show extends Command {
    private String returnMsg;
    private FieldFormatter fieldFormatter = new FieldFormatter();

    @Override
    public void action(String[] args, long chat_id) throws SQLException {
        PriceFormatter priceFormatter = new PriceFormatter();
        String sql = "SELECT productId, Name, Url, Price, Mode, ChatId FROM tasks WHERE ChatId = ? ORDER BY productId"; // Убрали Mode и ChatId
        returnMsg = "";

        List<Map<String, Object>> rows = database.execQuery(sql, new String[]{String.valueOf(chat_id)});

        if (rows.isEmpty()) {
            returnMsg = "Нет отслеживаемых товаров";
            return;
        }

        StringBuilder sb = new StringBuilder();
        for (Map<String, Object> row : rows) {
            sb.append(String.format("#%d%n", row.get("productId")));

            // Явно обрабатываем нужные поля
            sb.append(String.format("%s: %s%n",
                    fieldFormatter.getFormName("Name"),
                    row.get("Name")));

            sb.append(String.format("%s: %s%n",
                    fieldFormatter.getFormName("Url"),
                    row.get("Url")));

            String formattedPrice = priceFormatter.format(
                    Float.parseFloat(row.get("Price").toString()));
            sb.append(String.format("%s: %s%n%n",
                    fieldFormatter.getFormName("Price"),
                    formattedPrice));
        }

        returnMsg = sb.toString();
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{returnMsg.startsWith("#")
                ? "Список отслеживаемых товаров:\n" + returnMsg
                : returnMsg};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {};
    }
}
