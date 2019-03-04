package com.github.brunomndantas.jscrapper.core.instanceFactory;

public interface IInstanceFactory {


    <T> T create(Class<T> klass) throws InstanceFactoryException;


}
