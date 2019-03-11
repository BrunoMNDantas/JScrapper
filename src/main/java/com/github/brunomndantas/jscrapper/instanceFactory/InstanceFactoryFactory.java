package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.instanceFactory.annotation.InstanceFactory;

public class InstanceFactoryFactory {

    public static IInstanceFactory create(Class<?> klass) throws ScrapperException {
        IInstanceFactory factory = createUserDefinedFactory(klass);

        if(factory != null)
            return factory;

        return createDefaultFactory(klass);
    }

    private static IInstanceFactory createUserDefinedFactory(Class<?> klass) throws ScrapperException {
        com.github.brunomndantas.jscrapper.instanceFactory.annotation.InstanceFactory annotation = klass.getAnnotation(InstanceFactory.class);

        if(annotation == null)
            return null;

        Class<? extends IInstanceFactory> instanceFactoryClass = annotation.value();
        return Utils.createInstance(instanceFactoryClass);
    }

    private static IInstanceFactory createDefaultFactory(Class<?> klass) {
        return new EmptyConstructorInstanceFactory(klass);
    }

}
