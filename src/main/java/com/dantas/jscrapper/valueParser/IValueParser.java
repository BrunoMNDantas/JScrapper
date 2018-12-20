package com.dantas.jscrapper.valueParser;

import org.openqa.selenium.WebDriver;

public interface IValueParser<K> {

    String getName();
    K parse(WebDriver driver) throws ValueParserException;

}
