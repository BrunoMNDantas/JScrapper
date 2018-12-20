package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class FloatAttributeValueParser extends AttributeValueParser<Float> {

    public FloatAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public FloatAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Float parseAttribute(String value) throws ValueParserException {
        return Float.parseFloat(value);
    }

}
