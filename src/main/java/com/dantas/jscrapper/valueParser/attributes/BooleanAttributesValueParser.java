package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class BooleanAttributesValueParser extends AttributesValueParser<Boolean> {

    public BooleanAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public BooleanAttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Boolean parseAttribute(String value) throws ValueParserException {
        return Boolean.parseBoolean(value);
    }

}
