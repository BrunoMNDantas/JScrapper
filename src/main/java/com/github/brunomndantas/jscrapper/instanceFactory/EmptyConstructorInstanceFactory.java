package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.Utils;

public class EmptyConstructorInstanceFactory extends InstanceFactory {

    @Override
    protected <T> T createInstance(Class<T> klass) throws Exception {
        return Utils.createInstance(klass);
    }

}
