package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class StringElementValueParser extends ElementValueParser<String> {

    public StringElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public StringElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected String parseElement(String value) throws ValueParserException {
        return value;
    }

}
