package com.sbertech.commands;

import com.sbertech.Command;

public class Pwd extends Command {
    @Override
    public void action() {
        System.out.println(System.getProperty("user.dir"));
    }

    public static String description() {
        return "Возвращает текущий каталог";
    }
}
