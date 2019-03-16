package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayCharacterAttributeParser extends ArrayAttributeParser {

    protected ArrayCharacterAttributeParser(String attribute) {
        super(attribute, '\u0000');
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getAttribute(super.attribute).charAt(0);
    }

}
