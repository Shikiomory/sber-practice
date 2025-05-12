package com.sbertech.commands.manager;

import com.sbertech.commands.annotation.ButtonName;
import com.sbertech.commands.annotation.NeedsArgs;
import com.sbertech.database.Db;
import com.sbertech.commands.Command;
import com.sbertech.commands.annotation.CommandInfo;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class CommandManager {
    private static final Logger log = LoggerFactory.getLogger(CommandManager.class);
    private Map<String, Command> commands = new HashMap<>();
    private Map<String, String> commandsNames = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    public static boolean exit = false;
    private Db database;

    public CommandManager(Db database) {
        this.database = database;

        Reflections reflections = new Reflections("com.sbertech.commands");
        Set<Class<? extends Command>> subclasses = reflections.getSubTypesOf(Command.class);

        for (Class<? extends Command> aClass : subclasses) {
            Command instance;

            if (aClass.getSimpleName().equalsIgnoreCase("help")) {
                try {
                    instance = aClass.getDeclaredConstructor(Map.class).newInstance(commands);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }

            else {
                try {
                    instance = aClass.getDeclaredConstructor().newInstance();
                    instance.setDatabase(database);
                } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                         NoSuchMethodException e) {
                    throw new RuntimeException(e);
                }
            }

            pushCommand(instance);
        }

    }

    public String exec(String com, long chat_id) {
            String returnMsg = "";
            com = com.trim();
            String[] args = com.split(" ");

            Command command = commands.get(args[0].toLowerCase());
            if (command != null) {
                try {
                    command.action(args, chat_id);
                    returnMsg = command.getMsg()[0]; // заглушка!!!!!!!!
                } catch (SQLException e) {
                    log.error("Ошибка базы данных: {}", e.getMessage());
                } catch (IndexOutOfBoundsException e) {
                    returnMsg = "Количество полученных аргументов меньше количества нужных аргументов";
                    log.error("Количество полученных аргументов меньше количества нужных аргументов " + e.getMessage());
                }
            } else {
                returnMsg = String.format("Ошибка: неизвестная команда '%s'\n", args[0]);
            }
        return returnMsg;
    }

    public Command getCommand(String command) {
        return commands.get(command);
    }

    public boolean needsArgs(String command) {
        return commands.get(command).getClass().isAnnotationPresent(NeedsArgs.class);
    }
    private void pushCommand(Command command) {
        CommandInfo commandInfo = command.getClass().getAnnotation(CommandInfo.class);
        if (commandInfo == null) {
            log.error(String.format("Команда %s не имеет аннотации @CommandInfo\n", command.getClass().getSimpleName()));
            return;
        }
        commands.put(commandInfo.name(), command);

        ButtonName buttonName = command.getClass().getAnnotation(ButtonName.class);
        if (buttonName != null) {
            commandsNames.put(buttonName.name(), "/"+commandInfo.name());
        }
    }

    public String getCommandName(String buttonName) {
        return commandsNames.getOrDefault(buttonName, buttonName);
    }
}

