package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.Utils;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.driverLoader.IDriverLoader;
import com.github.brunomndantas.jscrapper.core.elementLoader.IElementLoader;
import com.github.brunomndantas.jscrapper.core.parser.IParser;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.ElementLoader;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Parser;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Property;
import com.github.brunomndantas.jscrapper.scrapper.annotation.element.Selector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.page.DriverLoader;
import com.github.brunomndantas.jscrapper.support.elementLoader.*;
import com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive.*;
import com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference.*;
import com.github.brunomndantas.jscrapper.support.selector.*;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

public class AnnotationFieldConfig {

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd";



    public static FieldConfig getFieldConfig(Field field) throws ScrapperException {
        try {
            FieldConfig config = new FieldConfig(field);

            config.setDriverLoader(getDriverLoader(field));
            config.setSelector(getSelector(field));
            config.setElementLoader(getElementLoader(field));
            config.setParser(getParser(field));
            config.setProperty(getProperty(field));

            return config;
        } catch(ScrapperException e) {
            throw new ScrapperException("Error getting config for field:" + field.getName() + "!",e);
        }
    }


    public static IDriverLoader getDriverLoader(Field field) throws ScrapperException {
        DriverLoader annotation = field.getDeclaredAnnotation(DriverLoader.class);
        return getDriverLoader(field, annotation);
    }

    private static IDriverLoader getDriverLoader(Field field, DriverLoader annotation) throws ScrapperException {
        return AnnotationClassConfig.getDriverLoader(field.getDeclaringClass(), annotation);
    }


    public static ISelector getSelector(Field field) throws ScrapperException {
        Selector annotation = field.getDeclaredAnnotation(Selector.class);
        return getSelector(field, annotation);
    }

    private static ISelector getSelector(Field field, Selector annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != ISelector.class)
                return Utils.createInstance(annotation.value());

