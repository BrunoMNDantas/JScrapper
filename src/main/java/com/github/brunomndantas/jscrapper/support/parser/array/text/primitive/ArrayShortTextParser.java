package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayShortTextParser extends ArrayTextParser {

    public ArrayShortTextParser() {
        super((short)0);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Short.parseShort(element.getText());
    }

}