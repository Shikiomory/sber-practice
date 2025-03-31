package com.sbertech.commands;

import java.util.HashMap;
import java.util.Map;

public class Help extends Command {
    private Map<String, Command> commands = new HashMap<>();

    public Help(Map<String, Command> commands) {
        this.commands = commands;
        name = "help";
        description = "Выводит список доступных команд";
    }

    @Override
    public void action(String[] args) {
        if (args.length == 1) {
            for (String names : commands.keySet()) {
                Command command = commands.get(names);
                System.out.printf("%-8s%s\n", command.getName(), command.getDescription());
            }
        }
        else {
            Command command = commands.get(args[1]);

            if (command != null) {
                System.out.printf("%-8s%s\n", command.getName(), command.getDescription());
            }
            else {
                System.out.printf("Ошибка: неизвестная команда '%s'\n", args[1]);
            }
        }
    }
}
