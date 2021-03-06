package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SinglePrimitiveIntegerTextParser extends SingleTextParser {

    public SinglePrimitiveIntegerTextParser() {
        super(0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Integer.parseInt(element.getText());
    }

}