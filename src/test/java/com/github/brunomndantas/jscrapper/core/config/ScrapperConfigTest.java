package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import org.junit.Test;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class ScrapperConfigTest {

    @Test
    public void getClassConfigsTest() {
        Map<Class<?>, ClassConfig> classConfigs = new HashMap<>();

        ScrapperConfig config = new ScrapperConfig(classConfigs, null);

        assertSame(classConfigs, config.getClassConfigs());
    }

    @Test
    public void setClassConfigsTest() {
        Map<Class<?>, ClassConfig> classConfigs = new HashMap<>();

        ScrapperConfig config = new ScrapperConfig(null, null);

        config.setClassConfigs(classConfigs);
        assertSame(classConfigs, config.getClassConfigs());
    }

    @Test
    public void getFieldConfigsTest() {
        Map<Field, FieldConfig> fieldConfigs = new HashMap<>();

        ScrapperConfig config = new ScrapperConfig(null, fieldConfigs);

        assertSame(fieldConfigs, config.getFieldConfigs());
    }

    @Test
    public void setFieldConfigsTest() {
        Map<Field, FieldConfig> fieldConfigs = new HashMap<>();

        ScrapperConfig config = new ScrapperConfig(null, null);

        config.setFieldConfigs(fieldConfigs);
        assertSame(fieldConfigs, config.getFieldConfigs());
    }

    @Test
    public void constructorsTest() {
        Map<Class<?>, ClassConfig> classConfigs = new HashMap<>();
        Map<Field, FieldConfig> fieldConfigs = new HashMap<>();

        ScrapperConfig config = new ScrapperConfig();

        assertNotNull(config.getClassConfigs());
        assertNotNull(config.getFieldConfigs());

        config = new ScrapperConfig(classConfigs, fieldConfigs);

        assertSame(classConfigs, config.getClassConfigs());
        assertSame(fieldConfigs, config.getFieldConfigs());
    }

    @Test
    public void registerClassConfigTest() {
        ClassConfig classconfig = new ClassConfig(Object.class);
        ScrapperConfig config = new ScrapperConfig();

        config.registerClassConfig(classconfig);
        assertSame(classconfig, config.getConfigFor(classconfig.getKlass()));
    }

    @Test
    public void overrideClassConfigTest() {
        ClassConfig classconfig = new ClassConfig(Object.class);
        ScrapperConfig config = new ScrapperConfig();

        config.registerClassConfig(classconfig);
        assertSame(classconfig, config.getConfigFor(classconfig.getKlass()));

        classconfig = new ClassConfig(Object.class);

        config.registerClassConfig(classconfig);
        assertSame(classconfig, config.getConfigFor(classconfig.getKlass()));
    }

    @Test
    public void getConfigForClassTest() {
        Map<Class<?>, ClassConfig> classConfigs = new HashMap<>();
        ClassConfig classconfig = new ClassConfig(Object.class);
        classConfigs.put(classconfig.getKlass(), classconfig);

        ScrapperConfig config = new ScrapperConfig(classConfigs, null);

        assertSame(classconfig, config.getConfigFor(classconfig.getKlass()));
    }

    @Test
    public void registerFieldConfigTest() throws NoSuchFieldException {
        FieldConfig fieldConfig = new FieldConfig(FieldConfig.class.getDeclaredField("field"));
        ScrapperConfig config = new ScrapperConfig();

        config.registerFieldConfig(fieldConfig);
        assertSame(fieldConfig, config.getConfigFor(fieldConfig.getField()));
    }

    @Test
    public void overrideFieldConfigTest() throws NoSuchFieldException {
        FieldConfig fieldConfig = new FieldConfig(FieldConfig.class.getDeclaredField("field"));
        ScrapperConfig config = new ScrapperConfig();

        config.registerFieldConfig(fieldConfig);
        assertSame(fieldConfig, config.getConfigFor(fieldConfig.getField()));

        fieldConfig = new FieldConfig(FieldConfig.class.getDeclaredField("field"));

        config.registerFieldConfig(fieldConfig);
        assertSame(fieldConfig, config.getConfigFor(fieldConfig.getField()));
    }

    @Test
    public void getConfigForFieldTest() throws NoSuchFieldException {
        Map<Field, FieldConfig> fieldConfigs = new HashMap<>();
        FieldConfig fieldConfig = new FieldConfig(FieldConfig.class.getDeclaredField("field"));
        fieldConfigs.put(fieldConfig.getField(), fieldConfig);

        ScrapperConfig config = new ScrapperConfig(null, fieldConfigs);

        assertSame(fieldConfig, config.getConfigFor(fieldConfig.getField()));
    }

    @Test
    public void registerInstanceFactoryTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IInstanceFactory instanceFactory = () -> null;

        config.registerInstanceFactory(klass, instanceFactory);

        assertNotNull(config.getClassConfigs().get(klass));
        assertSame(instanceFactory, config.getClassConfigs().get(klass).getInstanceFactory());

        instanceFactory = () -> null;
        config.registerInstanceFactory(klass, instanceFactory);

        assertSame(instanceFactory, config.getClassConfigs().get(klass).getInstanceFactory());
    }

    @Test
    public  void getInstanceFactoryForTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IInstanceFactory instanceFactory = () -> null;

        assertNull(config.getInstanceFactoryFor(klass));

        config.registerInstanceFactory(klass, instanceFactory);

        assertSame(instanceFactory, config.getInstanceFactoryFor(klass));
    }

    @Test
    public void registerDriverSupplierTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IDriverSupplier driverSupplier = () -> null;

        config.registerDriverSupplier(klass, driverSupplier);
        assertNotNull(config.getClassConfigs().get(klass));
        assertSame(driverSupplier, config.getClassConfigs().get(klass).getDriverSupplier());

        driverSupplier = () -> null;
        config.registerDriverSupplier(klass, driverSupplier);

        assertSame(driverSupplier, config.getClassConfigs().get(klass).getDriverSupplier());
    }

    @Test
    public void getDriverSupplierForTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IDriverSupplier driverSupplier = () -> null;

        assertNull(config.getDriverSupplierFor(klass));

        config.registerDriverSupplier(klass, driverSupplier);

        assertSame(driverSupplier, config.getDriverSupplierFor(klass));
    }

    @Test
    public void registerDriverLoaderClassTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IDriverLoader driverLoader = (k) -> {};

        config.registerDriverLoader(klass, driverLoader);
        assertNotNull(config.getClassConfigs().get(klass));
        assertSame(driverLoader, config.getClassConfigs().get(klass).getDriverLoader());

        driverLoader = (k) -> {};
        config.registerDriverLoader(klass, driverLoader);
        assertSame(driverLoader, config.getClassConfigs().get(klass).getDriverLoader());
    }

    @Test
    public void getDriverLoaderClassForTest() {
        ScrapperConfig config = new ScrapperConfig();
        Class<?> klass = Object.class;
        IDriverLoader driverLoader = (k) -> {};

        assertNull(config.getDriverLoaderFor(klass));

        config.registerDriverLoader(klass, driverLoader);

        assertSame(driverLoader, config.getDriverLoaderFor(klass));
    }

    @Test
    public void registerDriverLoaderFieldTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IDriverLoader driverLoader = (k) -> {};

        config.registerDriverLoader(field, driverLoader);
        assertNotNull(config.getFieldConfigs().get(field));
        assertSame(driverLoader, config.getFieldConfigs().get(field).getDriverLoader());

        driverLoader = (k) -> {};
        config.registerDriverLoader(field, driverLoader);
        assertSame(driverLoader, config.getFieldConfigs().get(field).getDriverLoader());
    }

    @Test
    public void getDriverLoaderFieldForTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IDriverLoader driverLoader = (k) -> {};

        assertNull(config.getDriverLoaderFor(field));

        config.registerDriverLoader(field, driverLoader);

        assertSame(driverLoader, config.getDriverLoaderFor(field));
    }

    @Test
    public void registerSelectorTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        ISelector selector = (d) -> null;

        config.registerSelector(field, selector);
        assertNotNull(config.getFieldConfigs().get(field));
        assertSame(selector, config.getFieldConfigs().get(field).getSelector());

        selector = (d) -> null;
        config.registerSelector(field, selector);
        assertSame(selector, config.getFieldConfigs().get(field).getSelector());
    }

    @Test
    public void getSelectorForTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        ISelector selector = (d) -> null;

        assertNull(config.getSelectorFor(field));

        config.registerSelector(field, selector);

        assertSame(selector, config.getSelectorFor(field));
    }

    @Test
    public void registerElementLoaderTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IElementLoader elementLoader = (d, e) -> {};

        config.registerElementLoader(field, elementLoader);
        assertNotNull(config.getFieldConfigs().get(field));
        assertSame(elementLoader, config.getFieldConfigs().get(field).getElementLoader());

        elementLoader = (d, e) -> {};
        config.registerElementLoader(field, elementLoader);
        assertSame(elementLoader, config.getFieldConfigs().get(field).getElementLoader());

    }

    @Test
    public void getElementLoaderForTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IElementLoader elementLoader = (d, e) -> {};

        assertNull(config.getElementLoaderFor(field));

        config.registerElementLoader(field, elementLoader);

        assertSame(elementLoader, config.getElementLoaderFor(field));
    }

    @Test
    public void registerParserTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IParser parser = (d, e) -> null;

        config.registerParser(field, parser);
        assertNotNull(config.getFieldConfigs().get(field));
        assertSame(parser, config.getFieldConfigs().get(field).getParser());

        parser = (d, e) -> null;
        config.registerParser(field, parser);
        assertSame(parser, config.getFieldConfigs().get(field).getParser());
    }

    @Test
    public void getParserForTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IParser parser = (d, e) -> null;

        assertNull(config.getParserFor(field));

        config.registerParser(field, parser);

        assertSame(parser, config.getParserFor(field));
    }

    @Test
    public void registerPropertyTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object instance, Object value) { }
        };

        config.registerProperty(field, property);
        assertNotNull(config.getFieldConfigs().get(field));
        assertSame(property, config.getFieldConfigs().get(field).getProperty());

        property = new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object instance, Object value) { }
        };
        config.registerProperty(field, property);
        assertSame(property, config.getFieldConfigs().get(field).getProperty());
    }

    @Test
    public void getPropertyForTest() throws NoSuchFieldException {
        ScrapperConfig config = new ScrapperConfig();
        Field field = ScrapperConfig.class.getDeclaredField("classConfigs");
        IProperty property = new IProperty() {
            @Override public Object get(Object instance) { return null; }
            @Override public void set(Object instance, Object value) { }
        };

        assertNull(config.getPropertyFor(field));

        config.registerProperty(field, property);

        assertSame(property, config.getPropertyFor(field));
    }

}
