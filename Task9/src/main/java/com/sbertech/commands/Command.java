package com.sbertech.commands;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class Command {
//    protected Connection connection;
    public abstract void action(String[] args, Connection connection) throws SQLException;
}
