package com.sbertech.commands;

import com.sbertech.Command;
import java.time.LocalTime;

public class Time extends Command{
    @Override
    public void action() {
        LocalTime time = LocalTime.now();
        int hour = time.getHour();
        int minute = time.getMinute();
        int second = time.getSecond();
        System.out.printf("%02d:%02d:%02d\n", hour, minute, second);
    }

    public static String description() {
        return "Возвращает текущее время в формате HH:mm:ss";
    }
}
