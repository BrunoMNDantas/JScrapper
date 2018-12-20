package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class FloatElementValueParser extends ElementValueParser<Float> {

    public FloatElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public FloatElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Float parseElement(String value) throws ValueParserException {
        return Float.parseFloat(value);
    }

}
