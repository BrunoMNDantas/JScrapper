package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.property.annotation.Property;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class PropertyFactoryTest {

    public static class PropertyWithoutEmptyConstructor implements IProperty {

        @Override
        public Object get(Object instance) throws PropertyException { return null; }

        @Override
        public void set(Object instance, Object value) throws PropertyException { }

    }

    public static class PropertyWithEmptyConstructor implements IProperty {

        public PropertyWithEmptyConstructor() { }



        @Override
        public Object get(Object instance) throws PropertyException { return null; }

        @Override
        public void set(Object instance, Object value) throws PropertyException { }

    }



    public static class EntityWithPropertyAnnotation {

        @Property(PropertyWithEmptyConstructor.class)
        public String f;

    }

    public static class EntityWithoutPropertyAnnotation {

        public String f;

    }

    public static class EntityWithNoEmptyConstructorPropertyAnnotation {

        @Property(PropertyWithoutEmptyConstructor.class)
        public String f;

    }



    @Test
    public void createEntityWithPropertyAnnotationTest() throws Exception {
        Class<?> klass = EntityWithPropertyAnnotation.class;
        Field field = klass.getDeclaredField("f");
        IProperty property = PropertyFactory.create(klass, field);

        assertNotNull(property);
        assertTrue(property instanceof PropertyWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutPropertyAnnotationTest() throws Exception {
        Class<?> klass = EntityWithoutPropertyAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            PropertyFactory.create(klass, field);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorPropertyAnnotationTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorPropertyAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            PropertyFactory.create(klass, field);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
