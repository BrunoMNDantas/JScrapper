package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayFloatTextParser extends ArrayTextParser {

    public ArrayFloatTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Float.parseFloat(element.getText());
    }

}
