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
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;


public class CheckerBot implements LongPollingSingleThreadUpdateConsumer{
    private TelegramClient telegramClient;
    private MessageHandler messageHandler;
    private CommandManager commandManager;
    private ReplyKeyboardMarkup keyboardMarkup;
    public CheckerBot(String botToken, CommandManager commandManager) {
        this.commandManager = commandManager;
        telegramClient = new OkHttpTelegramClient(botToken);
        messageHandler = new MessageHandler(commandManager);
        keyboardMarkup = KeyboardFactory.mainKeyboard();
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

            String command = commandManager.getCommandName(msg_text);

            answer =  messageHandler.handleMessage(command, chat_id);
            sendMsg(answer, chat_id);

        }
    }

    public void sendMsg(String message, long chat_id) {
        SendMessage msg;

        if (messageHandler.isActiveCommand(chat_id)) {
            msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(KeyboardFactory.cancelButton()).build();
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

    public void notifyUser(String message, long chat_id) {
        SendMessage msg = SendMessage.builder().chatId(chat_id).text(message).replyMarkup(keyboardMarkup).build();

        try {
            telegramClient.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
