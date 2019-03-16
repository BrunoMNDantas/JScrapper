package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.text.CollectionTextParser;
import org.openqa.selenium.WebElement;

public class CollectionDoubleTextParser extends CollectionTextParser {

    public CollectionDoubleTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Double.parseDouble(element.getText());
    }

}