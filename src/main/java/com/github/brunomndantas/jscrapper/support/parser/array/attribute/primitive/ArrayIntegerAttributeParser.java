package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayIntegerAttributeParser extends ArrayAttributeParser {

    protected ArrayIntegerAttributeParser(String attribute) {
        super(attribute, 0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getAttribute(super.attribute));
    }

}
