package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleDoubleAttributeParser extends SingleAttributeParser {

    public SingleDoubleAttributeParser(String attribute) {
        super(attribute, 0.0d);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Double.parseDouble(element.getAttribute(super.attribute));
    }

}
