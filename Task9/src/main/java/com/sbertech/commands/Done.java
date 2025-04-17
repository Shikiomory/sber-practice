package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "done", description = "Отмечает выбранную задачу выполненной")
public class Done extends Command{
    @Override
    public void action(String[] args) throws SQLException, IndexOutOfBoundsException{
        String sql = "UPDATE users SET Status = ? WHERE Name = ?";
        String name = args[1];
        String[] params = {name, "Done"};

        database.execUpdate(sql, params);
    }
}
