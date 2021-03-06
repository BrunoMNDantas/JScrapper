package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayReferenceShortAttributeParser extends ArrayAttributeParser {

    public ArrayReferenceShortAttributeParser(String attribute) {
        super(Short.class, attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Short.parseShort(element.getAttribute(super.attribute));
    }

}
