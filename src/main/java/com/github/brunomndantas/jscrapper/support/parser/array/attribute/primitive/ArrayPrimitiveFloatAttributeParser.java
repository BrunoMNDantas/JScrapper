package com.github.brunomndantas.jscrapper.support.parser.array.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayPrimitiveFloatAttributeParser extends ArrayAttributeParser {

    protected ArrayPrimitiveFloatAttributeParser(String attribute) {
        super(attribute, 0.0f);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Float.parseFloat(element.getAttribute(super.attribute));
    }

}