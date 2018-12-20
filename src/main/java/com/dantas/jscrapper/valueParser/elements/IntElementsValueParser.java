package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class IntElementsValueParser extends ElementsValueParser<Integer> {

    public IntElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public IntElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Integer parseElement(String value) throws ValueParserException {
        return Integer.parseInt(value);
    }

}
