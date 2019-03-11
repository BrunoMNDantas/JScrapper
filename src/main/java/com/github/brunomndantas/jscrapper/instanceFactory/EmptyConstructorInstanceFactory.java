package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.Utils;

public class EmptyConstructorInstanceFactory extends InstanceFactory {

    public EmptyConstructorInstanceFactory(Class<?> klass) {
        super(klass);
    }



    @Override
    protected Object createInstance() throws Exception {
        return Utils.createInstance(super.getKlass());
    }

}
