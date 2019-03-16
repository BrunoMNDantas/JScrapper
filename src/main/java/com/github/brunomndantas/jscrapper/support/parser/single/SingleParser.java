package com.github.brunomndantas.jscrapper.support.parser.single;

import com.github.brunomndantas.jscrapper.support.parser.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public abstract class SingleParser extends Parser {

    @Override
    protected Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        WebElement element = elements.stream().findFirst().orElse(null);
        return parseElement(driver, element);
    }



    protected abstract Object parseElement(WebDriver driver, WebElement element) throws Exception;

}
