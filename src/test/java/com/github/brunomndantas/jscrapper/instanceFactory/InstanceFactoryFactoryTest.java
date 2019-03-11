package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.instanceFactory.annotation.InstanceFactory;
import org.junit.Test;

import static org.junit.Assert.*;

public class InstanceFactoryFactoryTest {

    public static class FactoryWithoutEmptyConstructor implements IInstanceFactory {

        public FactoryWithoutEmptyConstructor(String str) { }



        @Override
        public Object create() throws InstanceFactoryException { return null; }

    }

    public static class FactoryWithEmptyConstructor implements IInstanceFactory {

        public FactoryWithEmptyConstructor() { }



        @Override
        public Object create() throws InstanceFactoryException { return null; }

    }


    @InstanceFactory(FactoryWithEmptyConstructor.class)
    public static class EntityWithInstanceFactoryAnnotation { }

    public static class EntityWithoutInstanceFactoryAnnotation { }

    @InstanceFactory(FactoryWithoutEmptyConstructor.class)
    public static class EntityWithNoEmptyConstructorInstanceFactoryAnnotation { }



    @Test
    public void createEntityWithInstanceFactoryAnnotationTest() throws ScrapperException {
        Class<?> klass = EntityWithInstanceFactoryAnnotation.class;
        IInstanceFactory factory = InstanceFactoryFactory.create(klass);

        assertNotNull(factory);
        assertTrue(factory instanceof FactoryWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutInstanceFactoryAnnotationTest() throws Exception {
        Class<?> klass = EntityWithoutInstanceFactoryAnnotation.class;

        IInstanceFactory factory = InstanceFactoryFactory.create(klass);
        assertTrue(factory instanceof EmptyConstructorInstanceFactory);
    }

    @Test
    public void createEntityWithNoEmptyConstructorInstanceFactoryAnnotationTest() {
        Class<?> klass = EntityWithNoEmptyConstructorInstanceFactoryAnnotation.class;

        try {
            InstanceFactoryFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
