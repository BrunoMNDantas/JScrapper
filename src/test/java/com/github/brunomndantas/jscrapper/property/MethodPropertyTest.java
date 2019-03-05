package com.github.brunomndantas.jscrapper.property;

import com.github.brunomndantas.jscrapper.core.property.PropertyException;
import org.junit.Test;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import static org.junit.Assert.*;

public class MethodPropertyTest {

    public static class PersonPublic {

        public boolean getterUsed;
        public boolean setterUsed;

        public String name;
        public String getName() { getterUsed = true; return this.name; }
        public void setName(String name) { setterUsed = true; this.name = name; }



        public PersonPublic(String name) {
            this.name = name;
        }

    }

    public static class PersonPrivate {

        public boolean getterUsed;
        public boolean setterUsed;

        private String name;
        private String getName() { getterUsed = true; return this.name; }
        private void setName(String name) { setterUsed = true; this.name = name; }



        public PersonPrivate(String name) {
            this.name = name;
        }

    }

    public static class PersonProtected {

        public boolean getterUsed;
        public boolean setterUsed;

        protected String name;
        protected String getName() { getterUsed = true; return this.name; }
        protected void setName(String name) { setterUsed = true; this.name = name; }



        public PersonProtected(String name) {
            this.name = name;
        }

    }

    public static class PersonBoolean {

        public String name;
        public String isName() { return this.isName(); }

    }

    public static class PersonWrongName {

        private String name;
        public String getNam() { return this.name; }
        public void setNam(String name) { this.name = name; }

    }

    public static class PersonWrongReturn {

        private String name;
        public int getName() { return 0; }
        public String setName(String name) { this.name = name; return this.name; }

    }

    public static class PersonWrongParameter {

        private String name;
        public String getName(String s) { return this.name; }
        public void setName(int a) { this.name = name; }
        public void setName() { this.name = name; }

    }

    public static class PersonAssignable {

        private List<String> col;
        public Collection<String> getCol() { return this.col; }
        public void setCol(LinkedList<String> col) { this.col = col; }

    }

    public static class PersonNotAssignable {

        private List<String> col;
        public LinkedList<String> getCol() { return null; }
        public void setCol(Collection<String> col) { }

    }



    @Test
    public void getGetterTest() throws Exception {
        PersonProtected person = new PersonProtected("");
        Field field = person.getClass().getDeclaredField("name");

        Method expected = person.getClass().getDeclaredMethod("getName");
        Method actual = MethodProperty.getGetter(person.getClass(), field);

        assertEquals(expected, actual);
    }

    @Test
    public void isGetterTest() throws Exception {
        PersonProtected person = new PersonProtected("");
        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("getName");

        assertTrue(MethodProperty.isGetter(method, field));

        method = PersonBoolean.class.getDeclaredMethod("isName");

        assertTrue(MethodProperty.isGetter(method, field));
    }

    @Test
    public void isGetterWrongNameTest() throws Exception {
        PersonWrongName person = new PersonWrongName();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("getNam");

        assertFalse(MethodProperty.isGetter(method, field));
    }

    @Test
    public void isGetterParametersTest() throws Exception {
        PersonWrongParameter person = new PersonWrongParameter();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("getName", String.class);

        assertFalse(MethodProperty.isGetter(method, field));
    }

    @Test
    public void isGetterReturnTest() throws Exception {
        PersonWrongReturn person = new PersonWrongReturn();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("getName");

        assertFalse(MethodProperty.isGetter(method, field));
    }

    @Test
    public void isGetterAssignableTest() throws Exception {
        PersonAssignable person = new PersonAssignable();

        Field field = person.getClass().getDeclaredField("col");
        Method method = person.getClass().getDeclaredMethod("getCol");

        assertTrue(MethodProperty.isGetter(method, field));
    }

    @Test
    public void isGetterNotAssignableTest() throws Exception {
        PersonNotAssignable person = new PersonNotAssignable();

        Field field = person.getClass().getDeclaredField("col");
        Method method = person.getClass().getDeclaredMethod("getCol");

        assertFalse(MethodProperty.isGetter(method, field));
    }

