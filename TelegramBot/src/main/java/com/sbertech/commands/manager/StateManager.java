package com.sbertech.commands.manager;

import com.sbertech.bot.CheckerBot;
import com.sbertech.commands.Command;

import java.util.HashMap;
import java.util.Map;

public class StateManager {
    private CheckerBot checkerBot;
    private Map<Long, CommandState> users = new HashMap<Long, CommandState>();
    private int currentState;
    private String commandName;

    public void setState(long chat_id, CommandState commandState) {
        users.put(chat_id, commandState);
    }

    public void clearState(long chat_id) {
        users.remove(chat_id);
    }

    public CommandState getState(long chat_id) {
        return users.get(chat_id);
    }
}

class CommandState {
    private int currentState;
    private String commandName;
    private String args;

    public CommandState(String commandName) {
        this.commandName = commandName;
        this.currentState = 0;
        args = "";
    }

    public void nextState() {
        currentState++;
    }

    public int getCurrentState() {
        return currentState;
    }
    public void collectArgs(String arg) {
//        args[currentState] = arg;
        args += arg + " ";
    }

    public String getCommand() {
        return commandName;
    }

    public String getArgs() {
        return args;
    }
}