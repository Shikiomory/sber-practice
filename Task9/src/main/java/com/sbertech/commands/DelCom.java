package com.sbertech.commands;

import java.sql.SQLException;

@CommandInfo(name = "delcom", description = "Удаляет из списка выполненные задачи")
public class DelCom extends Command{

    @Override
    public void action(String[] args) throws SQLException{
        String sql = "Delete From tasks WHERE Status = 'Done'";
        String[] params = {};

        database.execUpdate(sql, params);
    }
}
