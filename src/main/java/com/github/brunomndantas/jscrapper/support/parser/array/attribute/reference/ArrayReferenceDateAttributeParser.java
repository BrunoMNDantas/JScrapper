package com.github.brunomndantas.jscrapper.support.parser.array.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.attribute.ArrayAttributeParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;
import java.util.Date;

public class ArrayReferenceDateAttributeParser extends ArrayAttributeParser {

    private String format;
    public String getFormat() { return this.format; }



    public ArrayReferenceDateAttributeParser(String attribute, String format) {
        super(Date.class, attribute, null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getAttribute(super.attribute);
        return new SimpleDateFormat(this.format).parse(date);
    }

}
