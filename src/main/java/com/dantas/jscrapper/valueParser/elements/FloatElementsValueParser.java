package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class FloatElementsValueParser extends ElementsValueParser<Float> {

    public FloatElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public FloatElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Float parseElement(String value) throws ValueParserException {
        return Float.parseFloat(value);
    }

}
