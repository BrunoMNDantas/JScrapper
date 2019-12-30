package com.github.brunomndantas.jscrapper.utils.webDriver;

import com.github.brunomndantas.jscrapper.utils.webElement.WaitWebElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public class WaitWebDriver extends ComposedWebDriver {

    private long sleepTime;
    public long getSleepTime() { return this.sleepTime; }



    public WaitWebDriver(WebDriver driver, long sleepTime) {
        super(driver);
        this.sleepTime = sleepTime;
    }



    @Override
    public void get(String url) {
        super.get(url);
        sleep();
    }

    @Override
    public List<WebElement> findElements(By by) {
        List<WebElement> elements = super.findElements(by);

        if(elements.isEmpty()) {
            sleep();
            elements = super.findElements(by);
        }

        return elements.stream().map(e -> new WaitWebElement(e, sleepTime)).collect(Collectors.toList());
    }

    @Override
    public WebElement findElement(By by) {
        WebElement element = super.findElement(by);

        if(element == null) {
            sleep();
            element = super.findElement(by);
        }

        return new WaitWebElement(element, sleepTime);
    }

    private void sleep() {
        try {
            Thread.sleep(this.sleepTime);
        } catch (InterruptedException e) { }
    }
    
}
