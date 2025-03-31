package com.sbertech.commands;

import java.time.LocalDate;

public class CurDate extends Command {

    public CurDate() {
        name = "date";
        description = "Возвращает текущую дату в формате dd-MM-yyyy";
    }

    @Override
    public void action(String[] args) {
        LocalDate date = LocalDate.now();
        int year = date.getYear();
        int month = date.getMonthValue();
        int day = date.getDayOfMonth();
        System.out.printf("%02d-%02d-%d\n", day, month, year);
    }

}
