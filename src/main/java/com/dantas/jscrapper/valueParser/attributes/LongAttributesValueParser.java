package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class LongAttributesValueParser extends AttributesValueParser<Long> {

    public LongAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public LongAttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Long parseAttribute(String value) throws ValueParserException {
        return Long.parseLong(value);
    }

}
