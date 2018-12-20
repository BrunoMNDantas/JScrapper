package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class IntAttributeValueParser extends AttributeValueParser<Integer> {

    public IntAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public IntAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Integer parseAttribute(String value) throws ValueParserException {
        return Integer.parseInt(value);
    }

}
