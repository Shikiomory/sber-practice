package com.sbertech.service;

import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

public class Messager {
    private ReplyKeyboardMarkup keyboardMarkup;
    public void sendMsg(String message, String chat_id) {
        SendMessage msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(keyboardMarkup).build();

//        try {
//            telegramClient.execute(msg);
//        } catch (TelegramApiException e) {
//            e.printStackTrace();
//        }
    }
}
