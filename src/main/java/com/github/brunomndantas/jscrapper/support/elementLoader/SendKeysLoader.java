package com.github.brunomndantas.jscrapper.support.elementLoader;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public class SendKeysLoader extends ElementLoader {

    private String keys;
    public String getKeys() { return this.keys; }



    public SendKeysLoader(String keys) {
        this.keys = keys;
    }



    @Override
    protected void loadElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        for(WebElement element : elements)
            element.sendKeys(this.keys);
    }

}
