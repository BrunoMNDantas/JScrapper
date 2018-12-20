package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class IntAttributesValueParser extends AttributesValueParser<Integer> {

    public IntAttributesValueParser(By selector, String attribute, String name) {
        super(selector, attribute, name);
    }

    public IntAttributesValueParser(By
                                            selector, String attribute) {
        this(selector, attribute, "");
    }



    @Override
    protected Integer parseAttribute(String value) throws ValueParserException {
        return Integer.parseInt(value);
    }

}
