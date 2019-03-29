package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import org.openqa.selenium.WebElement;

public class CollectionReferenceStringAttributeParser extends CollectionAttributeParser {

    public CollectionReferenceStringAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getAttribute(super.attribute);
    }

}
