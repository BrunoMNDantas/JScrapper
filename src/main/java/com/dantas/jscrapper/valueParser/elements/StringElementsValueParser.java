package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class StringElementsValueParser extends ElementsValueParser<String> {

    public StringElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public StringElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected String parseElement(String value) throws ValueParserException {
        return value;
    }

}
