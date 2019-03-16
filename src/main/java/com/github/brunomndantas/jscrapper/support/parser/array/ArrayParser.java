package com.github.brunomndantas.jscrapper.support.parser.array;

import com.github.brunomndantas.jscrapper.support.parser.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public abstract class ArrayParser extends Parser {

    @Override
    protected Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        Object[] result = new Object[elements.size()];

        int idx = 0;
        for(WebElement element : elements) {
            result[idx] = parseElement(driver, element);
            idx++;
        }

        return result;
    }



    protected abstract Object parseElement(WebDriver driver, WebElement element) throws Exception;

}
