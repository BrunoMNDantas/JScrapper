package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.pageLoader.IPageLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;

public class Element {

    private String id;
    public String getId() { return this.id; }

    private IPageLoader pageLoader;
    public IPageLoader getPageLoader() { return this.pageLoader; }

    private ISelector selector;
    public ISelector getSelector() { return this.selector; }

    private IElementLoader elementLoader;
    public IElementLoader getElementLoader() { return this.elementLoader; }

    private IParser parser;
    public IParser getParser() { return this.parser; }

    private IProperty property;
    public IProperty getProperty() { return this.property; }



    public Element(String id, IPageLoader pageLoader, ISelector selector, IElementLoader elementLoader, IParser parser, IProperty property) {
        this.id = id;
        this.pageLoader = pageLoader;
        this.selector = selector;
        this.elementLoader = elementLoader;
        this.parser = parser;
        this.property = property;
    }

}
