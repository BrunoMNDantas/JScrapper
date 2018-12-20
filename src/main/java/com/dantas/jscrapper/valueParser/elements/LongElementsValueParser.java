package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class LongElementsValueParser extends ElementsValueParser<Long> {

    public LongElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public LongElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Long parseElement(String value) throws ValueParserException {
        return Long.parseLong(value);
    }

}
