package com.sbertech.commands;

import com.sbertech.Command;

public class Exit extends Command {

    @Override
    public void action() {
        com.sbertech.Console.exit = true;
    }

    public static String description() {
        return "Завершает работу";
    }
}
