package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import org.openqa.selenium.WebElement;

public class CollectionIntegerAttributeParser extends CollectionAttributeParser {

    protected CollectionIntegerAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getAttribute(super.attribute));
    }

}
