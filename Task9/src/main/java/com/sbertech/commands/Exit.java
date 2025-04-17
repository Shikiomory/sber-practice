package com.sbertech.commands;

@CommandInfo(name = "exit", description = "Завершает работу")
public class Exit extends Command {

    @Override
    public void action(String[] args) {
        com.sbertech.Console.exit = true;
    }

}