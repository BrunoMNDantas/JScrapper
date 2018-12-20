package com.dantas.jscrapper.valueParser.attributes;

import com.dantas.jscrapper.valueParser.ValueParserException;
import org.openqa.selenium.By;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAttributesValueParser extends AttributesValueParser<Date> {

    private String format;
    public String getFormat() { return format; }



    public DateAttributesValueParser(By selector, String attribute, String format, String name) {
        super(selector, attribute, name);
    }

    public DateAttributesValueParser(By selector, String attribute, String format) {
        this(selector, attribute, format, "");
    }



    @Override
    protected Date parseAttribute(String value) throws ValueParserException {
        SimpleDateFormat format = new SimpleDateFormat(this.format);

        try {
            return format.parse(value);
        } catch (ParseException e) {
            throw new ValueParserException("Error parsing Date with format:" + this.format + " and value:" + value + "!", e);
        }
    }

}
