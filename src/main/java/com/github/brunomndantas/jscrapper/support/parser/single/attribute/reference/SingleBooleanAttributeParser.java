package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleBooleanAttributeParser extends SingleAttributeParser {

    public SingleBooleanAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Boolean.parseBoolean(element.getAttribute(super.attribute));
    }

}
