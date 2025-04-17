package com.sbertech.commands;

import com.sbertech.Db;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Command {
    protected Db database;
    public abstract void action(String[] args) throws SQLException;

    public void setDatabase(Db database) {
        this.database = database;
    }
}
