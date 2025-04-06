package com.sbertech.commands;

public abstract class Command {
     protected String name;
     protected String description;
     public abstract void action(String[] args);
}
