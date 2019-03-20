package com.github.brunomndantas.jscrapper.support.driverLoader;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SubmitDriverLoader extends DriverLoader {

    private By by;
    public By getBy() { return this.by; }



    public SubmitDriverLoader(By by) {
        this.by = by;
    }



    @Override
    protected void loadDriver(WebDriver driver) throws Exception {
        for(WebElement element : driver.findElements(this.by))
            element.submit();
    }

}
