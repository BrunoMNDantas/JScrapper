package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SingleLongTextParser extends SingleTextParser {

    public SingleLongTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Long.parseLong(element.getText());
    }

}