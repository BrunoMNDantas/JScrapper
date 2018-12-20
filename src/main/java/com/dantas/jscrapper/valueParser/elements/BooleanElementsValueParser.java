package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class BooleanElementsValueParser extends ElementsValueParser<Boolean> {

    public BooleanElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public BooleanElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Boolean parseElement(String value) throws ValueParserException {
        return Boolean.parseBoolean(value);
    }

}
