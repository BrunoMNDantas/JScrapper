package com.github.brunomndantas.jscrapper.core.property;

public interface IProperty {

    Object get() throws PropertyException;

    void set(Object value) throws PropertyException;

}
