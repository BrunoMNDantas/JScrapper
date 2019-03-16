package com.github.brunomndantas.jscrapper.support.parser.collection;

import com.github.brunomndantas.jscrapper.support.parser.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;
import java.util.LinkedList;

public abstract class CollectionParser extends Parser {

    @Override
    protected Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        Collection<Object> result = new LinkedList<>();

        for(WebElement element : elements)
            result.add(parseElement(driver, element));

        return result;
    }



    protected abstract Object parseElement(WebDriver driver, WebElement element) throws Exception;

}
