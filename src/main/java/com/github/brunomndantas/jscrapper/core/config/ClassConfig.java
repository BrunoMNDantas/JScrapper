package com.github.brunomndantas.jscrapper.core.config;

import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;

import java.util.Collection;
import java.util.LinkedList;

public class ClassConfig {

    private Class<?> klass;
    public Class<?> getKlass() { return this.klass; }
    public void setKlass(Class<?> klass) { this.klass = klass; }

    private IInstanceFactory instanceFactory;
    public IInstanceFactory getInstanceFactory() { return instanceFactory; }
    public void setInstanceFactory(IInstanceFactory instanceFactory) { this.instanceFactory = instanceFactory; }

    private IDriverSupplier driverSupplier;
    public IDriverSupplier getDriverSupplier() { return driverSupplier; }
    public void setDriverSupplier(IDriverSupplier driverSupplier) { this.driverSupplier = driverSupplier; }

    private IDriverLoader driverLoader;
    public IDriverLoader getDriverLoader() { return driverLoader; }
    public void setDriverLoader(IDriverLoader driverLoader) { this.driverLoader = driverLoader; }

    private Collection<FieldConfig> fieldsConfig;
    public Collection<FieldConfig> getFieldsConfig() { return this.fieldsConfig; }
    public void  setFieldsConfig(Collection<FieldConfig> fieldsConfig) { this.fieldsConfig = fieldsConfig; }



    public ClassConfig(Class<?> klass, IInstanceFactory instanceFactory, IDriverSupplier driverSupplier, IDriverLoader driverLoader, Collection<FieldConfig> fieldsConfig) {
        this.klass = klass;
        this.instanceFactory = instanceFactory;
        this.driverSupplier = driverSupplier;
        this.driverLoader = driverLoader;
        this.fieldsConfig = fieldsConfig;
    }

    public ClassConfig(Class<?> klass) {
        this(klass, null, null, null, new LinkedList<>());
        this.klass = klass;
    }

    public ClassConfig() {
        this(null, null, null, null, new LinkedList<>());
    }

}
