package com.sbertech.commands.manager;

import com.sbertech.bot.KeyboardFactory;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

public class MessageHandler {
    private CommandHandler commandHandler;
    private NonComandHandler nonComandHandler;
    private StateManager stateManager;
    public MessageHandler(CommandManager commandManager) {
        stateManager = new StateManager();
        commandHandler = new CommandHandler(commandManager, stateManager);
        nonComandHandler = new NonComandHandler(commandManager);
    }

    public String handleMessage(String msg_text, long chat_id) {
        String returnMsg;
        if (msg_text.startsWith("/")) {
            returnMsg = commandHandler.handleCommand(msg_text.substring(1), chat_id);
        }
        else if (stateManager.getState(chat_id) != null){
            returnMsg = commandHandler.handleMultiCommand(msg_text, chat_id);
        }

        else {
            returnMsg = nonComandHandler.handleNonCommand(chat_id);
        }
        return returnMsg;
    }

    public boolean isActiveCommand(long chat_id) {
        return stateManager.getState(chat_id) != null;
    }

    public InlineKeyboardMarkup getKeyboard() {
        return commandHandler.getKeyboard();
    }
}
