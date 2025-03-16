package com.sbertech;

import com.sbertech.commands.*;

import java.util.Scanner;

public class Console {
    private Command[] commands = {new CurDate(), new Time(), new Pwd(), new Help(), new Exit()};
    private Scanner scanner = new Scanner(System.in);
    public static boolean exit = false;

    public void exec() {
        while(!exit) {
            System.out.print("> ");
            String com = scanner.nextLine();

            try {
                Comm comm = Comm.valueOf(com.toUpperCase());
                int index = comm.ordinal();

                commands[index].action();
            } catch (IllegalArgumentException e) {
                System.out.printf("Ошибка: неизвестная команда '%s'\n", com);
            }
        }
    }
}