            switch (annotation.selectorType()) {
                case ID: return new IdSelector(annotation.selector());
                case NAME: return new NameSelector(annotation.selector());
                case CLASS: return new ClassNameSelector(annotation.selector());
                case TAG: return new TagNameSelector(annotation.selector());
                case LINK_TEXT: return new LinkTextSelector(annotation.selector());
                case PARTIAL_LINK_TEXT: return new PartialLinkTextSelector(annotation.selector());
                case CSS: return new CSSSelector(annotation.selector());
                case XPATH: return new XPathSelector(annotation.selector());
                default: throw new ScrapperException("Unknown SelectorType:" + annotation.selectorType() + "!");
            }
        }

        return null;
    }


    public static IElementLoader getElementLoader(Field field) throws ScrapperException {
        ElementLoader annotation = field.getDeclaredAnnotation(ElementLoader.class);
        return getElementLoader(field, annotation);
    }

    private static IElementLoader getElementLoader(Field field, ElementLoader annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != IElementLoader.class)
                return Utils.createInstance(annotation.value());

            return getElementLoader(field, annotation.actions());
        }

        return null;
    }

    private static IElementLoader getElementLoader(Field field, ElementLoader.Action[] actions) throws ScrapperException {
        Collection<IElementLoader> loaders = new LinkedList<>();

        for(ElementLoader.Action action : actions)
            loaders.add(getElementLoader(field, action));

        return new ComposedElementLoader(loaders);
    }

    private static IElementLoader getElementLoader(Field field, ElementLoader.Action annotation) throws ScrapperException {
        if(annotation.clear().isUserDefined())
            return getElementLoader(field, annotation.clear());

        if(annotation.click().isUserDefined())
            return getElementLoader(field, annotation.click());

        if(annotation.doubleClick().isUserDefined())
            return getElementLoader(field, annotation.doubleClick());

        if(annotation.sendKeys().isUserDefined())
            return getElementLoader(field, annotation.sendKeys());

        if(annotation.submit().isUserDefined())
            return getElementLoader(field, annotation.submit());

        if(annotation.waitFor().isUserDefined())
            return getElementLoader(field, annotation.waitFor());

        if(annotation.waitVisible().isUserDefined())
            return getElementLoader(field, annotation.waitVisible());

        throw new ScrapperException("Unknown ElementLoader Action!");
    }

    private static ClearElementLoader getElementLoader(Field field, ElementLoader.Clear annotation) {
        return new ClearElementLoader();
    }

    private static ClickElementLoader getElementLoader(Field field, ElementLoader.Click annotation) {
        return new ClickElementLoader();
    }

    private static DoubleClickElementLoader getElementLoader(Field field, ElementLoader.DoubleClick annotation) {
        return new DoubleClickElementLoader();
    }

    private static SendKeysElementLoader getElementLoader(Field field, ElementLoader.SendKeys annotation) {
        return new SendKeysElementLoader(annotation.value());
    }

    private static SubmitElementLoader getElementLoader(Field field, ElementLoader.Submit annotation) {
        return new SubmitElementLoader();
    }

    private static WaitElementLoader getElementLoader(Field field, ElementLoader.Wait annotation) {
        return new WaitElementLoader(annotation.unit(), annotation.value());
    }

    private static WaitVisibleElementLoader getElementLoader(Field field, ElementLoader.WaitVisible annotation) {
        return new WaitVisibleElementLoader(annotation.unit(), annotation.value());
    }


    public static IParser getParser(Field field) throws ScrapperException {
        Parser annotation = field.getDeclaredAnnotation(Parser.class);
        return getParser(field, annotation);
    }

    private static IParser getParser(Field field, Parser annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != IParser.class)
                return Utils.createInstance(annotation.value());

            if(!annotation.attribute().isEmpty()) {
                if(field.getType().isArray())
                    return getArrayParser(field, annotation);

                if(field.getType().equals(Collection.class))
                    return getCollectionParser(field, annotation);

                return getSingleParser(field, annotation);
            }
        }

        return null;
    }

    private static IParser getArrayParser(Field field, Parser annotation) throws ScrapperException {
        if(field.getType().getComponentType().equals(boolean.class))
            return new ArrayPrimitiveBooleanAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(byte.class))
            return new ArrayPrimitiveByteAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(char.class))
            return new ArrayPrimitiveCharacterAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(double.class))
            return new ArrayPrimitiveDoubleAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(float.class))
            return new ArrayPrimitiveFloatAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(int.class))
            return new ArrayPrimitiveIntegerAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(long.class))
            return new ArrayPrimitiveLongAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(short.class))
            return new ArrayPrimitiveShortAttributeParser(annotation.attribute());


        if(field.getType().getComponentType().equals(Boolean.class))
            return new ArrayReferenceBooleanAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Byte.class))
            return new ArrayReferenceByteAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Character.class))
            return new ArrayReferenceCharacterAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Date.class))
            return new ArrayReferenceDateAttributeParser(annotation.attribute(), annotation.dateFormat().isEmpty() ? DEFAULT_DATE_FORMAT : annotation.dateFormat());

        if(field.getType().getComponentType().equals(Double.class))
            return new ArrayReferenceDoubleAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Float.class))
            return new ArrayReferenceFloatAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Integer.class))
            return new ArrayReferenceIntegerAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Long.class))
            return new ArrayReferenceLongAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(Short.class))
            return new ArrayReferenceShortAttributeParser(annotation.attribute());

        if(field.getType().getComponentType().equals(String.class))
            return new ArrayReferenceStringAttributeParser(annotation.attribute());

        return null;
    }

    private static IParser getCollectionParser(Field field, Parser annotation) throws ScrapperException {
        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Boolean.class))
            return new CollectionReferenceBooleanAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Byte.class))
            return new CollectionReferenceByteAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Character.class))
            return new CollectionReferenceCharacterAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Date.class))
            return new CollectionReferenceDateAttributeParser(annotation.attribute(), annotation.dateFormat().isEmpty() ? DEFAULT_DATE_FORMAT : annotation.dateFormat());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Double.class))
            return new CollectionReferenceDoubleAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Float.class))
            return new CollectionReferenceFloatAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Integer.class))
            return new CollectionReferenceIntegerAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Long.class))
            return new CollectionReferenceLongAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(Short.class))
            return new CollectionReferenceShortAttributeParser(annotation.attribute());

        if(((ParameterizedType)(field.getGenericType())).getActualTypeArguments()[0].equals(String.class))
            return new CollectionReferenceStringAttributeParser(annotation.attribute());

        return null;
    }

    private static IParser getSingleParser(Field field, Parser annotation) throws ScrapperException {
        if(field.getType().equals(boolean.class))
            return new SinglePrimitiveBooleanAttributeParser(annotation.attribute());

        if(field.getType().equals(byte.class))
            return new SinglePrimitiveByteAttributeParser(annotation.attribute());

        if(field.getType().equals(char.class))
            return new SinglePrimitiveCharacterAttributeParser(annotation.attribute());

        if(field.getType().equals(double.class))
            return new SinglePrimitiveDoubleAttributeParser(annotation.attribute());

        if(field.getType().equals(float.class))
            return new SinglePrimitiveFloatAttributeParser(annotation.attribute());

        if(field.getType().equals(int.class))
            return new SinglePrimitiveIntegerAttributeParser(annotation.attribute());

        if(field.getType().equals(long.class))
            return new SinglePrimitiveLongAttributeParser(annotation.attribute());

        if(field.getType().equals(short.class))
            return new SinglePrimitiveShortAttributeParser(annotation.attribute());


        if(field.getType().equals(Boolean.class))
            return new SingleReferenceBooleanAttributeParser(annotation.attribute());

        if(field.getType().equals(Byte.class))
            return new SingleReferenceByteAttributeParser(annotation.attribute());

        if(field.getType().equals(Character.class))
            return new SingleReferenceCharacterAttributeParser(annotation.attribute());

        if(field.getType().equals(Date.class))
            return new SingleReferenceDateAttributeParser(annotation.attribute(), annotation.dateFormat().isEmpty() ? DEFAULT_DATE_FORMAT : annotation.dateFormat());

        if(field.getType().equals(Double.class))
            return new SingleReferenceDoubleAttributeParser(annotation.attribute());

        if(field.getType().equals(Float.class))
            return new SingleReferenceFloatAttributeParser(annotation.attribute());

        if(field.getType().equals(Integer.class))
            return new SingleReferenceIntegerAttributeParser(annotation.attribute());

        if(field.getType().equals(Long.class))
            return new SingleReferenceLongAttributeParser(annotation.attribute());

        if(field.getType().equals(Short.class))
            return new SingleReferenceShortAttributeParser(annotation.attribute());

        if(field.getType().equals(String.class))
            return new SingleReferenceStringAttributeParser(annotation.attribute());

        return null;
    }


    public static IProperty getProperty(Field field) throws ScrapperException {
        Property annotation = field.getDeclaredAnnotation(Property.class);
        return getProperty(field, annotation);
    }

    private static IProperty getProperty(Field field, Property annotation) throws ScrapperException {
        if(annotation != null) {
            if(annotation.value() != IProperty.class)
                return Utils.createInstance(annotation.value());
        }

        return null;
    }

}
