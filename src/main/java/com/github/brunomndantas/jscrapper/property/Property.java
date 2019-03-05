package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;

import java.lang.reflect.Field;

public abstract class Property implements IProperty {

    protected Field field;
    public Field getField() { return this.field; }



    public Property(Field field) {
        this.field = field;
    }



    @Override
    public Object get(Object instance) throws PropertyException {
        try {
            return getValue(instance);
        } catch (Exception e) {
            String msg = "Error getting value of field:" + this.field.getName() +
                    " of class:" + instance.getClass().getName();
            throw new PropertyException(msg, e);
        }
    }

    @Override
    public void set(Object instance, Object value) throws PropertyException {
        try {
            setValue(instance, value);
        } catch (Exception e) {
            String msg = "Error setting value of field:" + this.field.getName() +
                    " of class:" + instance.getClass().getName();
            throw new PropertyException(msg, e);
        }
    }



    protected abstract Object getValue(Object instance) throws Exception;
    protected abstract void setValue(Object instance, Object value) throws Exception;

}
