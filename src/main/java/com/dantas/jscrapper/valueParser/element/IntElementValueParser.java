package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class IntElementValueParser extends ElementValueParser<Integer> {

    public IntElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public IntElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Integer parseElement(String value) throws ValueParserException {
        return Integer.parseInt(value);
    }

}
