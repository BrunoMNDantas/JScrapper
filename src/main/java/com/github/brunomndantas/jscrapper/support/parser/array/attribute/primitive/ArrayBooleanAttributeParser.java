package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayBooleanAttributeParser extends ArrayAttributeParser {

    protected ArrayBooleanAttributeParser(String attribute) {
        super(attribute, false);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Boolean.parseBoolean(element.getAttribute(super.attribute));
    }

}
