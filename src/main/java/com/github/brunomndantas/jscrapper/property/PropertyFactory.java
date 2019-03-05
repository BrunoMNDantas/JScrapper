package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.property.annotation.Property;

import java.lang.reflect.Field;

public class PropertyFactory {

    public static IProperty create(Class<?> klass, Field field) throws ScrapperException {
        Property annotation = field.getAnnotation(Property.class);

        if(annotation == null)
            throw new ScrapperException("No PropertyFound found!");

        Class<? extends IProperty> propertyClass = annotation.value();
        return Utils.createInstance(propertyClass);
    }

}
