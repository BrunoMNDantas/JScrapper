package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.pageLoader.IPageLoader;

import java.util.Collection;

public class Page {

    private String id;
    public String getId() { return this.id; }

    private String url;
    public String getUrl() { return this.url; }

    private IPageLoader pageLoader;
    public IPageLoader getPageLoader() { return this.pageLoader; }

    private IDriverSupplier driverSupplier;
    public IDriverSupplier getDriverSupplier() { return this.driverSupplier; }

    private Collection<Element> elements;
    public Collection<Element> getElements() { return this.elements; }



    public Page(String id, String url, IPageLoader pageLoader, IDriverSupplier driverSupplier, Collection<Element> elements) {
        this.id = id;
        this.url = url;
        this.pageLoader = pageLoader;
        this.driverSupplier = driverSupplier;
        this.elements = elements;
    }

}
