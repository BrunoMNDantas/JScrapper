package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveShortAttributeParser extends ArrayAttributeParser {

    public ArrayPrimitiveShortAttributeParser(String attribute) {
        super(attribute, (short)0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Short.parseShort(element.getAttribute(super.attribute));
    }

}
