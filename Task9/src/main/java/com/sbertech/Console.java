package com.sbertech;

import com.sbertech.commands.Command;
import com.sbertech.commands.CommandInfo;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Console {
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    public static boolean exit = false;

    public Console(Db database) {
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
    public void exec() {
        while(!exit) {
            System.out.print("> ");
            String com = scanner.nextLine().trim();
            String[] args = com.split(" ");
            if (com.isEmpty()) {
                continue;
            }

            Command command = commands.get(args[0].toLowerCase());
            if (command != null) {
                try {
                    command.action(args);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } catch (IndexOutOfBoundsException e) {
                    System.err.println("Количество полученных аргументов меньше количества нужных аргументов " + e.getMessage());
                }
            } else {
                System.out.printf("Ошибка: неизвестная команда '%s'\n", com);
            }
        }
    }

    private void pushCommand(Command command) {
        CommandInfo annotation = command.getClass().getAnnotation(CommandInfo.class);
        if (annotation == null) {
            System.err.printf("Команда %s не имеет аннотации @CommandInfo\n",
                    command.getClass().getSimpleName());
            return;
        }
        commands.put(annotation.name(), command);
    }

}

