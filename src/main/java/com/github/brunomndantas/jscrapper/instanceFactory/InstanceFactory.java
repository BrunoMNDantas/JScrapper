package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;

public abstract class InstanceFactory implements IInstanceFactory {

    @Override
    public <T> T create(Class<T> klass) throws InstanceFactoryException {
        try {
            return createInstance(klass);
        } catch (Exception e) {
            String msg = "Error creating instance of class:" + klass.getName();
            throw new InstanceFactoryException(msg, e);
        }
    }



    protected abstract <T> T createInstance(Class<T> klass) throws Exception;

}
