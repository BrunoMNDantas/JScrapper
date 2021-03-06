package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SingleReferenceIntegerTextParser extends SingleTextParser {

    public SingleReferenceIntegerTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getText());
    }

}