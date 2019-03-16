package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.text.CollectionTextParser;
import org.openqa.selenium.WebElement;

public class CollectionShortTextParser extends CollectionTextParser {

    public CollectionShortTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Short.parseShort(element.getText());
    }

}