package com.sbertech.bot;
import com.sbertech.commands.manager.CommandManager;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

public class CheckerBot implements LongPollingSingleThreadUpdateConsumer{
    private TelegramClient telegramClient;
    private CommandManager console;
    public CheckerBot(String botToken, CommandManager console) {
        telegramClient = new OkHttpTelegramClient(botToken);
        this.console = console;
    }
    @Override
    public void consume(Update update) {
        // TODO
        String answer;
        if (update.hasMessage() && update.getMessage().hasText()) {
            String msg_text = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();

            if (msg_text.startsWith("/")) {
                answer = console.exec(msg_text.substring(1) + " " + chat_id);
            }
            else {
                answer = console.exec("start" + " " + chat_id);
            }

            SendMessage msg = SendMessage.builder().chatId(chat_id).text(answer).build();
            try {
                telegramClient.execute(msg);
                System.out.printf("Пользователь %s, выполнил команду %s\n", chat_id, msg_text);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    public void sendMsg(String message, String chat_id) {
        SendMessage msg = SendMessage.builder().chatId(chat_id).text(message).build();
        try {
            telegramClient.execute(msg);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }
}
