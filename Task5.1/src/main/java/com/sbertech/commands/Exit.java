package com.sbertech.commands;

public class Exit extends Command {

    public Exit() {
        name = "exit";
        description = "Завершает работу";
    }

    @Override
    public void action(String[] args) {
        com.sbertech.Console.exit = true;
    }

}
