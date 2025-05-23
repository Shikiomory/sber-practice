package com.sbertech.commands;

import java.time.LocalTime;

public class Time extends Command {

    public Time() {
        name = "time";
        description = "Возвращает текущее время в формате HH:mm:ss";
    }
    @Override
    public void action(String[] args) {
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.printf("%02d:%02d:%02d\n", hour, minute, second);
    }

}
