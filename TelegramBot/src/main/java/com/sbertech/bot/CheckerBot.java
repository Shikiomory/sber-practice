package com.sbertech.bot;
import com.sbertech.commands.manager.CommandManager;
import com.sbertech.commands.manager.MessageHandler;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.updatingmessages.EditMessageText;
import org.telegram.telegrambots.meta.api.objects.CallbackQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CheckerBot implements LongPollingSingleThreadUpdateConsumer{
    private TelegramClient telegramClient;
    private MessageHandler messageHandler;
    private ReplyKeyboardMarkup keyboardMarkup;
    private Map<String,String > commands = new HashMap<>();
    private KeyobardFactory keyobardFactory;
    public CheckerBot(String botToken, CommandManager commandManager) {
        telegramClient = new OkHttpTelegramClient(botToken);
        messageHandler = new MessageHandler(commandManager);
        keyboardMarkup = initKeyboard();
        commands = Map.of(
                "\uD83D\uDED2 Добавить товар", "/add",
                "\uD83D\uDCCB Посмотреть товары", "/show",
                "\u274C Удалить товар", "/delete",
                "\uD83D\uDDD1\uFE0F Удалить все товары", "/clear",
                "\u2753 Помощь", "/help");
    }

    public ReplyKeyboardMarkup initKeyboard() {

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
    @Override
    public void consume(Update update) {
        // TODO
        if (update.hasCallbackQuery()) {
            handleCallback(update.getCallbackQuery());
            return;
        }

        if (update.hasMessage() && update.getMessage().hasText()) {
            String answer;
            String msg_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String command = commands.getOrDefault(msg_text, msg_text);

            answer =  messageHandler.handleMessage(command, chat_id);
            sendMsg(answer, chat_id);


        }
    }

    public void sendMsg(String message, long chat_id) {
        SendMessage msg;

        if (messageHandler.isActiveCommand(chat_id)) {
            msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(KeyobardFactory.cancelButton()).build();
        } else {
            msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(keyboardMarkup).build();
        }

        try {
            telegramClient.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    private void handleCallback(CallbackQuery callbackQuery) {
        if (callbackQuery.getData().equals("/cancel")) {
            long chat_id = callbackQuery.getMessage().getChatId();
            String answer = messageHandler.handleMessage(callbackQuery.getData(), chat_id);
            EditMessageText editMsg = EditMessageText.builder().chatId(chat_id).messageId(callbackQuery.getMessage().getMessageId()).text(answer).build();
            try {
                telegramClient.execute(editMsg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }
}
