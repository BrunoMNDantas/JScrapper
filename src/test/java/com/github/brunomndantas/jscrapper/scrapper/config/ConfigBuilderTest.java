package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.DriverLoaderException;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.DriverSupplierException;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.ElementLoaderException;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.parser.ParserException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.core.selector.SelectorException;
import com.github.brunomndantas.jscrapper.core.urlSupplier.IURLSupplier;
import com.github.brunomndantas.jscrapper.core.urlSupplier.URLSupplierException;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.ElementLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Property;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Selector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.InstanceFactory;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.URLSupplier;
import com.github.brunomndantas.jscrapper.support.instanceFactory.EmptyConstructorInstanceFactory;
import com.github.brunomndantas.jscrapper.support.parser.single.text.reference.SingleReferenceStringTextParser;
import com.github.brunomndantas.jscrapper.support.property.FieldProperty;
import com.github.brunomndantas.jscrapper.support.selector.IdSelector;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ConfigBuilderTest {

    private static class MyInstanceFactory implements IInstanceFactory {
        @Override public Object create() throws InstanceFactoryException { return null; }
    }

    private static class MyURLSupplier implements IURLSupplier{
        @Override public String get() throws URLSupplierException { return null; }
    }

    private static class MyDriverSupplier implements IDriverSupplier {
        @Override public WebDriver get() throws DriverSupplierException { return null; }
    }

    private static class MyDriverLoader implements IDriverLoader {
        @Override public void load(WebDriver driver) throws DriverLoaderException { }
    }

    private static class MySelector implements ISelector {
        @Override public Collection<WebElement> select(WebDriver driver) throws SelectorException { return null; }
    }

    private static class MyElementLoader implements IElementLoader {
        @Override public void load(WebDriver driver, Collection<WebElement> elements) throws ElementLoaderException { }
    }

    private static class MyParser implements IParser {
        @Override public Object parse(WebDriver driver, Collection<WebElement> elements) throws ParserException { return null; }
    }

    private static class MyProperty implements IProperty {
        @Override public Object get(Object instance) throws PropertyException { return null; }
        @Override public void set(Object instance, Object value) throws PropertyException { }
    }

    @InstanceFactory(MyInstanceFactory.class)
    @URLSupplier(MyURLSupplier.class)
    @DriverSupplier(MyDriverSupplier.class)
    @DriverLoader(MyDriverLoader.class)
    private static class Person {
        @DriverLoader(MyDriverLoader.class)
        @Selector(MySelector.class)
        @ElementLoader(MyElementLoader.class)
        @Parser(MyParser.class)
        @Property(MyProperty.class)
        public String name;

        @DriverLoader(MyDriverLoader.class)
        @Selector(MySelector.class)
        @ElementLoader(MyElementLoader.class)
        @Parser(MyParser.class)
        @Property(MyProperty.class)
        public int age;
    }

    private static class NoAnnotationPerson {
        public String name;
    }


    @Test
    public void mergeConfigTest() throws Exception {
        Class<?> klass = Person.class;
        ClassConfig classConfig = ConfigBuilder.createConfig(klass);
        FieldConfig fieldConfig = classConfig.getFieldsConfig().stream().findFirst().get();
        FieldConfig ageFieldConfig = classConfig.getFieldsConfig().stream().skip(1).findFirst().get();

        IInstanceFactory instanceFactory = new MyInstanceFactory();
        IURLSupplier urlSupplier = () -> null;
        IDriverSupplier driverSupplier = new MyDriverSupplier();
        IDriverLoader driverLoader = new MyDriverLoader();
        Field field = Person.class.getDeclaredField("name");
        ISelector selector = new MySelector();
        IElementLoader elementLoader = new MyElementLoader();
        IParser parser = new MyParser();
        IProperty property = new MyProperty();

        ClassConfig userClassConfig = new ClassConfig(klass, instanceFactory, urlSupplier, driverSupplier, driverLoader, new LinkedList<>());
        FieldConfig userFieldConfig = new FieldConfig(field, driverLoader, selector, elementLoader, parser, property);
        userClassConfig.getFieldsConfig().add(userFieldConfig);

        ConfigBuilder.mergeConfig(classConfig, userClassConfig);

        assertSame(instanceFactory, classConfig.getInstanceFactory());
        assertSame(urlSupplier, classConfig.getURLSupplier());
        assertSame(driverSupplier, classConfig.getDriverSupplier());
        assertSame(driverLoader, classConfig.getDriverLoader());
        assertSame(driverLoader, fieldConfig.getDriverLoader());
        assertSame(selector, fieldConfig.getSelector());
        assertSame(elementLoader, fieldConfig.getElementLoader());
        assertSame(parser, fieldConfig.getParser());
        assertSame(property, fieldConfig.getProperty());
        assertNull(ageFieldConfig.getDriverLoader());
        assertNull(ageFieldConfig.getSelector());
        assertNull(ageFieldConfig.getElementLoader());
        assertNull(ageFieldConfig.getParser());
        assertNull(ageFieldConfig.getProperty());
    }

    @Test
    public void createConfigTest() throws Exception {
        Class<?> klass = Person.class;
        Field field = klass.getDeclaredField("name");

        ClassConfig config = ConfigBuilder.createConfig(klass);

        assertSame(klass, config.getKlass());
        assertEquals(2, config.getFieldsConfig().size());
        assertEquals(field, config.getFieldsConfig().stream().findFirst().get().getField());
    }

    @Test
    public void buildWithUserConfigTest() throws Exception {
        Class<?> klass = Person.class;
        IInstanceFactory instanceFactory = new MyInstanceFactory();
        IURLSupplier urlSupplier = () -> null;
        IDriverSupplier driverSupplier = new MyDriverSupplier();
        IDriverLoader driverLoader = new MyDriverLoader();
        Field field = Person.class.getDeclaredField("name");
        ISelector selector = new MySelector();
        IElementLoader elementLoader = new MyElementLoader();
        IParser parser = new MyParser();
        IProperty property = new MyProperty();

        ClassConfig classConfig = new ClassConfig(klass, instanceFactory, urlSupplier, driverSupplier, driverLoader, new LinkedList<>());
        FieldConfig fieldConfig = new FieldConfig(field, driverLoader, selector, elementLoader, parser, property);
        classConfig.getFieldsConfig().add(fieldConfig);

        ConfigBuilder.buildConfig(classConfig);

        assertSame(klass, classConfig.getKlass());
        assertSame(instanceFactory, classConfig.getInstanceFactory());
        assertSame(urlSupplier, classConfig.getURLSupplier());
        assertSame(driverSupplier, classConfig.getDriverSupplier());
        assertSame(driverLoader, classConfig.getDriverLoader());
        assertSame(field, fieldConfig.getField());
        assertSame(driverLoader, fieldConfig.getDriverLoader());
        assertSame(selector, fieldConfig.getSelector());
        assertSame(elementLoader, fieldConfig.getElementLoader());
        assertSame(parser, fieldConfig.getParser());
        assertSame(property, fieldConfig.getProperty());
    }

    @Test
    public void buildWithAnnotationConfigTest() throws Exception {
        ClassConfig classConfig = new ClassConfig(Person.class);
        FieldConfig fieldConfig = new FieldConfig(Person.class.getDeclaredField("name"));
        classConfig.getFieldsConfig().add(fieldConfig);

        ConfigBuilder.buildConfig(classConfig);

        assertSame(Person.class, classConfig.getKlass());
        assertTrue(classConfig.getInstanceFactory() instanceof MyInstanceFactory);
        assertTrue(classConfig.getURLSupplier() instanceof MyURLSupplier);
        assertTrue(classConfig.getDriverSupplier() instanceof MyDriverSupplier);
        assertTrue(classConfig.getDriverLoader() instanceof MyDriverLoader);
        assertEquals(Person.class.getDeclaredField("name"), fieldConfig.getField());
        assertTrue(fieldConfig.getDriverLoader() instanceof MyDriverLoader);
        assertTrue(fieldConfig.getSelector() instanceof MySelector);
        assertTrue(fieldConfig.getElementLoader() instanceof MyElementLoader);
        assertTrue(fieldConfig.getParser() instanceof MyParser);
        assertTrue(fieldConfig.getProperty() instanceof MyProperty);
    }

    @Test
    public void buildDefaultDefaultConfigTest() throws Exception {
        ClassConfig classConfig = new ClassConfig(NoAnnotationPerson.class);
        FieldConfig fieldConfig = new FieldConfig(NoAnnotationPerson.class.getDeclaredField("name"));
        classConfig.getFieldsConfig().add(fieldConfig);

        ConfigBuilder.buildConfig(classConfig);

        assertSame(NoAnnotationPerson.class, classConfig.getKlass());
        assertTrue(classConfig.getInstanceFactory() instanceof EmptyConstructorInstanceFactory);
        assertNotNull(classConfig.getURLSupplier());
        assertNull(classConfig.getDriverSupplier());
        assertNotNull(classConfig.getDriverLoader());
        assertEquals(NoAnnotationPerson.class.getDeclaredField("name"), fieldConfig.getField());
        assertNotNull(fieldConfig.getDriverLoader());
        assertTrue(fieldConfig.getSelector() instanceof IdSelector);
        assertNotNull(fieldConfig.getElementLoader());
        assertTrue(fieldConfig.getParser() instanceof SingleReferenceStringTextParser);
        assertTrue(fieldConfig.getProperty() instanceof FieldProperty);
    }

}
