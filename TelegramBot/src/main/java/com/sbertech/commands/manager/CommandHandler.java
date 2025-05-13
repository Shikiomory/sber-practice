package com.sbertech.commands.manager;

import com.sbertech.commands.Command;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;

import java.sql.SQLException;

public class CommandHandler {
    CommandManager commandManager;
    StateManager stateManager;
    Command command;
    private InlineKeyboardMarkup keyboardMarkup;
    public CommandHandler(CommandManager commandManager, StateManager stateManager) {
        this.commandManager = commandManager;
        this.stateManager = stateManager;
    }

    public String handleCommand(String commandName, long chat_id) {
        command = commandManager.getCommand(commandName);
        if (command != null && commandManager.needsArgs(commandName)) {
            CommandState commandState = new CommandState(commandName);
            stateManager.setState(chat_id, commandState);
            setKeyboard(command.getKeyboard()[0]);
            commandState.nextState();
        }
        else {
            commandManager.exec(commandName, chat_id);
        }
        return command.getMsg()[0];
    }

    public String handleMultiCommand(String arg, long chat_id) {
        CommandState commandState = stateManager.getState(chat_id);
        int currentStep = commandState.getCurrentState();


        if (arg.equalsIgnoreCase("cancel")) {
            stateManager.clearState(chat_id);
            return "Команда отменена";
        }
        else if (arg.equalsIgnoreCase("confirm")) {
            commandState = stateManager.getState(chat_id);
            String answer = command.getMsg()[currentStep];
            commandState.nextState();
            return answer;
        }

        commandState.collectArgs(arg);
        String[] messages;
        if(currentStep < command.getMsg().length - 1) {
            messages = command.getMsg();
            setKeyboard(command.getKeyboard()[currentStep]);
            commandState.nextState();
            stateManager.setState(chat_id, commandState);
        }
        else {
//            commandManager.exec(commandState.getCommand() + " " + commandState.getArgs(), chat_id);
            try {
                command.action(commandState.getArgs(), chat_id);
                messages = command.getMsg();
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            stateManager.clearState(chat_id);
        }
        return messages[currentStep];
    }

    private void setKeyboard(InlineKeyboardMarkup keyboardMarkup) {
        this.keyboardMarkup = keyboardMarkup;
    }

    public InlineKeyboardMarkup getKeyboard() {
        return keyboardMarkup;
    }
}
