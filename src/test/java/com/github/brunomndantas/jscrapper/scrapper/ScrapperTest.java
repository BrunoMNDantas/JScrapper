package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Element;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.Page;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Collection;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScrapperTest {

    private static class MyDriverSupplier implements IDriverSupplier {
        @Override public WebDriver get() throws DriverSupplierException { return new DummyDriver(); }
    }

    private static class MyParser implements IParser {
        @Override public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return "name"; }
    }

    @Page(driverSupplier = @DriverSupplier(MyDriverSupplier.class))
    private static class Person {

        @Element(parser = @Parser(MyParser.class))
        public String name;

    }
    


    @Test
    public void scrapTest() throws Exception {
        Person person = new Scrapper().scrap(Person.class);
        assertNotNull(person);
        assertEquals("name", person.name);
    }

}
