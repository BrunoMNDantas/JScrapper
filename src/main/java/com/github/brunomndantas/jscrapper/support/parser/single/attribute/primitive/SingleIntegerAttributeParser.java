package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleIntegerAttributeParser extends SingleAttributeParser {

    public SingleIntegerAttributeParser(String attribute) {
        super(attribute, 0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getAttribute(super.attribute));
    }

}
