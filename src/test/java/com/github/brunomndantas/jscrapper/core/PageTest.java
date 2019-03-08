package com.github.brunomndantas.jscrapper.core;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.assertSame;

public class PageTest {

    @Test
    public void getIdTest() {
        String id = "";
        Page page = new Page(id, null, null, null, null);
        assertSame(id, page.getId());
    }

    @Test
    public void getDriverLoaderTest() {
        IDriverLoader driverLoader = driver -> { };
        Page page = new Page(null, driverLoader, null, null, null);
        assertSame(driverLoader, page.getDriverLoader());
    }

    @Test
    public void getDriverSupplierTest() {
        IDriverSupplier driverSupplier = () -> null;
        Page page = new Page(null, null, driverSupplier, null, null);
        assertSame(driverSupplier, page.getDriverSupplier());
    }

    @Test
    public void getInstanceFactoryTest() {
        IInstanceFactory factory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        Page page = new Page(null,  null, null, factory, null);
        assertSame(factory, page.getInstanceFactory());
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
        IDriverLoader driverLoader = driver -> { };
        IDriverSupplier driverSupplier = () -> null;
        IInstanceFactory instanceFactory = new IInstanceFactory() {
            @Override public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }
        };
        Collection<Element> elements = new LinkedList<>();

        Page page = new Page(id, driverLoader, driverSupplier, instanceFactory, elements);

        assertSame(id, page.getId());
        assertSame(driverLoader, page.getDriverLoader());
        assertSame(driverSupplier, page.getDriverSupplier());
        assertSame(instanceFactory, page.getInstanceFactory());
        assertSame(elements, page.getElements());
    }

}
