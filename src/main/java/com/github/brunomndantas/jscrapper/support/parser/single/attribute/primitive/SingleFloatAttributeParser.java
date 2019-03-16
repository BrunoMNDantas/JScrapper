package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleFloatAttributeParser extends SingleAttributeParser {

    public SingleFloatAttributeParser(String attribute) {
        super(attribute, 0.0f);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Float.parseFloat(element.getAttribute(super.attribute));
    }

}
