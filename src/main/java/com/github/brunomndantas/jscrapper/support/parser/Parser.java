package com.github.brunomndantas.jscrapper.support.parser;

import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public abstract class Parser implements IParser {

    @Override
    public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException {
        try {
            return parseElements(driver, elements);
        } catch (Exception e) {
            String msg = "Error parsing Elements on url:" + driver.getCurrentUrl() + "!";
            throw new ParserException(msg, e);
        }
    }



    protected abstract Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception;

}
