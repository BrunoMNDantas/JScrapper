package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.property.annotation.Property;

import java.lang.reflect.Field;

public class PropertyFactory {

    public static IProperty create(Class<?> klass, Field field) throws ScrapperException {
        IProperty property  = createUserDefinedProperty(klass, field);

        if(property != null)
            return property;

        return createDefaultProperty(klass, field);
    }

    private static IProperty createUserDefinedProperty(Class<?> klass, Field field) throws ScrapperException {
        Property annotation = field.getAnnotation(Property.class);

        if(annotation == null)
        return null;

        Class<? extends IProperty> propertyClass = annotation.value();
        return Utils.createInstance(propertyClass);
    }

    public static IProperty createDefaultProperty(Class<?> klass, Field field) {
        boolean hasGetter = MethodProperty.getGetter(klass, field) != null;
        boolean hasSetter = MethodProperty.getSetter(klass, field) != null;

        if(!hasGetter && !hasSetter)
            return new FieldProperty(field);
        else if(!hasGetter && hasSetter)
            return new ComposedProperty(new FieldProperty(field), new MethodProperty(field));
        else if(hasGetter && !hasSetter)
            return new ComposedProperty(new MethodProperty(field), new FieldProperty(field));

        return new MethodProperty(field);
    }

}
