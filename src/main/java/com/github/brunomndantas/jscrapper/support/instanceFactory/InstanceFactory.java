package com.github.brunomndantas.jscrapper.support.instanceFactory;

import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;

public abstract class InstanceFactory implements IInstanceFactory {

    @Override
    public Object create() throws InstanceFactoryException {
        try {
            return createInstance();
        } catch (Exception e) {
            String msg = "Error creating instance!";
            throw new InstanceFactoryException(msg, e);
        }
    }



    protected abstract Object createInstance() throws Exception;

}
