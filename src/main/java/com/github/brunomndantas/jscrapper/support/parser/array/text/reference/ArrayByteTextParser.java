package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayByteTextParser extends ArrayTextParser {

    public ArrayByteTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return Byte.parseByte(element.getText());
    }

}