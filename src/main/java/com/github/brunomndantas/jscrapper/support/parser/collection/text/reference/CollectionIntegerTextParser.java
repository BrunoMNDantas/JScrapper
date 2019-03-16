package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.text.CollectionTextParser;
import org.openqa.selenium.WebElement;

public class CollectionIntegerTextParser extends CollectionTextParser {

    public CollectionIntegerTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getText());
    }

}
