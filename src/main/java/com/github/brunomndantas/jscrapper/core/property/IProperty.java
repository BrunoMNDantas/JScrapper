package com.github.brunomndantas.jscrapper.core.property;

public interface IProperty {

    Object get(Object instance) throws PropertyException;

    void set(Object instance, Object value) throws PropertyException;

}
