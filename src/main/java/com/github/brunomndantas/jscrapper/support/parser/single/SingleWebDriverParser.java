package com.github.brunomndantas.jscrapper.support.parser.single;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class SingleWebDriverParser extends SingleParser {

    @Override
    protected Object parseElement(WebDriver driver, WebElement element) throws Exception {
        return driver;
    }

}
