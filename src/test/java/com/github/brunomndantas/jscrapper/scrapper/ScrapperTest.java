package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.URLSupplier;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ScrapperTest {

    private static class MyDriver extends DummyDriver {

        public String url;

        @Override
        public void get(String url) {
            super.get(url);
            this.url = url;
        }
    }

    private static class MyDriverSupplier implements IDriverSupplier {
        @Override public WebDriver get() throws DriverSupplierException { return new MyDriver(); }
    }

    private static class MyParser implements IParser {
        @Override public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return "name"; }
    }

    private static class URLParser implements IParser {
        @Override public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return ((MyDriver)driver).url; }
    }

    @DriverSupplier(MyDriverSupplier.class)
    private static class Person {
        @Parser(MyParser.class)
        public String name;
    }

    @DriverSupplier(MyDriverSupplier.class)
    @URLSupplier(url = "www.google.pt/{name}")
    private static class ParameterizedPerson {
        @Parser(URLParser.class)
        public String name;
    }
    


    @Test
    public void scrapTest() throws Exception {
        Person person = new Scrapper().scrap(Person.class);
        assertNotNull(person);
        assertEquals("name", person.name);
    }

    @Test
    public void scrapParameterizedURLTest() throws Exception {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "paul");

        ParameterizedPerson person = new Scrapper().scrap(ParameterizedPerson.class, parameters);

        assertNotNull(person);
        assertEquals("www.google.pt/paul", person.name);
    }

    @Test
    public void scrapMultipleParameterizedURLTest() throws Exception {
        Map<String, String> parametersA = new HashMap<>();
        parametersA.put("name", "paul");
        Map<String, String> parametersB = new HashMap<>();
        parametersB.put("name", "ana");
        Collection<Map<String, String>> parameters = Arrays.asList(parametersA, parametersB);

        Collection<ParameterizedPerson> people = new Scrapper().scrap(ParameterizedPerson.class, parameters);

        assertNotNull(people);
        assertEquals(2, people.size());
        assertEquals("www.google.pt/paul", people.stream().findFirst().get().name);
        assertEquals("www.google.pt/ana", people.stream().skip(1).findFirst().get().name);
    }

}


