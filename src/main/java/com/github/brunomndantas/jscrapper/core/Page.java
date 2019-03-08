package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;

import java.util.Collection;

public class Page {

    private String id;
    public String getId() { return this.id; }

    private IDriverLoader driverLoader;
    public IDriverLoader getDriverLoader() { return this.driverLoader; }

    private IDriverSupplier driverSupplier;
    public IDriverSupplier getDriverSupplier() { return this.driverSupplier; }

    private IInstanceFactory instanceFactory;
    public IInstanceFactory getInstanceFactory() { return this.instanceFactory; }

    private Collection<Element> elements;
    public Collection<Element> getElements() { return this.elements; }



    public Page(String id, IDriverLoader driverLoader, IDriverSupplier driverSupplier, IInstanceFactory instanceFactory, Collection<Element> elements) {
        this.id = id;
        this.driverLoader = driverLoader;
        this.driverSupplier = driverSupplier;
        this.instanceFactory = instanceFactory;
        this.elements = elements;
    }

}
