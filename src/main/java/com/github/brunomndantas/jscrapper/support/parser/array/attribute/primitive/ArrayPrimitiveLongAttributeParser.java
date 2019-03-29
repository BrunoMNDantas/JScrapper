package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveLongAttributeParser extends ArrayAttributeParser {

    public ArrayPrimitiveLongAttributeParser(String attribute) {
        super(attribute, 0L);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Long.parseLong(element.getAttribute(super.attribute));
    }

}
