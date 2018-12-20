package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class LongAttributeValueParser extends AttributeValueParser<Long> {

    public LongAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public LongAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Long parseAttribute(String value) throws ValueParserException {
        return Long.parseLong(value);
    }

}
