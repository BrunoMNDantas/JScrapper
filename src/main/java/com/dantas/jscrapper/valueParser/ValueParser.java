package com.dantas.jscrapper.valueParser;

import org.openqa.selenium.WebDriver;

public abstract class ValueParser<K> implements IValueParser<K> {

    private String name;
    @Override public String getName() { return this.name; }



    public ValueParser(String name) {
        this.name = name;
    }

    public ValueParser() {
        this("");
    }



    @Override
    public K parse(WebDriver driver) throws ValueParserException {
        try {
            return this.internalParse(driver);
        } catch (Exception e) {
            throw new ValueParserException("Error on Parser " + this.name + "!", e);
        }
    }


    protected abstract K internalParse(WebDriver driver) throws ValueParserException;

}
