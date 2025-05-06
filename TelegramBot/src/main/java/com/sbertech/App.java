package com.sbertech;
import com.sbertech.bot.CheckerBot;
import com.sbertech.commands.manager.CommandManager;
import com.sbertech.database.Db;
import com.sbertech.service.PriceChecker;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;
import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class App 
{
    private static final String URL = "jdbc:h2:mem:testdb";
    private static final String USER = "sa";
    private static final String PASSWORD = "";

    public static void main( String[] args )
    {
        String botToken = args[0];
        try(TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            Db database = new Db(URL, USER, PASSWORD);
            database.readFromFile();

            CommandManager commandManager = new CommandManager(database);
            CheckerBot checkerBot = new CheckerBot(botToken, commandManager);

            botsApplication.registerBot(botToken, checkerBot);
            System.out.println("Бот запущен!");

            JobDataMap jobData = new JobDataMap();
            jobData.put("database", database);
            jobData.put("checkerBot", checkerBot);

            JobDetail job = JobBuilder.newJob(PriceChecker.class)
                    .withIdentity("priceCheckJob", "group1")
                    .usingJobData(jobData)
                    .build();

            Trigger trigger = TriggerBuilder.newTrigger().withSchedule(CronScheduleBuilder.cronSchedule("0 0/1 * * * ?")).build();

            Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
            scheduler.scheduleJob(job, trigger);
            scheduler.start();
            System.out.println("Планировщик запущен!");


            // Ensure this prcess wait forever
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
