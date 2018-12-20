package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class LongElementValueParser extends ElementValueParser<Long> {

    public LongElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public LongElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Long parseElement(String value) throws ValueParserException {
        return Long.parseLong(value);
    }

}
