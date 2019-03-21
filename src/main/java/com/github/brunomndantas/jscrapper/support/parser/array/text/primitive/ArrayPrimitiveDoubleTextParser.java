package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveDoubleTextParser extends ArrayTextParser {

    public ArrayPrimitiveDoubleTextParser() {
        super(0.0d);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Double.parseDouble(element.getText());
    }

}