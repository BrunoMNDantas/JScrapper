package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import org.junit.Test;

import java.util.Collection;
import java.util.LinkedList;

import static org.junit.Assert.*;

public class ClassConfigTest {

    @Test
    public void getKlassTest() {
        Class klass = Object.class;

        ClassConfig config = new ClassConfig(klass, null, null, null, null);

        assertSame(klass, config.getKlass());
    }

    @Test
    public void setKlassTest() {
        Class klass = Object.class;

        ClassConfig config = new ClassConfig(null, null, null, null, null);

        config.setKlass(klass);
        assertSame(klass, config.getKlass());
    }

    @Test
    public void getInstanceFactoryTest() {
        IInstanceFactory factory = () -> null;

        ClassConfig config = new ClassConfig(null, factory, null, null, null);

        assertSame(factory, config.getInstanceFactory());
    }

    @Test
    public void setInstanceFactoryTest() {
        IInstanceFactory factory = () -> null;

        ClassConfig config = new ClassConfig(null, null, null, null, null);

        config.setInstanceFactory(factory);
        assertSame(factory, config.getInstanceFactory());
    }

    @Test
    public void getDriverSupplierTest() {
        IDriverSupplier supplier = () -> null;

        ClassConfig config = new ClassConfig(null, null, supplier, null, null);

        assertSame(supplier, config.getDriverSupplier());
    }

    @Test
    public void setDriverSupplierTest() {
        IDriverSupplier supplier = () -> null;

        ClassConfig config = new ClassConfig(null, null, null, null, null);

        config.setDriverSupplier(supplier);
        assertSame(supplier, config.getDriverSupplier());
    }

    @Test
    public void getDriverLoaderTest() {
        IDriverLoader loader =  driver -> {};

        ClassConfig config = new ClassConfig(null, null, null, loader, null);

        assertSame(loader, config.getDriverLoader());
    }

    @Test
    public void setDriverLoaderTest() {
        IDriverLoader loader =  driver -> {};

        ClassConfig config = new ClassConfig(null, null, null, null, null);

        config.setDriverLoader(loader);
        assertSame(loader, config.getDriverLoader());
    }

    @Test
    public void getFieldsConfigTest() {
        Collection<FieldConfig> fieldsConfig = new LinkedList<>();

        ClassConfig config = new ClassConfig(null, null, null, null, fieldsConfig);

        assertSame(fieldsConfig, config.getFieldsConfig());
    }

    @Test
    public void setFieldsConfigTest() {
        Collection<FieldConfig> fieldsConfig = new LinkedList<>();

        ClassConfig config = new ClassConfig(null, null, null, null, null);

        config.setFieldsConfig(fieldsConfig);
        assertSame(fieldsConfig, config.getFieldsConfig());
    }

    @Test
    public void constructorsTest() {
        Class klass = Object.class;
        IInstanceFactory factory = () -> null;
        IDriverSupplier supplier = () -> null;
        IDriverLoader loader =  driver -> {};
        Collection<FieldConfig> fieldsConfig = new LinkedList<>();

        ClassConfig config = new ClassConfig();

        assertNull(config.getKlass());
        assertNull(config.getInstanceFactory());
        assertNull(config.getDriverSupplier());
        assertNull(config.getDriverLoader());
        assertNotNull(config.getFieldsConfig());

        config = new ClassConfig(klass);

        assertSame(klass, config.getKlass());
        assertNull(config.getInstanceFactory());
        assertNull(config.getDriverSupplier());
        assertNull(config.getDriverLoader());
        assertNotNull(config.getFieldsConfig());


        config = new ClassConfig(klass, factory, supplier, loader, fieldsConfig);

        assertSame(klass, config.getKlass());
        assertSame(factory, config.getInstanceFactory());
        assertSame(supplier, config.getDriverSupplier());
        assertSame(loader, config.getDriverLoader());
        assertSame(fieldsConfig, config.getFieldsConfig());
    }

}
