package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayReferenceByteAttributeParser extends ArrayAttributeParser {

    public ArrayReferenceByteAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Byte.parseByte(element.getAttribute(super.attribute));
    }

}
