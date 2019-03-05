package com.github.brunomndantas.jscrapper.property;

import java.lang.reflect.Field;

public class FieldProperty extends Property {

    public FieldProperty(Field field) {
        super(field);
    }



    @Override
    protected Object getValue(Object instance) throws Exception {
        this.field.setAccessible(true);
        return this.field.get(instance);
    }

    @Override
    protected void setValue(Object instance, Object value) throws Exception {
        this.field.setAccessible(true);
        this.field.set(instance, value);
    }

}
