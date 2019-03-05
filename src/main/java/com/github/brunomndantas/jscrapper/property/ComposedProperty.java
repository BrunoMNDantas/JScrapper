package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;

public class ComposedProperty implements IProperty {

    protected IProperty getter;
    public IProperty getGetter() { return this.getter; }

    protected IProperty setter;
    public IProperty getSetter() { return this.setter; }



    public ComposedProperty(IProperty getter, IProperty setter) {
        this.getter = getter;
        this.setter = setter;
    }



    @Override
    public Object get(Object instance) throws PropertyException {
        return this.getter.get(instance);
    }

    @Override
    public void set(Object instance, Object value) throws PropertyException {
        this.setter.set(instance, value);
    }

}
