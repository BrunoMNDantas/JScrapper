package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

public class ArrayReferenceIntegerAttributeParser extends ArrayAttributeParser {

    public ArrayReferenceIntegerAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getAttribute(super.attribute));
    }

}
