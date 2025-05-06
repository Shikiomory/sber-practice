package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;

import java.sql.SQLException;

@CommandInfo(name = "start", description = "Вступительное сообщение с помощью")
public class Start extends Command {

    @Override
    public void action(String[] args) throws SQLException {

    }

    @Override
    public String getMsg() {
        return "Привет, я - твой персональный помощник по контролю цен в магазине!\n" +
                "Отправь мне ссылку на товар и я пришлю тебе уведомление, когда цена на него изменится!\n\n" +
                "Cписок доступных команд:\n" +
                "/add <ссылка> - добавить товар\n" +
                "/delete <номер товара>- удалить товар из отслеживаемых\n" +
                "/show - показать отслеживаемые товары\n" +
                "/clear - очистить список отслеживаемых товаров";
    }
}
