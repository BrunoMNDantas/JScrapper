package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.support.parser.array.ArrayWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.array.text.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.array.text.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.collection.CollectionWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.collection.text.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.single.SingleWebDriverParser;
import com.github.brunomndantas.jscrapper.support.parser.single.SingleWebElementParser;
import com.github.brunomndantas.jscrapper.support.parser.single.text.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.single.text.reference.*;
import com.github.brunomndantas.jscrapper.support.property.ComposedProperty;
import com.github.brunomndantas.jscrapper.support.property.FieldProperty;
import com.github.brunomndantas.jscrapper.support.property.MethodProperty;
import com.github.brunomndantas.jscrapper.support.selector.IdSelector;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;

public class DefaultFieldConfig {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";



    public static FieldConfig getFieldConfig(Field field) throws ScrapperException {
        FieldConfig config = new FieldConfig(field);

        config.setDriverLoader(getDriverLoader(field));
        config.setSelector(getSelector(field));
        config.setElementLoader(getElementLoader(field));
        config.setParser(getParser(field));
        config.setProperty(getProperty(field));

        return config;
    }


    public static IDriverLoader getDriverLoader(Field field) throws ScrapperException {
        return (diver) -> {};
    }


    public static ISelector getSelector(Field field) throws ScrapperException {
       return new IdSelector(field.getName());
    }


    public static IElementLoader getElementLoader(Field field) throws ScrapperException {
        return (driver, elements) -> {};
    }


    public static IParser getParser(Field field) throws ScrapperException {
        if(field.getType().isArray())
            return getArrayParser(field);

        if(field.getType().equals(Collection.class))
            return getCollectionParser(field);

        return getSingleParser(field);
    }

    private static IParser getArrayParser(Field field) throws ScrapperException {
        if(field.getType().getComponentType().equals(boolean.class))
            return new ArrayPrimitiveBooleanTextParser();

        if(field.getType().getComponentType().equals(byte.class))
            return new ArrayPrimitiveByteTextParser();

        if(field.getType().getComponentType().equals(char.class))
            return new ArrayPrimitiveCharacterTextParser();

        if(field.getType().getComponentType().equals(double.class))
            return new ArrayPrimitiveDoubleTextParser();

        if(field.getType().getComponentType().equals(float.class))
            return new ArrayPrimitiveFloatTextParser();

        if(field.getType().getComponentType().equals(int.class))
            return new ArrayPrimitiveIntegerTextParser();

        if(field.getType().getComponentType().equals(long.class))
            return new ArrayPrimitiveLongTextParser();

        if(field.getType().getComponentType().equals(short.class))
            return new ArrayPrimitiveShortTextParser();


        if(field.getType().getComponentType().equals(Boolean.class))
            return new ArrayReferenceBooleanTextParser();

        if(field.getType().getComponentType().equals(Byte.class))
            return new ArrayReferenceByteTextParser();

        if(field.getType().getComponentType().equals(Character.class))
            return new ArrayReferenceCharacterTextParser();

        if(field.getType().getComponentType().equals(Date.class))
            return new ArrayReferenceDateTextParser(DEFAULT_DATE_FORMAT);

        if(field.getType().getComponentType().equals(Double.class))
            return new ArrayReferenceDoubleTextParser();

        if(field.getType().getComponentType().equals(Float.class))
            return new ArrayReferenceFloatTextParser();

        if(field.getType().getComponentType().equals(Integer.class))
            return new ArrayReferenceIntegerTextParser();

        if(field.getType().getComponentType().equals(Long.class))
            return new ArrayReferenceLongTextParser();

        if(field.getType().getComponentType().equals(Short.class))
            return new ArrayReferenceShortTextParser();

        if(field.getType().getComponentType().equals(String.class))
            return new ArrayReferenceStringTextParser();

        if(field.getType().getComponentType().equals(WebElement.class))
            return new ArrayWebElementParser();

        return null;
    }

    private static IParser getCollectionParser(Field field) throws ScrapperException {
        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Boolean.class))
            return new CollectionReferenceBooleanTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Byte.class))
            return new CollectionReferenceByteTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Character.class))
            return new CollectionReferenceCharacterTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Date.class))
            return new CollectionReferenceDateTextParser(DEFAULT_DATE_FORMAT);

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Double.class))
            return new CollectionReferenceDoubleTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Float.class))
            return new CollectionReferenceFloatTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Integer.class))
            return new CollectionReferenceIntegerTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Long.class))
            return new CollectionReferenceLongTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Short.class))
            return new CollectionReferenceShortTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(String.class))
            return new CollectionReferenceStringTextParser();

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(WebElement.class))
            return new CollectionWebElementParser();

        return null;
    }

    private static IParser getSingleParser(Field field) throws ScrapperException {
        if(field.getType().equals(boolean.class))
            return new SinglePrimitiveBooleanTextParser();

        if(field.getType().equals(byte.class))
            return new SinglePrimitiveByteTextParser();

        if(field.getType().equals(char.class))
            return new SinglePrimitiveCharacterTextParser();

        if(field.getType().equals(double.class))
            return new SinglePrimitiveDoubleTextParser();

        if(field.getType().equals(float.class))
            return new SinglePrimitiveFloatTextParser();

        if(field.getType().equals(int.class))
            return new SinglePrimitiveIntegerTextParser();

        if(field.getType().equals(long.class))
            return new SinglePrimitiveLongTextParser();

        if(field.getType().equals(short.class))
            return new SinglePrimitiveShortTextParser();


        if(field.getType().equals(Boolean.class))
            return new SingleReferenceBooleanTextParser();

        if(field.getType().equals(Byte.class))
            return new SingleReferenceByteTextParser();

        if(field.getType().equals(Character.class))
            return new SingleReferenceCharacterTextParser();

        if(field.getType().equals(Date.class))
            return new SingleReferenceDateTextParser(DEFAULT_DATE_FORMAT);

        if(field.getType().equals(Double.class))
            return new SingleReferenceDoubleTextParser();

        if(field.getType().equals(Float.class))
            return new SingleReferenceFloatTextParser();

        if(field.getType().equals(Integer.class))
            return new SingleReferenceIntegerTextParser();

        if(field.getType().equals(Long.class))
            return new SingleReferenceLongTextParser();

        if(field.getType().equals(Short.class))
            return new SingleReferenceShortTextParser();

        if(field.getType().equals(String.class))
            return new SingleReferenceStringTextParser();

        if(field.getType().equals(WebElement.class))
            return new SingleWebElementParser();

        if(field.getType().equals(WebDriver.class))
            return new SingleWebDriverParser();

        return null;
    }


    public static IProperty getProperty(Field field) throws ScrapperException {
        boolean hasGetter = MethodProperty.getGetter(field.getDeclaringClass(), field) != null;
        boolean hasSetter = MethodProperty.getSetter(field.getDeclaringClass(), field) != null;

        if(!hasGetter && !hasSetter)
            return new FieldProperty(field);
        else if(!hasGetter && hasSetter)
            return new ComposedProperty(new FieldProperty(field), new MethodProperty(field));
        else if(hasGetter && !hasSetter)
            return new ComposedProperty(new MethodProperty(field), new FieldProperty(field));

        return new MethodProperty(field);
    }

}
