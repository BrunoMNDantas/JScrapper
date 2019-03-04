package com.github.brunomndantas.jscrapper.core.parser;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public interface IParser {

    Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException;

}
