package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.text.CollectionTextParser;
import org.openqa.selenium.WebElement;

public class CollectionLongTextParser extends CollectionTextParser {

    public CollectionLongTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Long.parseLong(element.getText());
    }

}
