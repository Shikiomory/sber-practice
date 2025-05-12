package com.sbertech.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyobardFactory {

    public static InlineKeyboardMarkup cancelButton() {
        InlineKeyboardButton cancel = InlineKeyboardButton.builder().text("Отменить команду").callbackData("/cancel").build();
        InlineKeyboardRow inlineKeyboardButtons = new InlineKeyboardRow();
        inlineKeyboardButtons.add(cancel);
        return InlineKeyboardMarkup.builder().keyboardRow(inlineKeyboardButtons).build();
    }
}
