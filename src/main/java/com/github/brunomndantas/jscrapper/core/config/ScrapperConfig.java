package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ScrapperConfig {

    private Map<Class<?>, ClassConfig> classConfigs;
    public Map<Class<?>, ClassConfig> getClassConfigs() { return this.classConfigs; }
    public void setClassConfigs(Map<Class<?>, ClassConfig> classConfigs) { this.classConfigs = classConfigs; }

    private Map<Field, FieldConfig> fieldConfigs;
    public Map<Field, FieldConfig> getFieldConfigs() { return this.fieldConfigs; }
    public void setFieldConfigs(Map<Field, FieldConfig> fieldConfigs) { this.fieldConfigs = fieldConfigs; }



    public ScrapperConfig(Map<Class<?>, ClassConfig> classConfigs, Map<Field, FieldConfig> fieldConfigs) {
        this.classConfigs = classConfigs;
        this.fieldConfigs = fieldConfigs;
    }

    public ScrapperConfig() {
        this(new HashMap<>(), new HashMap<>());
    }



    public void registerClassConfig(ClassConfig classConfig) {
        this.classConfigs.put(classConfig.getKlass(), classConfig);
    }

    public ClassConfig getConfigFor(Class<?> klass) {
        return this.classConfigs.get(klass);
    }

    public void registerFieldConfig(FieldConfig fieldConfig) {
        this.fieldConfigs.put(fieldConfig.getField(), fieldConfig);
    }

    public FieldConfig getConfigFor(Field field) {
        return this.fieldConfigs.get(field);
    }

    public void registerInstanceFactory(Class<?> klass, IInstanceFactory instanceFactory) {
        createClassConfigIfNotExists(klass);
        getConfigFor(klass).setInstanceFactory(instanceFactory);
    }

    public IInstanceFactory getInstanceFactoryFor(Class<?> klass) {
        createClassConfigIfNotExists(klass);
        return this.getConfigFor(klass).getInstanceFactory();
    }

    public void registerDriverSupplier(Class<?> klass, IDriverSupplier driverSupplier) {
        createClassConfigIfNotExists(klass);
        getConfigFor(klass).setDriverSupplier(driverSupplier);
    }

    public IDriverSupplier getDriverSupplierFor(Class<?> klass) {
        createClassConfigIfNotExists(klass);
        return this.getConfigFor(klass).getDriverSupplier();
    }

    public void registerDriverLoader(Class<?> klass, IDriverLoader driverLoader) {
        createClassConfigIfNotExists(klass);
        getConfigFor(klass).setDriverLoader(driverLoader);
    }

    public IDriverLoader getDriverLoaderFor(Class<?> klass) {
        createClassConfigIfNotExists(klass);
        return this.getConfigFor(klass).getDriverLoader();
    }

    public void registerDriverLoader(Field field, IDriverLoader driverLoader) {
        createFieldConfigIfNotExists(field);
        getConfigFor(field).setDriverLoader(driverLoader);
    }

    public IDriverLoader getDriverLoaderFor(Field field) {
        createFieldConfigIfNotExists(field);
        return this.getConfigFor(field).getDriverLoader();
    }

    public void registerSelector(Field field, ISelector selector) {
        createFieldConfigIfNotExists(field);
        getConfigFor(field).setSelector(selector);
    }

    public ISelector getSelectorFor(Field field) {
        createFieldConfigIfNotExists(field);
        return this.getConfigFor(field).getSelector();
    }

    public void registerElementLoader(Field field, IElementLoader elementLoader) {
        createFieldConfigIfNotExists(field);
        getConfigFor(field).setElementLoader(elementLoader);
    }

    public IElementLoader getElementLoaderFor(Field field) {
        createFieldConfigIfNotExists(field);
        return this.getConfigFor(field).getElementLoader();
    }

    public void registerParser(Field field, IParser parser) {
        createFieldConfigIfNotExists(field);
        getConfigFor(field).setParser(parser);
    }

    public IParser getParserFor(Field field) {
        createFieldConfigIfNotExists(field);
        return this.getConfigFor(field).getParser();
    }

    public void registerProperty(Field field, IProperty property) {
        createFieldConfigIfNotExists(field);
        getConfigFor(field).setProperty(property);
    }

    public IProperty getPropertyFor(Field field) {
        createFieldConfigIfNotExists(field);
        return this.getConfigFor(field).getProperty();
    }

    private void createClassConfigIfNotExists(Class<?> klass) {
        this.classConfigs.putIfAbsent(klass, new ClassConfig(klass));
    }

    private void createFieldConfigIfNotExists(Field field) {
        this.fieldConfigs.putIfAbsent(field, new FieldConfig(field));
    }

}
