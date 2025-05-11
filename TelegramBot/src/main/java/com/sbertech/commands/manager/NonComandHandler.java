package com.sbertech.commands.manager;

public class NonComandHandler {
    private CommandManager commandManager;
    public NonComandHandler(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
    public String handleNonCommand(long chat_id) {
        commandManager.exec("help", chat_id);
        return commandManager.getCommand("help").getMsg()[0];
    }
}
