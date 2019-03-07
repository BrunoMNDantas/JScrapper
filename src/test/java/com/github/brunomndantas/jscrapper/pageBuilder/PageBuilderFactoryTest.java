package com.github.brunomndantas.jscrapper.pageBuilder;

import com.github.brunomndantas.jscrapper.core.Page;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.pageBuilder.IPageBuilder;
import com.github.brunomndantas.jscrapper.core.pageBuilder.PageBuilderException;
import com.github.brunomndantas.jscrapper.pageBuilder.annotation.PageBuilder;
import org.junit.Test;

import static org.junit.Assert.*;

public class PageBuilderFactoryTest {

    public static class BuilderWithoutEmptyConstructor implements IPageBuilder {

        public BuilderWithoutEmptyConstructor(String str) { }



        @Override
        public Page build(Class<?> klass) throws PageBuilderException { return null; }

    }

    public static class BuilderWithEmptyConstructor implements IPageBuilder {

        public BuilderWithEmptyConstructor() { }



        @Override
        public Page build(Class<?> klass) throws PageBuilderException { return null; }

    }


    @PageBuilder(BuilderWithEmptyConstructor.class)
    public static class EntityWithPageBuilderAnnotation { }

    public static class EntityWithoutPageBuilderAnnotation { }

    @PageBuilder(BuilderWithoutEmptyConstructor.class)
    public static class EntityWithNoEmptyConstructorPageBuilderAnnotation { }



    @Test
    public void createEntityWithPageBuilderAnnotationTest() throws ScrapperException {
        Class<?> klass = EntityWithPageBuilderAnnotation.class;
        IPageBuilder builder = PageBuilderFactory.create(klass);

        assertNotNull(builder);
        assertTrue(builder instanceof BuilderWithEmptyConstructor);
    }

    @Test
    public void createEntityWithoutPageBuilderAnnotationTest() {
        Class<?> klass = EntityWithoutPageBuilderAnnotation.class;

        try {
            PageBuilderFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("found"));
        }
    }

    @Test
    public void createEntityWithNoEmptyConstructorPageBuilderAnnotationTest() {
        Class<?> klass = EntityWithNoEmptyConstructorPageBuilderAnnotation.class;

        try {
            PageBuilderFactory.create(klass);
            fail("Exception should be thrown!");
        } catch (ScrapperException e) {
            assertTrue(e.getMessage().contains("empty constructor"));
        }
    }

}
