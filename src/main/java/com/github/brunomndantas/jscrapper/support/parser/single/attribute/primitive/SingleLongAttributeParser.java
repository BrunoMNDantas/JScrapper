package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleLongAttributeParser extends SingleAttributeParser {

    public SingleLongAttributeParser(String attribute) {
        super(attribute, 0L);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Long.parseLong(element.getAttribute(super.attribute));
    }

}
