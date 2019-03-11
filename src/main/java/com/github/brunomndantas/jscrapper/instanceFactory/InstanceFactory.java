package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;

public abstract class InstanceFactory implements IInstanceFactory {

    protected Class<?> klass;
    public Class<?> getKlass() { return this.klass; }



    public InstanceFactory(Class<?> klass) {
        this.klass = klass;
    }



    @Override
    public Object create() throws InstanceFactoryException {
        try {
            return createInstance();
        } catch (Exception e) {
            String msg = "Error creating instance of class:" + klass.getName();
            throw new InstanceFactoryException(msg, e);
        }
    }



    protected abstract Object createInstance() throws Exception;

}
