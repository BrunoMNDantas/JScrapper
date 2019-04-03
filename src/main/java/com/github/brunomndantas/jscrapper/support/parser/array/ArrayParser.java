package com.github.brunomndantas.jscrapper.support.parser.array;

import com.github.brunomndantas.jscrapper.support.parser.Parser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.lang.reflect.Array;
import java.util.Collection;

public abstract class ArrayParser extends Parser {

    private Class<?> klass;
    public Class<?> getKlass() { return this.klass; }



    public ArrayParser(Class<?> klass) {
        this.klass = klass;
    }



    @Override
    protected Object parseElements(WebDriver driver, Collection<WebElement> elements) throws Exception {
        Object[] result = (Object[]) Array.newInstance(this.klass, elements.size());

        int idx = 0;
        for(WebElement element : elements) {
            result[idx] = parseElement(driver, element);
            idx++;
        }

        return result;
    }



    protected abstract Object parseElement(WebDriver driver, WebElement element) throws Exception;

}
