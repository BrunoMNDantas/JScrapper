package com.github.brunomndantas.jscrapper.property;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public class MethodProperty extends Property {

    public static Method getGetter(Class klass, Field field) {
        for(Method method : klass.getDeclaredMethods()) {
            if(isGetter(method, field))
                return method;
        }

        return null;
    }

    public static boolean isGetter(Method method, Field field) {
        String fieldName = field.getName();
        String getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
        String getterBooleanName = "is" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        return (method.getName().equals(getterName) || method.getName().equals(getterBooleanName)) &&
                method.getParameterCount() == 0 &&
                method.getReturnType().isAssignableFrom(field.getType());
    }

    public static Method getSetter(Class klass, Field field) {
        for(Method method : klass.getDeclaredMethods()) {
            if(isSetter(method, field))
                return method;
        }

        return null;
    }

    public static boolean isSetter(Method method, Field field) {
        String fieldName = field.getName();
        String setterName = "set" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);

        return method.getName().equals(setterName) &&
                method.getParameterCount() == 1 &&
                field.getType().isAssignableFrom(method.getParameters()[0].getType()) &&
                method.getReturnType().equals(Void.TYPE);
    }



    public MethodProperty(Field field) {
        super(field);
    }



    @Override
    protected Object getValue(Object instance) throws Exception {
        Method getter = MethodProperty.getGetter(instance.getClass(), this.field);

        if(getter == null)
            throw new Exception("There is no getter method for field:" + this.field.getName() + " of class:" + instance.getClass().getName());

        getter.setAccessible(true);
        return getter.invoke(instance);
    }

    @Override
    protected void setValue(Object instance, Object value) throws Exception {
        Method setter = MethodProperty.getSetter(instance.getClass(), this.field);

        if(setter == null)
            throw new Exception("There is no setter method for field:" + this.field.getName() + " of class:" + instance.getClass().getName());

        setter.setAccessible(true);
        setter.invoke(instance, value);
    }

}
