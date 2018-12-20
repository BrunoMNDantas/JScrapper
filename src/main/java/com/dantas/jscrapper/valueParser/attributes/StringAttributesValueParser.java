package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class StringAttributesValueParser extends AttributesValueParser<String> {

    public StringAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public StringAttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected String parseAttribute(String value) throws ValueParserException {
        return value;
    }

}
