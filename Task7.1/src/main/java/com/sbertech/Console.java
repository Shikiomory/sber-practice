package com.sbertech;

import com.sbertech.commands.Command;
import com.sbertech.commands.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.lang.reflect.*;
import java.util.Set;

import org.reflections.Reflections;

public class Console {
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    public static boolean exit = false;

    public Console() {
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
                command.action(args);
            } else {
                System.out.printf("Ошибка: неизвестная команда '%s'\n", com);
            }
        }
    }

    private void pushCommand(Command command) {
        commands.put(command.getName(), command);
    }

}

