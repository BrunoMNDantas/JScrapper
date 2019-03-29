package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import org.openqa.selenium.WebElement;

public class CollectionReferenceCharacterAttributeParser extends CollectionAttributeParser {

    public CollectionReferenceCharacterAttributeParser(String attribute) {
        super(attribute, null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getAttribute(super.attribute).charAt(0);
    }

}
