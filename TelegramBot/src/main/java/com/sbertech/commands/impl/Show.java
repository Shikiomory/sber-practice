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
        String sql = String.format("SELECT * FROM tasks WHERE ChatId = '%s'", String.valueOf(chat_id));
        returnMsg = "";
        List<Map<String, Object>> rows = database.execQuery(sql);
        for (Map<String, Object> row : rows) {
            for (String elem: row.keySet()) {
                if (elem.equalsIgnoreCase("Price")) {
                    returnMsg += String.format("%s: %s\n", fieldFormatter.getFormName(elem), priceFormatter.format(Float.valueOf((String)row.get(elem))));
                }
                else if (!elem.equalsIgnoreCase("ChatId") && !elem.equalsIgnoreCase("Mode")){
                    returnMsg += String.format("%s: %s\n", fieldFormatter.getFormName(elem), row.get(elem));
                }
            }
            returnMsg += "\n";
        }
    }

    @Override
    public String[] getMsg() {
        messages = new String[]{returnMsg.isEmpty() ? "Нет отслеживаемых товаров" : "Список отслеживаемых товаров:\n" + returnMsg};
        return messages;
    }

    @Override
    public InlineKeyboardMarkup[] getKeyboard() {
        return new InlineKeyboardMarkup[] {};
    }
}
