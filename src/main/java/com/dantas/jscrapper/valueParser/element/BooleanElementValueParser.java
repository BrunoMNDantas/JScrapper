package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class BooleanElementValueParser extends ElementValueParser<Boolean> {

    public BooleanElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public BooleanElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Boolean parseElement(String value) throws ValueParserException {
        return Boolean.parseBoolean(value);
    }

}
