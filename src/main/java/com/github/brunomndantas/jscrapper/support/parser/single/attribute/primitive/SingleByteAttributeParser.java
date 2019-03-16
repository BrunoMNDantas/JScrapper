package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleByteAttributeParser extends SingleAttributeParser {

    public SingleByteAttributeParser(String attribute) {
        super(attribute, (byte)0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Byte.parseByte(element.getAttribute(super.attribute));
    }

}

