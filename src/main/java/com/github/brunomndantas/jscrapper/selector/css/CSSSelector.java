package com.github.brunomndantas.jscrapper.selector.css;

import com.github.brunomndantas.jscrapper.selector.Selector;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class CSSSelector extends Selector {

    private String selector;
    public String getSelector() { return this.selector; }



    public CSSSelector(String selector) {
        this.selector = selector;
    }



    @Override
    protected Collection<WebElement> selectElements(WebDriver driver) throws Exception {
        return driver.findElements(By.cssSelector(this.selector));
    }

}
