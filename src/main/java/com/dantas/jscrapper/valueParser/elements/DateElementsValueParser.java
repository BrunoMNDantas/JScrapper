package com.dantas.jscrapper.valueParser.elements;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateElementsValueParser extends ElementsValueParser<Date> {

    private String format;
    public String getFormat() { return format; }



    public DateElementsValueParser(By selector, String format, String name) {
        super(selector, name);
    }

    public DateElementsValueParser(By selector, String format) {
        this(selector, format, "");
    }



    @Override
    protected Date parseElement(String value) throws ValueParserException {
        SimpleDateFormat format = new SimpleDateFormat(this.format);

        try {
            return format.parse(value);
        } catch (ParseException e) {
            throw new ValueParserException("Error parsing Date with format:" + this.format + " and value:" + value + "!", e);
        }
    }

}
