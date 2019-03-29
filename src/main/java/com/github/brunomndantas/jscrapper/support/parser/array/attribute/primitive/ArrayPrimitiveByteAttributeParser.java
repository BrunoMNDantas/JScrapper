package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveByteAttributeParser extends ArrayAttributeParser {

    public ArrayPrimitiveByteAttributeParser(String attribute) {
        super(attribute, (byte)0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Byte.parseByte(element.getAttribute(super.attribute));
    }

}
