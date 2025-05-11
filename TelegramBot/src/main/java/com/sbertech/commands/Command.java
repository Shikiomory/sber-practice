package com.sbertech.commands;

import com.sbertech.database.Db;

import java.sql.SQLException;

public abstract class Command {
    protected Db database;
    protected String[] messages;
    public abstract void action(String[] args, long chat_id) throws SQLException;
    public abstract String[] getMsg();
    public void setDatabase(Db database) {
        this.database = database;
    }
}
