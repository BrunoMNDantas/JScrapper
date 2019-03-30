package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.core.CoreScrapper;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.scrapper.config.ConfigBuilder;

public class Scrapper {

    public <T> T scrap(Class<T> klass) throws Exception {
        return scrap(klass, ConfigBuilder.createConfig(klass));
    }

    public <T> T scrap(Class<T> klass, ClassConfig userConfig) throws ScrapperException {
        ClassConfig config = createConfig(userConfig);
        return (T) new CoreScrapper().scrap(config);
    }

    private ClassConfig createConfig(ClassConfig userConfig) throws ScrapperException {
        ClassConfig config = ConfigBuilder.createConfig(userConfig.getKlass());

        ConfigBuilder.mergeConfigs(config, userConfig);

        ConfigBuilder.buildConfig(config);

        return config;
    }

}
