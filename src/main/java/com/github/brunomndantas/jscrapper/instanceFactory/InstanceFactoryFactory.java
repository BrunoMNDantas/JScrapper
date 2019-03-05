package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.instanceFactory.annotation.InstanceFactory;

public class InstanceFactoryFactory {

    public static IInstanceFactory create(Class<?> klass) throws ScrapperException {
        InstanceFactory annotation = klass.getAnnotation(InstanceFactory.class);

        if(annotation == null)
            throw new ScrapperException("No InstanceFactory found!");

        Class<? extends IInstanceFactory> instanceFactoryClass = annotation.value();
        return Utils.createInstance(instanceFactoryClass);
    }

}
