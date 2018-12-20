package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class DoubleAttributesValueParser extends AttributesValueParser<Double> {

    public DoubleAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public DoubleAttributesValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Double parseAttribute(String value) throws ValueParserException {
        return Double.parseDouble(value);
    }

}
