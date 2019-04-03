package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.core.CoreScrapper;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.scrapper.config.ConfigBuilder;
import com.github.brunomndantas.jscrapper.support.urlSupplier.ParameterizedURLSupplier;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class Scrapper {

    public <T> T scrap(Class<T> klass) throws ScrapperException {
        ClassConfig config = createConfig(klass);
        return (T) new CoreScrapper().scrap(config);
    }

    public <T> T scrap(Class<T> klass, Map<String, String> urlParameters) throws ScrapperException {
        ClassConfig config = createConfig(klass);

        config.setURLSupplier(new ParameterizedURLSupplier(config.getURLSupplier(), urlParameters));

        return (T) new CoreScrapper().scrap(config);
    }

    public <T> Collection<T> scrap(Class<T> klass, Collection<Map<String, String>> urlParameters) throws ScrapperException {
        Collection<T> elements = new LinkedList<>();

        for(Map<String, String> parameters : urlParameters)
            elements.add(scrap(klass, parameters));

        return elements;
    }

    private ClassConfig createConfig(Class<?> klass) throws ScrapperException {
        ClassConfig config = ConfigBuilder.createConfig(klass);

        ConfigBuilder.buildConfig(config);

        return config;
    }

}
