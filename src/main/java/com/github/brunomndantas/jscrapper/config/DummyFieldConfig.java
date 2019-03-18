package com.github.brunomndantas.jscrapper.config;

import com.github.brunomndantas.jscrapper.core.config.FieldConfig;
import com.github.brunomndantas.jscrapper.core.property.IProperty;
import com.github.brunomndantas.jscrapper.core.property.PropertyException;

import java.lang.reflect.Field;

public class DummyFieldConfig extends FieldConfig {

    public static class DummyProperty implements IProperty {

        @Override
        public Object get(Object instance) throws PropertyException {
            return null;
        }

        @Override
        public void set(Object instance, Object value) throws PropertyException {

        }

    }



    public DummyFieldConfig(Field field) {
        super(field);

        super.setDriverLoader((driver) -> {});
        super.setSelector((driver) -> null);
        super.setElementLoader((driver, elements) -> {});
        super.setParser((driver, elements) -> null);
        super.setProperty(new DummyProperty());
    }

}
