package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SinglePrimitiveFloatTextParser extends SingleTextParser {

    public SinglePrimitiveFloatTextParser() {
        super(0.0f);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Float.parseFloat(element.getText());
    }

}