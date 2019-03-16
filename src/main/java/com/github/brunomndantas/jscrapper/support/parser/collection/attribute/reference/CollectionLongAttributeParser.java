package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import org.openqa.selenium.WebElement;

public class CollectionLongAttributeParser extends CollectionAttributeParser {

    protected CollectionLongAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Long.parseLong(element.getAttribute(super.attribute));
    }

}
