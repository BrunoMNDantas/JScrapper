package com.github.brunomndantas.jscrapper.scrapper.annotation.element;

import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface Selector {

    String selector() default "";

    SelectorType selectorType() default SelectorType.ID;

    Class<? extends ISelector> value() default ISelector.class;

}
