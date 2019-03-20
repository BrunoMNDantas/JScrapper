package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class SendKeysDriverLoader extends DriverLoader {

    private String keys;
    public String getKeys() { return this.keys; }

    private By by;
    public By getBy() { return this.by; }



    public SendKeysDriverLoader(String keys, By by) {
        this.keys = keys;
        this.by = by;
    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        for(WebElement element : driver.findElements(this.by))
            element.sendKeys(this.keys);
    }

}
