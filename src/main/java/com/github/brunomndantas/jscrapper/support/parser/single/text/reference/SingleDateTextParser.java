package com.github.brunomndantas.jscrapper.support.parser.single.text.reference;

import com.github.brunomndantas.jscrapper.support.parser.single.text.SingleTextParser;
import org.openqa.selenium.WebElement;

import java.text.SimpleDateFormat;

public class SingleDateTextParser extends SingleTextParser {

    private String format;
    public String getFormat() { return this.format; }



    public SingleDateTextParser(String format) {
        super(null);
        this.format = format;
    }



    @Override
    protected Object parseElement(WebElement element) throws Exception {
        String date = element.getText();
        return new SimpleDateFormat(this.format).parse(date);
    }

}