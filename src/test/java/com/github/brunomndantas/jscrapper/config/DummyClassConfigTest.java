package com.github.brunomndantas.jscrapper.config;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;

public class DummyClassConfigTest extends ClassConfig {

    private static class Person {
        public String name;
    }


    @Test
    public void constructorTest() {
        Class klass = Person.class;
        DummyClassConfig config = new DummyClassConfig(klass);

        assertSame(klass, config.getKlass());
        assertNotNull(config.getInstanceFactory());
        assertNotNull(config.getDriverSupplier());
        assertNotNull(config.getDriverLoader());
    }

}
