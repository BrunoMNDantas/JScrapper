package com.github.brunomndantas.jscrapper.support.parser.single.attribute.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.attribute.SingleAttributeParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

public class SingleDateAttributeParser extends SingleAttributeParser {

    private String format;
    public String getFormat() { return this.format; }



    public SingleDateAttributeParser( String attribute, String format) {
        super(attribute, null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getAttribute(super.attribute);
        return new SimpleDateFormat(this.format).parse(date);
    }

}
