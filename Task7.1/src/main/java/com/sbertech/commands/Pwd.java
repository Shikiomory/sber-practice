package com.sbertech.commands;

public class Pwd extends Command {

    public Pwd() {
        name = "pwd";
        description = "Возвращает текущий каталог";
    }
    @Override
    public void action(String[] args) {
        System.out.println(System.getProperty("user.dir"));
    }

}
