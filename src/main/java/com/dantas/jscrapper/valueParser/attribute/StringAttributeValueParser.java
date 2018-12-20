package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class StringAttributeValueParser extends AttributeValueParser<String> {

    public StringAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public StringAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected String parseAttribute(String value) throws ValueParserException {
        return value;
    }

}
