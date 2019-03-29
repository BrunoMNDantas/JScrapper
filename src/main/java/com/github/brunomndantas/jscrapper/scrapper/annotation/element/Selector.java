package com.github.brunomndantas.jscrapper.scrapper.annotation.element;

import com.github.brunomndantas.jscrapper.core.selector.ISelector;
import com.github.brunomndantas.jscrapper.scrapper.annotation.SelectorType;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface Selector {

    String selector() default "";

    SelectorType selectorType() default SelectorType.ID;

    Class<? extends ISelector> value() default ISelector.class;

    /*Ignore this property. This property it is just to distinguish between userDefined annotation or default value annotation*/
    @Deprecated boolean isUserDefined() default true;

}
