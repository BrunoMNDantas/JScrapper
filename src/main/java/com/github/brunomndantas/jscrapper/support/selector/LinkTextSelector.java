package com.github.brunomndantas.jscrapper.support.selector;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class LinkTextSelector extends Selector {

    private String selector;
    public String getSelector() { return this.selector; }



    public LinkTextSelector(String selector) {
        this.selector = selector;
    }



    @Override
    protected Collection<WebElement> selectElements(WebDriver driver) throws Exception {
        return driver.findElements(By.linkText(this.selector));
    }

}
