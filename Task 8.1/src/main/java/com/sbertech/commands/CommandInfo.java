package com.sbertech.commands;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
public @interface CommandInfo {
    String name();
    String description();
}
