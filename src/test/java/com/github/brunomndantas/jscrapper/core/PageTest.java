package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;

public class PageTest {

    @Test
    public void etIdTest() {
        String id = "";
        Page page = new Page(id, null, null, null, null);
        assertSame(id, page.getId());
    }

    @Test
    public void getUrlTest() {
        String url = "";
        Page page = new Page(null, url, null, null, null);
        assertSame(url, page.getUrl());
    }

    @Test
    public void getDriverLoaderTest() {
        IDriverLoader driverLoader = driver -> { };
        Page page = new Page(null, null, driverLoader, null, null);
        assertSame(driverLoader, page.getDriverLoader());
    }

    @Test
    public void getDriverSupplierTest() {
        IDriverSupplier driverSupplier = () -> null;
        Page page = new Page(null, null, null, driverSupplier, null);
        assertSame(driverSupplier, page.getDriverSupplier());
    }

    @Test
    public void getElementsTest() {
        Collection<Element> elements = new LinkedList<>();
        Page page = new Page(null, null, null, null, elements);
        assertSame(elements, page.getElements());
    }

    @Test
    public void constructorTest() {
        String id = "";
        String url = "";
        IDriverLoader driverLoader = driver -> { };
        IDriverSupplier driverSupplier = () -> null;
        Collection<Element> elements = new LinkedList<>();

        Page page = new Page(id, url, driverLoader, driverSupplier, elements);

        assertSame(id, page.getId());
        assertSame(url, page.getUrl());
        assertSame(driverLoader, page.getDriverLoader());
        assertSame(driverSupplier, page.getDriverSupplier());
        assertSame(elements, page.getElements());
    }

}
