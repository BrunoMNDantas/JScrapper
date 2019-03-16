package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SingleDoubleTextParser extends SingleTextParser {

    public SingleDoubleTextParser() {
        super(0.0d);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Double.parseDouble(element.getText());
    }

}