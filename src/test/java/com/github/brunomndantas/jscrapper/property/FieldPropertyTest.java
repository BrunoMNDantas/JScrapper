package com.github.brunomndantas.jscrapper.property;

import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.assertSame;

public class FieldPropertyTest {

    public static class PersonPrivate {

        private String name;



        public PersonPrivate(String name) {
            this.name = name;
        }

    }

    public static class PersonProtected {

        protected String name;



        public PersonProtected(String name) {
            this.name = name;
        }

    }

    public static class PersonPublic {

        public String name;



        public PersonPublic(String name) {
            this.name = name;
        }

    }



    @Test
    public void constructorTest() throws Exception {
        Object target = new PersonPublic("");
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        assertSame(field, property.getField());
    }

    @Test
    public void getPublicTest() throws Exception {
        String name = "";
        Object target = new PersonPublic(name);
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        assertSame(name, property.get(target));
    }

    @Test
    public void getPrivateTest() throws Exception {
        String name = "";
        Object target = new PersonPrivate(name);
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        assertSame(name, property.get(target));
    }

    @Test
    public void getProtectedTest() throws Exception {
        String name = "";
        Object target = new PersonProtected(name);
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        assertSame(name, property.get(target));
    }

    @Test
    public void setPublicTest() throws Exception {
        Object target = new PersonPublic("");
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
    }

    @Test
    public void setPrivateTest() throws Exception {
        Object target = new PersonPrivate("");
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
    }

    @Test
    public void setProtectedTest() throws Exception {
        Object target = new PersonProtected("");
        Field field = target.getClass().getDeclaredField("name");

        FieldProperty property = new FieldProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
    }

}
