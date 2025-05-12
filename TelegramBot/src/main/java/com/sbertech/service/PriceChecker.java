package com.sbertech.service;

import com.sbertech.bot.CheckerBot;
import com.sbertech.database.Db;
import com.sbertech.service.modes.ChangePriceMode;
import com.sbertech.service.modes.LowPriceMode;
import com.sbertech.service.modes.MorePriceMode;
import com.sbertech.service.modes.PriceMode;
import com.sbertech.util.PriceFormatter;
import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;


public class PriceChecker implements Job {
    private static final Logger log = LoggerFactory.getLogger(PriceChecker.class);
    private Db database;
    private CheckerBot checkerBot;
    private Map<String, PriceMode> priceModeMap = Map.of("1", new LowPriceMode(), "2", new MorePriceMode(), "3", new ChangePriceMode());

    public PriceChecker(Db database, CheckerBot checkerBot) {
        this.database = database;
        this.checkerBot = checkerBot;
    }

    public PriceChecker() {}

    private void checkPrices() throws SQLException {
        String sql = "SELECT * FROM tasks";
        List<Map<String, Object>> rows = database.execQuery(sql);;
        for (Map<String, Object> row: rows) {
            float price = Float.valueOf((String)row.get("Price"));
            Parser parser = new SelParser((String)row.get("Url"));
            float newPrice = parser.getPrice();

            String mode = (String)row.get("Mode");

            if (newPrice == -1) {
                checkerBot.sendMsg(String.format("Возникла ошибка с товаром, %s, возможно его больше не существует", (String)row.get("Name")),Long.valueOf((String)row.get("ChatId")));
            }
            else if (priceModeMap.get(mode).isChange(price, newPrice)) {
//            else if (newPrice <= price) {
                String name = (String)row.get("Name");
                long chatId = Long.valueOf((String)row.get("ChatId"));
                String url = (String)row.get("Url");
                notifyUser(chatId, name, price, newPrice, url);
                updatePrice((String)row.get("Uid"), String.valueOf(newPrice));
            }
            parser.close();
        }
    }

    private void notifyUser(long chatId, String name, float price, float newPrice, String url) {
        PriceFormatter priceFormatter = new PriceFormatter();
        String msg = String.format("Цена на \"%s\" изменилась.\nБыло %s\nСтало: %s\nСсылка на товар:\n %s", name, priceFormatter.format(price), priceFormatter.format(newPrice), url);
        if (chatId != 0) {
            checkerBot.notifyUser(msg, chatId);
        }
        else {
            log.error("Не удалось отправить уведомление: chatId отсутствует");
        }
    }

    private void updatePrice(String uid, String newPrice) throws SQLException {
        String sql = "UPDATE tasks SET Price = ? WHERE Uid = ?";
        String[] params = {newPrice, uid};
        database.execUpdate(sql, params);
    }

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        this.database = (Db) dataMap.get("database");
        this.checkerBot = (CheckerBot) dataMap.get("checkerBot");
        try {
            System.out.println("Идет проверка цен...");
            checkPrices();
            System.out.println("Проверка цен завершена!");
        } catch (SQLException e) {
            log.error("Ошибка записи новой цены: " + e.getMessage());
        }
    }
}
