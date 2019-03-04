package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;

import java.util.Collection;

public class Page {

    private String id;
    public String getId() { return this.id; }

    private String url;
    public String getUrl() { return this.url; }

    private IDriverLoader driverLoader;
    public IDriverLoader getDriverLoader() { return this.driverLoader; }

    private IDriverSupplier driverSupplier;
    public IDriverSupplier getDriverSupplier() { return this.driverSupplier; }

    private Collection<Element> elements;
    public Collection<Element> getElements() { return this.elements; }



    public Page(String id, String url, IDriverLoader driverLoader, IDriverSupplier driverSupplier, Collection<Element> elements) {
        this.id = id;
        this.url = url;
        this.driverLoader = driverLoader;
        this.driverSupplier = driverSupplier;
        this.elements = elements;
    }

}
