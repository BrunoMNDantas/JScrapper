package com.github.brunomndantas.jscrapper.scrapper.config;

import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.config.FieldConfig;

import java.lang.reflect.Field;

public class ConfigBuilder {

    public static void mergeConfig(ClassConfig config, ClassConfig userConfig) {
        if(userConfig.getInstanceFactory() != null)
            config.setInstanceFactory(userConfig.getInstanceFactory());

        if(userConfig.getDriverSupplier() != null)
            config.setDriverSupplier(userConfig.getDriverSupplier());

        if(userConfig.getURLSupplier() != null)
            config.setURLSupplier(userConfig.getURLSupplier());

        if(userConfig.getDriverLoader() != null)
            config.setDriverLoader(userConfig.getDriverLoader());

        FieldConfig userFieldConfig;
        for(FieldConfig fieldConfig : config.getFieldsConfig()) {
            userFieldConfig = getConfigForField(userConfig, fieldConfig.getField());
            if(userFieldConfig != null)
                mergeConfig(fieldConfig, userFieldConfig);
        }
    }

    private static FieldConfig getConfigForField(ClassConfig config, Field field) {
        for(FieldConfig fieldConfig : config.getFieldsConfig())
            if(field.equals(fieldConfig.getField()))
                return fieldConfig;

            return null;
    }

    private static void mergeConfig(FieldConfig config, FieldConfig userConfig) {
        if(userConfig.getDriverLoader() != null)
            config.setDriverLoader(userConfig.getDriverLoader());

        if(userConfig.getSelector() != null)
            config.setSelector(userConfig.getSelector());

        if(userConfig.getElementLoader() != null)
            config.setElementLoader(userConfig.getElementLoader());

        if(userConfig.getParser() != null)
            config.setParser(userConfig.getParser());

        if(userConfig.getProperty() != null)
            config.setProperty(userConfig.getProperty());
    }


    public static ClassConfig createConfig(Class<?> klass) throws ScrapperException {
        ClassConfig config = new ClassConfig(klass);

        for(Field field : klass.getDeclaredFields())
            config.getFieldsConfig().add(new FieldConfig(field));

        return config;
    }


    public static void buildConfig(ClassConfig config) throws ScrapperException {
        buildInstanceFactory(config);
        buildURLSupplier(config);
        buildDriverSupplier(config);
        buildDriverLoader(config);

        for(FieldConfig fieldConfig : config.getFieldsConfig())
            buildConfig(fieldConfig);
    }

    private static void buildConfig(FieldConfig config) throws ScrapperException {
        buildDriverLoader(config);
        buildSelector(config);
        buildElementLoader(config);
        buildParser(config);
        buildProperty(config);
    }


    private static void buildInstanceFactory(ClassConfig config) throws ScrapperException {
        if(config.getInstanceFactory() == null) {
            config.setInstanceFactory(AnnotationClassConfig.getInstanceFactory(config.getKlass()));

            if(config.getInstanceFactory() == null)
                config.setInstanceFactory(DefaultClassConfig.getInstanceFactory(config.getKlass()));
        }
    }

    private static void buildURLSupplier(ClassConfig config) throws ScrapperException {
        if(config.getURLSupplier() == null) {
            config.setURLSupplier(AnnotationClassConfig.getURLSupplier(config.getKlass()));

            if(config.getURLSupplier() == null)
                config.setURLSupplier(DefaultClassConfig.getURLSupplier(config.getKlass()));
        }
    }

    private static void buildDriverSupplier(ClassConfig config) throws ScrapperException {
        if(config.getDriverSupplier() == null) {
            config.setDriverSupplier(AnnotationClassConfig.getDriverSupplier(config.getKlass()));

            if(config.getDriverSupplier() == null)
                config.setDriverSupplier(DefaultClassConfig.getDriverSupplier(config.getKlass()));
        }
    }

    private static void buildDriverLoader(ClassConfig config) throws ScrapperException {
        if(config.getDriverLoader() == null) {
            config.setDriverLoader(AnnotationClassConfig.getDriverLoader(config.getKlass()));

            if(config.getDriverLoader() == null)
                config.setDriverLoader(DefaultClassConfig.getDriverLoader(config.getKlass()));
        }
    }


    private static void buildDriverLoader(FieldConfig config) throws ScrapperException {
        if(config.getDriverLoader() == null) {
            config.setDriverLoader(AnnotationFieldConfig.getDriverLoader(config.getField()));

            if(config.getDriverLoader() == null)
                config.setDriverLoader(DefaultFieldConfig.getDriverLoader(config.getField()));
        }
    }

    private static void buildSelector(FieldConfig config) throws ScrapperException {
        if(config.getSelector() == null) {
            config.setSelector(AnnotationFieldConfig.getSelector(config.getField()));

            if(config.getSelector() == null)
                config.setSelector(DefaultFieldConfig.getSelector(config.getField()));
        }
    }

    private static void buildElementLoader(FieldConfig config) throws ScrapperException {
        if(config.getElementLoader() == null) {
            config.setElementLoader(AnnotationFieldConfig.getElementLoader(config.getField()));

            if(config.getElementLoader() == null)
                config.setElementLoader(DefaultFieldConfig.getElementLoader(config.getField()));
        }
    }

    private static void buildParser(FieldConfig config) throws ScrapperException {
        if(config.getParser() == null) {
            config.setParser(AnnotationFieldConfig.getParser(config.getField()));

            if(config.getParser() == null)
                config.setParser(DefaultFieldConfig.getParser(config.getField()));
        }
    }

    private static void buildProperty(FieldConfig config) throws ScrapperException {
        if(config.getProperty() == null) {
            config.setProperty(AnnotationFieldConfig.getProperty(config.getField()));

            if(config.getProperty() == null)
                config.setProperty(DefaultFieldConfig.getProperty(config.getField()));
        }
    }

}
