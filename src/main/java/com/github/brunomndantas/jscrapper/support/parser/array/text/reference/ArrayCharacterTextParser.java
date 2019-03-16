package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayCharacterTextParser extends ArrayTextParser {

    public ArrayCharacterTextParser() {
        super(null);
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getText().charAt(0);
    }

}