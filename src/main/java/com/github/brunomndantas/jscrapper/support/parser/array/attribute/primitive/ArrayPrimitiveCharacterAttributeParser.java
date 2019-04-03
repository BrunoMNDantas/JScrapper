package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveCharacterAttributeParser extends ArrayAttributeParser {

    public ArrayPrimitiveCharacterAttributeParser(String attribute) {
        super(char.class, attribute, '\u0000');

}


    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getAttribute(super.attribute).charAt(0);
    }

}
