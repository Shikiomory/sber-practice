package com.sbertech.commands;

import com.sbertech.Command;
import java.time.LocalDate;

public class CurDate extends Command {
    @Override
    public void action() {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        System.out.printf("%02d-%02d-%d\n", day, month, year);
    }

    public static String description() {
        return "Возвращает текущую дату в формате dd-MM-yyyy";
    }
}
