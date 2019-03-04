package com.github.brunomndantas.jscrapper.core.pageLoader;

import org.openqa.selenium.WebDriver;

public interface IPageLoader {

    void load(WebDriver driver) throws PageLoaderException;

}
