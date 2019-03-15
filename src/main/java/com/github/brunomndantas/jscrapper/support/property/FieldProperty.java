package com.github.brunomndantas.jscrapper.support.property;

import java.lang.reflect.Field;

public class FieldProperty extends Property {

    private Field field;
    public Field getField() { return this.field; }



    public FieldProperty(Field field) {
        this.field = field;
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
