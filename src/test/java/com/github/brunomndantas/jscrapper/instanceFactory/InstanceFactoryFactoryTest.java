package com.github.brunomndantas.jscrapper.instanceFactory;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.instanceFactory.IInstanceFactory;
import com.github.brunomndantas.jscrapper.core.instanceFactory.InstanceFactoryException;
import com.github.brunomndantas.jscrapper.instanceFactory.annotation.InstanceFactory;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class InstanceFactoryFactoryTest {

    public static class FactoryWithoutEmptyConstructor implements IInstanceFactory {

        @Override
        public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }

    }

    public static class FactoryWithEmptyConstructor implements IInstanceFactory {

        public FactoryWithEmptyConstructor() { }



        @Override
        public <T> T create(Class<T> klass) throws InstanceFactoryException { return null; }

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
    public void createEntityWithoutInstanceFactoryAnnotationTest() {
        Class<?> klass = EntityWithoutInstanceFactoryAnnotation.class;

        try {
            InstanceFactoryFactory.create(klass);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorInstanceFactoryAnnotationTest() {
        Class<?> klass = EntityWithNoEmptyConstructorInstanceFactoryAnnotation.class;

        try {
            InstanceFactoryFactory.create(klass);
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
