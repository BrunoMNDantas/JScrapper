package com.github.brunomndantas.jscrapper.core.selector;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

public interface ISelector {

    Collection<WebElement> select(WebDriver driver) throws SelectorException;

}
