package com.sbertech.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SelParser implements Parser{
    private static final Logger log = LoggerFactory.getLogger(SelParser.class);
    private final String NameN = "[data-marker='item-view/title-info']";
    private final String PriceN = "[data-marker='item-view/item-price']";
    private String[] userAgents = {};
    private Random random = new Random();

    private WebDriver driver;
//    private WebDriver driver = new ChromeDriver(new ChromeOptions().addArguments("--headless=new"));


    //ozon
    //"m1q_28 tsHeadline550Medium"
    //"m5p_28 pm5_28 m9p_28"

    //dns
    //"product-card-top__title"
    //"product-buy__price product-buy__price_active"
    public SelParser (String url) {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-blink-features=AutomationControlled");
//         "--user-agent=" + userAgents[random.nextInt(userAgents.length)]
        driver = new ChromeDriver(options);
        try {
            driver.get(url);
        } catch (Exception e) {
            log.error("Ошибка, страницы не существует." + e.getMessage());
        }
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    }

    @Override
    public float getPrice() {
        driver.getTitle();
        try {
        WebElement msg = driver.findElement(By.cssSelector(PriceN));
            float price = Float.valueOf(msg.getDomAttribute(("content")));
            return price;
        } catch (Exception e) {
            log.error("Элемент 'цена' не найден");
        }
        return -1;
    }

    @Override
    public String getName() {
        driver.getTitle();
        try {
            WebElement msg = driver.findElement(By.cssSelector(NameN));
            String name = msg.getText();
            name = name.replaceAll(",", "");
            return name;
        } catch (Exception e) {
            log.error("Элемент 'Имя' не найден");
        }
        return "\0";
    }

    @Override
    public void close() {
        driver.quit();
    }
}
