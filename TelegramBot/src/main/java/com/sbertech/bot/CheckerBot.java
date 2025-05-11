package com.sbertech.bot;
import com.sbertech.commands.manager.CommandHandler;
import com.sbertech.commands.manager.CommandManager;
import com.sbertech.commands.manager.MessageHandler;
import com.sbertech.commands.manager.StateManager;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
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
    private CommandManager commandManager;
    private CommandHandler commandHandler;
    private StateManager stateManager;
    private MessageHandler messageHandler;
    private ReplyKeyboardMarkup keyboardMarkup;
    private Map<String,String > commands = new HashMap<>();
    public CheckerBot(String botToken, CommandManager commandManager) {
        telegramClient = new OkHttpTelegramClient(botToken);
        this.commandManager = commandManager;
        messageHandler = new MessageHandler(commandManager);
        keyboardMarkup = initKeyboard();
        commands = Map.of(
                "Добавить товар", "/add",
                "Посмотреть товары", "/show",
                "Удалить товар", "/delete",
                "Удалить все товары", "/clear",
                "Команды", "/help",
                "Отменить действие", "/cancel");
    }

    public ReplyKeyboardMarkup initKeyboard() {

        List<KeyboardRow> keyboardRows = new ArrayList<>();

        KeyboardRow row = new KeyboardRow();
        row.add("Добавить товар");
        row.add("Удалить товар");
        row.add("Помощь");
        keyboardRows.add(row);

        row = new KeyboardRow();

        row.add("Удалить все товары");
        row.add("Посмотреть товары");
        row.add("Отменить действие");
        keyboardRows.add(row);
        return ReplyKeyboardMarkup.builder().keyboard(keyboardRows).build();
    }
    @Override
    public void consume(Update update) {
        // TODO
        String answer;
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            String command = commands.getOrDefault(msg_text, msg_text);

            answer =  messageHandler.handleMessage(command, chat_id);
            sendMsg(answer, String.valueOf(chat_id));


        }
    }

    public void sendMsg(String message, String chat_id) {
        SendMessage msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(keyboardMarkup).build();

        try {
            telegramClient.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
