package com.github.brunomndantas.jscrapper.selector;

import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public abstract class Selector implements ISelector {

    @Override
    public Collection<WebElement> select(WebDriver driver) throws SelectorException {
        try {
            return selectElements(driver);
        } catch (Exception e) {
            String msg = "Error selecting elements on url:" + driver.getCurrentUrl();
            throw new SelectorException(msg, e);
        }
    }



    protected abstract Collection<WebElement> selectElements(WebDriver driver) throws Exception;

}
