package com.dantas.jscrapper.valueParser.attribute;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class DoubleAttributeValueParser extends AttributeValueParser<Double> {

    public DoubleAttributeValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public DoubleAttributeValueParser(By selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Double parseAttribute(String value) throws ValueParserException {
        return Double.parseDouble(value);
    }

}
