package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.DummyDriver;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.URLSupplier;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.*;

import static org.junit.Assert.*;

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

    @URLSupplier(url = "www.google.pt")
    private static class DefaultDriverEntity {
        public WebDriver driver;
    }



    @Test
    public void getDefaultDriverSupplier() {
        IDriverSupplier supplier = () -> null;
        Scrapper scrapper = new Scrapper(supplier);
        assertSame(supplier, scrapper.getDefaultDriverSupplier());
    }

    @Test
    public void constructorsTest() {
        Scrapper scrapper = new Scrapper();
        assertNull(scrapper.getDefaultDriverSupplier());

        IDriverSupplier supplier = () -> null;
        scrapper = new Scrapper(supplier);
        assertSame(supplier, scrapper.getDefaultDriverSupplier());
    }

    @Test
    public void usesDefaultDriverSupplier() throws Exception {
        WebDriver driver = new DummyDriver(){
            @Override
            public List<WebElement> findElements(By by) {
                return new LinkedList<>();
            }
        };
        IDriverSupplier supplier = () -> driver;

        DefaultDriverEntity entity = new Scrapper(supplier).scrap(DefaultDriverEntity.class);
        assertSame(driver, entity.driver);
    }

    @Test
    public void scrapTest() throws Exception {
        Person person = new Scrapper().scrap(Person.class);
        assertNotNull(person);
        assertEquals("name", person.name);
    }

    @Test
    public void scrapWithUserConfigTest() throws Exception {
        boolean[] passed = new boolean[1];
        IDriverSupplier driverSupplier = () -> { passed[0] = true; return new MyDriver(); };

        ClassConfig userConfig = new ClassConfig(Person.class);
        userConfig.setDriverSupplier(driverSupplier);

        Person person = new Scrapper().scrap(Person.class, userConfig);

        assertNotNull(person);
        assertEquals("name", person.name);
        assertTrue(passed[0]);
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
    public void scrapParameterizedURLWithUserConfigTest() throws Exception {
        boolean[] passed = new boolean[1];
        IDriverSupplier driverSupplier = () -> { passed[0] = true; return new MyDriver(); };

        ClassConfig userConfig = new ClassConfig(Person.class);
        userConfig.setDriverSupplier(driverSupplier);

        Map<String, String> parameters = new HashMap<>();
        parameters.put("name", "paul");

        ParameterizedPerson person = new Scrapper().scrap(ParameterizedPerson.class, parameters, userConfig);

        assertNotNull(person);
        assertEquals("www.google.pt/paul", person.name);
        assertTrue(passed[0]);
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

    @Test
    public void scrapMultipleParameterizedURLWithUserConfigTest() throws Exception {
        boolean[] passed = new boolean[1];
        IDriverSupplier driverSupplier = () -> { passed[0] = true; return new MyDriver(); };

        ClassConfig userConfig = new ClassConfig(Person.class);
        userConfig.setDriverSupplier(driverSupplier);

        Map<String, String> parametersA = new HashMap<>();
        parametersA.put("name", "paul");
        Map<String, String> parametersB = new HashMap<>();
        parametersB.put("name", "ana");
        Collection<Map<String, String>> parameters = Arrays.asList(parametersA, parametersB);

        Collection<ParameterizedPerson> people = new Scrapper().scrap(ParameterizedPerson.class, parameters, userConfig);

        assertNotNull(people);
        assertEquals(2, people.size());
        assertEquals("www.google.pt/paul", people.stream().findFirst().get().name);
        assertEquals("www.google.pt/ana", people.stream().skip(1).findFirst().get().name);
        assertTrue(passed[0]);
    }

}


