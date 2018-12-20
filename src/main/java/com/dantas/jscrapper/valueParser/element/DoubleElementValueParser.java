package com.dantas.jscrapper.valueParser.element;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class DoubleElementValueParser extends ElementValueParser<Double> {

    public DoubleElementValueParser(By selector, String name) {
        super(selector, name);
    }

    public DoubleElementValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Double parseElement(String value) throws ValueParserException {
        return Double.parseDouble(value);
    }

}
