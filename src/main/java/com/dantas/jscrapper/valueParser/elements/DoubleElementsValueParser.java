package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

public class DoubleElementsValueParser extends ElementsValueParser<Double> {

    public DoubleElementsValueParser(By selector, String name) {
        super(selector, name);
    }

    public DoubleElementsValueParser(By selector) {
        this(selector, "");
    }



    @Override
    protected Double parseElement(String value) throws ValueParserException {
        return Double.parseDouble(value);
    }

}
