package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import com.github.brunomndantas.jscrapper.property.annotation.Property;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

public class PropertyFactoryTest {

    public static class PropertyWithoutEmptyConstructor implements IProperty {

        public PropertyWithoutEmptyConstructor(String str) { }



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

    public static class EntityWithNoEmptyConstructorPropertyAnnotation {

        @Property(PropertyWithoutEmptyConstructor.class)
        public String f;

    }

    public static class PersonFieldGetFieldSet {

        private String name;

    }

    public static class PersonFieldGetMethodSet {

        private String name;
        private void setName(String name) { this.name = name; }

    }

    public static class PersonMethodGetFieldSet {

        private String name;
        private String getName() { return this.name; }

    }

    public static class PersonMethodGetMethodSet {

        private String name;
        private String getName() { return this.name; }
        private void setName(String name) { this.name = name; }

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
    public void createEntityWithNoEmptyConstructorPropertyAnnotationTest() throws Exception {
        Class<?> klass = EntityWithNoEmptyConstructorPropertyAnnotation.class;
        Field field = klass.getDeclaredField("f");

        try {
            PropertyFactory.create(klass, field);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

    @Test
    public void createPropertyTest() throws Exception {
        Field field;
        IProperty property;

        field = PersonFieldGetFieldSet.class.getDeclaredField("name");
        property = PropertyFactory.create(PersonFieldGetFieldSet.class, field);
        assertTrue(property instanceof FieldProperty);
        assertSame(field, ((FieldProperty)property).getField());

        field = PersonFieldGetMethodSet.class.getDeclaredField("name");
        property = PropertyFactory.create(PersonFieldGetMethodSet.class, field);
        assertTrue(property instanceof ComposedProperty);
        assertTrue(((ComposedProperty)property).getGetter() instanceof  FieldProperty);
        assertSame(field, ((FieldProperty)((ComposedProperty)property).getGetter()).getField());
        assertTrue(((ComposedProperty)property).getSetter() instanceof MethodProperty);
        assertSame(field, ((MethodProperty)((ComposedProperty)property).getSetter()).getField());

        field = PersonMethodGetFieldSet.class.getDeclaredField("name");
        property = PropertyFactory.create(PersonMethodGetFieldSet.class, field);
        assertTrue(property instanceof ComposedProperty);
        assertTrue(((ComposedProperty)property).getGetter() instanceof  MethodProperty);
        assertSame(field, ((MethodProperty)((ComposedProperty)property).getGetter()).getField());
        assertTrue(((ComposedProperty)property).getSetter() instanceof  FieldProperty);
        assertSame(field, ((FieldProperty)((ComposedProperty)property).getSetter()).getField());

        field = PersonMethodGetMethodSet.class.getDeclaredField("name");
        property = PropertyFactory.create(PersonMethodGetMethodSet.class, field);
        assertTrue(property instanceof MethodProperty);
        assertSame(field, ((MethodProperty)property).getField());
    }

}
