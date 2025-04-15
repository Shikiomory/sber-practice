package com.sbertech.commands;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@CommandInfo(name = "help", description = "Выводит список доступных команд")
public class Help extends Command {
    private Map<String, Command> commands = new HashMap<>();

    public Help(Map<String, Command> commands) {
        this.commands = commands;
    }

    @Override
    public void action(String[] args, Connection connection) {
        if (args.length == 1) {
            for (String names : commands.keySet()) {
                Command command = commands.get(names);
//                System.out.printf("%-8s%s\n", command.getName(), command.getDescription());
                CommandInfo info = command.getClass().getAnnotation(CommandInfo.class);
                System.out.printf("%-8s%s\n", info.name(), info.description());
            }
        }
        else {
            Command command = commands.get(args[1]);

            if (command != null) {
//                System.out.printf("%-8s%s\n", command.getName(), command.getDescription());

                CommandInfo info = command.getClass().getAnnotation(CommandInfo.class);
                System.out.printf("%-8s%s\n", info.name(), info.description());
            }
            else {
                System.out.printf("Ошибка: неизвестная команда '%s'\n", args[1]);
            }
        }
    }
}
