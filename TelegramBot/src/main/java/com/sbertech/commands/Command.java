package com.sbertech.commands;

import com.sbertech.database.Db;

import java.sql.SQLException;

public abstract class Command {
    protected Db database;
    public abstract void action(String[] args) throws SQLException;
    public abstract String getMsg();
    public void setDatabase(Db database) {
        this.database = database;
    }
}
