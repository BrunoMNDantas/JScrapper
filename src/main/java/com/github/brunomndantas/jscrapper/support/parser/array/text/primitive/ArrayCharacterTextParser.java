package com.github.brunomndantas.jscrapper.support.parser.array.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

public class ArrayCharacterTextParser extends ArrayTextParser {

    public ArrayCharacterTextParser() {
        super('\u0000');
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getText().charAt(0);
    }

}