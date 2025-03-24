package com.sbertech;

import com.sbertech.commands.*;
import com.sbertech.commands.Command;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Console {
    private Map<String, Command> commands = new HashMap<>();
    private Scanner scanner = new Scanner(System.in);
    public static boolean exit = false;

    public Console() {
        pushCommand(new CurDate());
        pushCommand(new Time());
        pushCommand(new Pwd());
        pushCommand(new Help(commands));
        pushCommand(new Exit());
    }
    public void exec() {
        while(!exit) {
            System.out.print("> ");
            String com = scanner.nextLine().trim();
            String[] args = com.split(" ");
            if (com == "") {
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

