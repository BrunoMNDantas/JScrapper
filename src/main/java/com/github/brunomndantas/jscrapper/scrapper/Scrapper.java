package com.github.brunomndantas.jscrapper.scrapper;

import com.github.brunomndantas.jscrapper.core.CoreScrapper;
import com.github.brunomndantas.jscrapper.core.ScrapperException;
import com.github.brunomndantas.jscrapper.core.config.ClassConfig;
import com.github.brunomndantas.jscrapper.scrapper.config.ConfigBuilder;

public class Scrapper {

    public <T> T scrap(Class<T> klass) throws Exception {
        ClassConfig config = createConfig(klass);
        return (T) new CoreScrapper().scrap(config);
    }

    private ClassConfig createConfig(Class<?> klass) throws ScrapperException {
        ClassConfig config = ConfigBuilder.createConfig(klass);

        ConfigBuilder.buildConfig(config);

        return config;
    }

}
