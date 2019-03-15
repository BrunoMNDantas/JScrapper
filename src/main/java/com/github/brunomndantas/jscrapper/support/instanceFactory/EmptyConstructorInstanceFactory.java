package com.github.brunomndantas.jscrapper.support.instanceFactory;

import com.github.brunomndantas.jscrapper.Utils;

public class EmptyConstructorInstanceFactory extends InstanceFactory {

    private Class klass;
    public Class getKlass() { return this.klass; }



    public EmptyConstructorInstanceFactory(Class<?> klass) {
        this.klass = klass;
    }



    @Override
    protected Object createInstance() throws Exception {
        return Utils.createInstance(this.klass);
    }

}
