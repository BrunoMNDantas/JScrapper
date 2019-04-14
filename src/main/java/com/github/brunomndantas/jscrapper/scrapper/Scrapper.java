package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.core.CoreScrapper;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.core.driverSupplier.IDriverSupplier;
import com.github.brunomndantas.jscrapper.scrapper.config.ConfigBuilder;
import com.github.brunomndantas.jscrapper.support.urlSupplier.ParameterizedURLSupplier;

import java.util.Collection;
import java.util.LinkedList;
import java.util.Map;

public class Scrapper {

    private IDriverSupplier defaultDriverSupplier;
    public IDriverSupplier getDefaultDriverSupplier() { return this.defaultDriverSupplier; }



    public Scrapper(IDriverSupplier defaultDriverSupplier) {
        this.defaultDriverSupplier = defaultDriverSupplier;
    }

    public Scrapper() {
        this(null);
    }



    public <T> T scrap(Class<T> klass) throws ScrapperException {
        return scrap(klass, (ClassConfig) null);
    }

    public <T> T scrap(Class<T> klass, ClassConfig userConfig) throws ScrapperException {
        ClassConfig config = createConfig(klass, userConfig);
        return (T) new CoreScrapper().scrap(config);
    }

    public <T> T scrap(Class<T> klass, Map<String, String> urlParameters) throws ScrapperException {
        return scrap(klass, urlParameters, null);
    }

    public <T> T scrap(Class<T> klass, Map<String, String> urlParameters, ClassConfig userConfig) throws ScrapperException {
        ClassConfig config = createConfig(klass, userConfig);

        config.setURLSupplier(new ParameterizedURLSupplier(config.getURLSupplier(), urlParameters));

        return (T) new CoreScrapper().scrap(config);
    }

    public <T> Collection<T> scrap(Class<T> klass, Collection<Map<String, String>> urlParameters) throws ScrapperException {
        return scrap(klass, urlParameters, null);
    }

    public <T> Collection<T> scrap(Class<T> klass, Collection<Map<String, String>> urlParameters, ClassConfig userConfig) throws ScrapperException {
        Collection<T> elements = new LinkedList<>();

        for(Map<String, String> parameters : urlParameters)
            elements.add(scrap(klass, parameters, userConfig));

        return elements;
    }

    private ClassConfig createConfig(Class<?> klass, ClassConfig userConfig) throws ScrapperException {
        ClassConfig config = ConfigBuilder.createConfig(klass);

        ConfigBuilder.buildConfig(config);

        if(userConfig != null)
            ConfigBuilder.mergeConfig(config, userConfig);

        if(config.getDriverSupplier() == null)
            config.setDriverSupplier(this.defaultDriverSupplier);

        return config;
    }

}
