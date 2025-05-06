package com.sbertech.commands.impl;

import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;

import java.util.HashMap;
import java.util.Map;

@CommandInfo(name = "help", description = "Выводит список доступных команд")
public class Help extends Command {
    private Map<String, Command> commands = new HashMap<>();
    private String returnMsg = "";

    public Help(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void action(String[] args) {
        if (args.length == 1) {
            for (String names : commands.keySet()) {
                Command command = commands.get(names);
                CommandInfo info = command.getClass().getAnnotation(CommandInfo.class);
                returnMsg += String.format("%-8s%s\n", info.name(), info.description());
            }
        }
        else {
            Command command = commands.get(args[1]);

            if (command != null) {
                CommandInfo info = command.getClass().getAnnotation(CommandInfo.class);
                returnMsg += String.format("%-8s%s\n", info.name(), info.description());
            }
            else {
                returnMsg = String.format("Ошибка: неизвестная команда '%s'\n", args[1]);
            }
        }
    }

    @Override
    public String getMsg() {
        return "Список комманд:\n" + returnMsg;
    }
}
