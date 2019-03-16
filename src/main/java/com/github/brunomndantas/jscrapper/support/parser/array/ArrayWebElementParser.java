package com.github.brunomndantas.jscrapper.support.parser.array;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class ArrayWebElementParser extends ArrayParser {

    @Override
    protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
        return element;
    }

}
