package com.sbertech.commands;

import com.sbertech.Command;

public class Help extends Command {
    @Override
    public void action() {
        System.out.printf("DATE \t%s\nTIME \t%s\nPWD \t%s\nEXIT \t%s\n",
                CurDate.description(), Time.description(), Pwd.description(), Exit.description());
    }
}
