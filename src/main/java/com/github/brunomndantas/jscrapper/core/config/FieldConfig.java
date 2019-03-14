package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;

import java.lang.reflect.Field;

public class FieldConfig {

    private Field field;
    public Field getField() { return this.field; }
    public void setField(Field field) { this.field = field; }

    private IDriverLoader driverLoader;
    public IDriverLoader getDriverLoader() { return driverLoader; }
    public void setDriverLoader(IDriverLoader driverLoader) { this.driverLoader = driverLoader; }

    private ISelector selector;
    public ISelector getSelector() { return selector; }
    public void setSelector(ISelector selector) { this.selector = selector; }

    private IElementLoader elementLoader;
    public IElementLoader getElementLoader() { return elementLoader; }
    public void setElementLoader(IElementLoader elementLoader) { this.elementLoader = elementLoader; }

    private IParser parser;
    public IParser getParser() { return parser; }
    public void setParser(IParser parser) { this.parser = parser; }

    private IProperty property;
    public IProperty getProperty() { return property; }
    public void setProperty(IProperty property) { this.property = property; }



    public FieldConfig(Field field, IDriverLoader driverLoader, ISelector selector, IElementLoader elementLoader, IParser parser, IProperty property) {
        this.field = field;
        this.driverLoader = driverLoader;
        this.selector = selector;
        this.elementLoader = elementLoader;
        this.parser = parser;
        this.property = property;
    }

    public FieldConfig(Field field) {
        this.field = field;
    }

    public FieldConfig() { }

}
