package com.sbertech.commands.manager;

import com.sbertech.bot.CheckerBot;
import com.sbertech.commands.Command;

public class CommandHandler {
    CommandManager commandManager;
    StateManager stateManager;
    CheckerBot checkerBot;
    Command command;
    public CommandHandler(CommandManager commandManager, StateManager stateManager) {
        this.commandManager = commandManager;
        this.stateManager = stateManager;
    }

    public String handleCommand(String commandName, long chat_id) {
        command = commandManager.getCommand(commandName);
        if (command != null && commandManager.needsArgs(commandName)) {
            CommandState commandState = new CommandState(commandName);
            stateManager.setState(chat_id, commandState);
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

        commandState.collectArgs(arg);
        String[] messages = command.getMsg();
        if(currentStep < messages.length - 1) {
            commandState.nextState();
            stateManager.setState(chat_id, commandState);
        }
        else {
            commandManager.exec(commandState.getCommand() + " " + commandState.getArgs(), chat_id);
//            System.out.println(commandState.getCommand() + commandState.getArgs());
            stateManager.clearState(chat_id);
        }
        return messages[currentStep];
    }
}
