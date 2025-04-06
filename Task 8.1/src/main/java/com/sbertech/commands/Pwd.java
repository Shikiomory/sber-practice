package com.sbertech.commands;

@CommandInfo(name = "pwd", description = "Возвращает текущий каталог")
public class Pwd extends Command {

    @Override
    public void action(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

}
