package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.CommandInfo;
import com.sbertech.util.PriceFormatter;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@CommandInfo(name = "show", description = "Показывает список всех задач")
@ButtonName(name = "\uD83D\uDCCB Посмотреть товары")
public class Show extends Command {
    private String returnMsg;

    @Override
    public void action(String[] args, long chat_id) throws SQLException {
        PriceFormatter priceFormatter = new PriceFormatter();
        String sql = String.format("SELECT * FROM tasks WHERE ChatId = '%s'", String.valueOf(chat_id));
        returnMsg = "";
        List<Map<String, Object>> rows = database.execQuery(sql);
        for (Map<String, Object> row : rows) {
            for (String elem: row.keySet()) {
                if (elem.equalsIgnoreCase("Price")) {
                    returnMsg += String.format("%s: %s\n",elem, priceFormatter.format(Float.valueOf((String)row.get(elem))));
                }
                else if (!elem.equalsIgnoreCase("ChatId") && !elem.equalsIgnoreCase("Mode")){
                    returnMsg += String.format("%s: %s\n",elem, row.get(elem));
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
}
