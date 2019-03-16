package com.github.brunomndantas.jscrapper.support.parser.single.text.primitive;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

public class SingleCharacterTextParser extends SingleTextParser {

    public SingleCharacterTextParser() {
        super('\u0000');
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        return element.getText().charAt(0);
    }

}
