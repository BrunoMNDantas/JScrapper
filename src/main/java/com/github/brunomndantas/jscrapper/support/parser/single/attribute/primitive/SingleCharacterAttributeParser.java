package com.github.brunomndantas.jscrapper.support.parser.single.attribute.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

public class SingleCharacterAttributeParser extends SingleAttributeParser {

    public SingleCharacterAttributeParser(String attribute) {
        super(attribute, '\u0000');
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getAttribute(super.attribute).charAt(0);
    }

}
