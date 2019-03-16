package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SingleBooleanTextParser extends SingleTextParser {

    public SingleBooleanTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Boolean.parseBoolean(element.getText());
    }

}