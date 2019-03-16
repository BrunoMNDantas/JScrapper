package com.github.brunomndantas.jscrapper.support.parser.collection.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.text.CollectionTextParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

public class CollectionDateTextParser extends CollectionTextParser {

    private String format;
    public String getFormat() { return this.format; }



    public CollectionDateTextParser(String format) {
        super(null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getText();
        return new SimpleDateFormat(this.format).parse(date);
    }

}