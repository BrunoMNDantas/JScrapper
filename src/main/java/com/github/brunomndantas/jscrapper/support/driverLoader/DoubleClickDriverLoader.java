package com.github.brunomndantas.jscrapper.support.driverLoader;

import com.github.brunomndantas.jscrapper.support.elementLoader.ElementLoader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

import java.util.Collection;

public class DoubleClickDriverLoader extends DriverLoader {

    private By by;
    public By getBy() { return this.by; }



    public DoubleClickDriverLoader(By by) {
        this.by = by;
    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        for(WebElement element : driver.findElements(this.by))
            new Actions(driver).doubleClick(element).build().perform();
    }

}