    @Test
    public void getSetterTest() throws Exception {
        PersonProtected person = new PersonProtected("");
        Field field = person.getClass().getDeclaredField("name");

        Method expected = person.getClass().getDeclaredMethod("setName", String.class);
        Method actual = MethodProperty.getSetter(person.getClass(), field);

        assertEquals(expected, actual);
    }

    @Test
    public void isSetterTest() throws Exception {
        PersonPrivate person = new PersonPrivate("");
        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("setName", String.class);

        assertTrue(MethodProperty.isSetter(method, field));
    }

    @Test
    public void isSetterWrongNameTest() throws Exception {
        PersonWrongName person = new PersonWrongName();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("setNam", String.class);

        assertFalse(MethodProperty.isSetter(method, field));
    }

    @Test
    public void isSetterParametersTest() throws Exception {
        PersonWrongParameter person = new PersonWrongParameter();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("setName");

        assertFalse(MethodProperty.isSetter(method, field));

        method = person.getClass().getDeclaredMethod("setName", Integer.TYPE);

        assertFalse(MethodProperty.isSetter(method, field));
    }

    @Test
    public void isSetterReturnTest() throws Exception {
        PersonWrongReturn person = new PersonWrongReturn();

        Field field = person.getClass().getDeclaredField("name");
        Method method = person.getClass().getDeclaredMethod("setName", String.class);

        assertFalse(MethodProperty.isSetter(method, field));
    }

    @Test
    public void isSetterAssignableTest() throws Exception {
        PersonAssignable person = new PersonAssignable();

        Field field = person.getClass().getDeclaredField("col");
        Method method = person.getClass().getDeclaredMethod("setCol", LinkedList.class);

        assertTrue(MethodProperty.isSetter(method, field));
    }

    @Test
    public void isSetterNotAssignableTest() throws Exception {
        PersonNotAssignable person = new PersonNotAssignable();

        Field field = person.getClass().getDeclaredField("col");
        Method method = person.getClass().getDeclaredMethod("setCol", Collection.class);

        assertFalse(MethodProperty.isSetter(method, field));
    }


    @Test
    public void constructorTest() throws Exception {
        Object target = new PersonPublic("");
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        assertSame(field, property.getField());
    }

    @Test
    public void noGetterTest() throws Exception {
        Object target = new FieldPropertyTest.PersonPublic("");
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        try {
            property.get(target);
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
            assertTrue(e.getCause().getMessage().contains("getter"));
        }
    }

    @Test
    public void getPublicTest() throws Exception {
        String name = "";
        PersonPublic target = new PersonPublic(name);
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        assertSame(name, property.get(target));
        assertTrue(target.getterUsed);
    }

    @Test
    public void getPrivateTest() throws Exception {
        String name = "";
        PersonPrivate target = new PersonPrivate(name);
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        assertSame(name, property.get(target));
        assertTrue(target.getterUsed);
    }

    @Test
    public void getProtectedTest() throws Exception {
        String name = "";
        PersonProtected target = new PersonProtected(name);
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        assertSame(name, property.get(target));
        assertTrue(target.getterUsed);
    }

    @Test
    public void noSetterTest() throws Exception {
        Object target = new FieldPropertyTest.PersonPublic("");
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        try {
            property.set(target, "");
            fail("Exception should be thrown!");
        } catch (PropertyException e) {
            assertTrue(e.getCause().getMessage().contains("setter"));
        }
    }

    @Test
    public void setPublicTest() throws Exception {
        PersonPublic target = new PersonPublic("");

        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
        assertTrue(target.setterUsed);
    }

    @Test
    public void setPrivateTest() throws Exception {
        PersonPrivate target = new PersonPrivate("");
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
        assertTrue(target.setterUsed);
    }

    @Test
    public void setProtectedTest() throws Exception {
        PersonProtected target = new PersonProtected("");
        Field field = target.getClass().getDeclaredField("name");

        MethodProperty property = new MethodProperty(field);

        String name = "";
        property.set(target, name);
        assertSame(name, property.get(target));
        assertTrue(target.setterUsed);
    }

}
