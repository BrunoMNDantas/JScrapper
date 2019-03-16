package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleShortAttributeParser extends SingleAttributeParser {

    public SingleShortAttributeParser(String attribute) {
        super(attribute, (short)0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Short.parseShort(element.getAttribute(super.attribute));
    }

}
