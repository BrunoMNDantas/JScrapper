package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class FloatAttributesValueParser extends AttributesValueParser<Float> {

    public FloatAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public FloatAttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Float parseAttribute(String value) throws ValueParserException {
        return Float.parseFloat(value);
    }

}
