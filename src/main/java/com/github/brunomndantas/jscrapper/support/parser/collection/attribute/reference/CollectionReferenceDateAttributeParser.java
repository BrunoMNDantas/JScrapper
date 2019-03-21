package com.github.brunomndantas.jscrapper.support.parser.collection.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.collection.attribute.CollectionAttributeParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

public class CollectionReferenceDateAttributeParser extends CollectionAttributeParser {

    private String format;
    public String getFormat() { return this.format; }



    protected CollectionReferenceDateAttributeParser(String attribute, String format) {
        super(attribute, null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getAttribute(super.attribute);
        return new SimpleDateFormat(this.format).parse(date);
    }

}
