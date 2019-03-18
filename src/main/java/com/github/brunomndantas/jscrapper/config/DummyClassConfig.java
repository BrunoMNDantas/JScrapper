package com.github.brunomndantas.jscrapper.config;

import com.github.brunomndantas.jscrapper.core.config.ClassConfig;

public class DummyClassConfig extends ClassConfig {

    public DummyClassConfig(Class<?> klass) {
        super(klass);

        super.setInstanceFactory(() -> null);
        super.setDriverSupplier(() -> null);
        super.setDriverLoader((driver) -> {});
    }


}
