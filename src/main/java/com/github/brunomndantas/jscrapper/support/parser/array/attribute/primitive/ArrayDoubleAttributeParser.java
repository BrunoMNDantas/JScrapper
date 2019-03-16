package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayDoubleAttributeParser extends ArrayAttributeParser {

    protected ArrayDoubleAttributeParser(String attribute) {
        super(attribute, 0.0d);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Double.parseDouble(element.getAttribute(super.attribute));
    }

}
