package com.github.brunomndantas.jscrapper.support.parser.array.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.array.text.ArrayTextParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

public class ArrayDateTextParser extends ArrayTextParser {

    private String format;
    public String getFormat() { return this.format; }



    public ArrayDateTextParser(String format) {
        super(null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getText();
        return new SimpleDateFormat(this.format).parse(date);
    }

}