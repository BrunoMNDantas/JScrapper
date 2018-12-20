package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class BooleanAttributeValueParser extends AttributeValueParser<Boolean> {

    public BooleanAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public BooleanAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Boolean parseAttribute(String value) throws ValueParserException {
        return Boolean.parseBoolean(value);
    }

}
