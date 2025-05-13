package com.sbertech.bot;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardRow;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class KeyboardFactory {

    public static ReplyKeyboardMarkup mainKeyboard() {

        //TODO
        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("\uD83D\uDED2 Добавить товар");
        row.add("\u274C Удалить товар");
        row.add("\u2753 Помощь");
        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("\uD83D\uDDD1\uFE0F Удалить все товары");
        row.add("\uD83D\uDCCB Посмотреть товары");
        keyboardRows.add(row);
        return ReplyKeyboardMarkup.builder().keyboard(keyboardRows).resizeKeyboard(true).build();
    }

    public static InlineKeyboardMarkup cancelButton() {
        InlineKeyboardButton cancel = InlineKeyboardButton.builder().text("Отменить команду").callbackData("cancel").build();
        InlineKeyboardRow inlineKeyboardButtons = new InlineKeyboardRow();
        inlineKeyboardButtons.add(cancel);
        return InlineKeyboardMarkup.builder().keyboardRow(inlineKeyboardButtons).build();
    }

    public static InlineKeyboardMarkup modeButtons() {
        List<InlineKeyboardRow> keyboard = new ArrayList<>();
        InlineKeyboardRow inlineKeyboarRow = new InlineKeyboardRow();
        inlineKeyboarRow.add(InlineKeyboardButton.builder().text("\u0031\uFE0F\u20E3").callbackData("1").build());
        inlineKeyboarRow.add(InlineKeyboardButton.builder().text("\u0032\uFE0F\u20E3").callbackData("2").build());
        inlineKeyboarRow.add(InlineKeyboardButton.builder().text("\u0033\uFE0F\u20E3").callbackData("3").build());

        keyboard.add(inlineKeyboarRow);

        inlineKeyboarRow = new InlineKeyboardRow();
        inlineKeyboarRow.add(InlineKeyboardButton.builder().text("Отменить команду").callbackData("cancel").build());
        keyboard.add(inlineKeyboarRow);
        return InlineKeyboardMarkup.builder().keyboard(keyboard).build();
    }

    public static InlineKeyboardMarkup confirmButton() {
        InlineKeyboardRow inlineKeyboardButtons = new InlineKeyboardRow();
        inlineKeyboardButtons.add(InlineKeyboardButton.builder().text("Да").callbackData("confirm").build());
        inlineKeyboardButtons.add(InlineKeyboardButton.builder().text("Нет").callbackData("cancel").build());
        return InlineKeyboardMarkup.builder().keyboardRow(inlineKeyboardButtons).build();
    }
}
